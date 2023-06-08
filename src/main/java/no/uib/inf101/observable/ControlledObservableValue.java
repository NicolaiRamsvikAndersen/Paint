package no.uib.inf101.observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControlledObservableValue<E> implements ObservableValue<E> {
    private final List<ValueChangedListener<E>> listeners = new ArrayList<>();
    private E value;
  
    public ControlledObservableValue() {
      this(null);
    }
  
    public ControlledObservableValue(E initialValue) {
      this.value = initialValue;
    }
  
    @Override
    public void addValueChangedListener(ValueChangedListener<E> listener) {
      this.listeners.add(listener);
    }
  
    @Override
    public boolean removeValueChangedListener(ValueChangedListener<E> listener) {
      return this.listeners.remove(listener);
    }
  
    @Override
    public E getValue() {
      return this.value;
    }
  
    public void setValue(E newValue) {
      if (!Objects.equals(this.value, newValue)) {
        E oldValue = this.value;
        this.value = newValue;
        this.notifyListeners(newValue, oldValue);
      }
    }
  
    private void notifyListeners(E newValue, E oldValue) {
      for (ValueChangedListener<E> listener : this.listeners) {
        listener.onValueChanged(this, newValue, oldValue);
      }
    }
  }
  