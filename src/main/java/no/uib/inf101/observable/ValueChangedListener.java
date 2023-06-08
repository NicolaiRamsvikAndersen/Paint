package no.uib.inf101.observable;

@FunctionalInterface
public interface ValueChangedListener<E> {
  void onValueChanged(ObservableValue<E> source, E newValue, E oldValue);
}