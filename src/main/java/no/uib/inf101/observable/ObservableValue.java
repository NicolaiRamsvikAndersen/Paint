package no.uib.inf101.observable;

public interface ObservableValue<E> {
    void addValueChangedListener(ValueChangedListener<E> listener);
    boolean removeValueChangedListener(ValueChangedListener<E> listener);
    E getValue();
  }