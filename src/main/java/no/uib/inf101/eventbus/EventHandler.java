package no.uib.inf101.eventbus;

/**
 * An EventHandler is a function that takes an Event as input and
 * returns nothing. It is used to handle events that are posted to an
 * {@link EventBus}. Once the event handler is registered with the
 * EventBus, it will receive all events that are posted to the bus.
 */
@FunctionalInterface
public interface EventHandler {
  /**
   * Handles an event that is posted to an EventBus.
   *
   * @param event the event to handle
   */
  void handle(Event event);
}
