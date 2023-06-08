package no.uib.inf101.eventbus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An EventBus is a simple event bus that can be used to send events
 * to multiple event handlers. Every {@link EventHandler} that is
 * registered with the EventBus will receive all events that are
 * subsequently posted to the EventBus.
 * <p>
 * An event producer must create an {@link Event} object and then call
 * the {@link #post(Event)} method on the EventBus. When an event
 * is posted to the EventBus, all registered event handlers will
 * receive the event.
 */
public class EventBus {
  private List<EventHandler> eventHandlers = new ArrayList<>();

  /**
   * Register an event handler with this EventBus. The event handler
   * will receive all events that are posted to the EventBus after
   * the event handler is registered.
   *
   * @param eventHandler the event handler to register (non-null)
   */
  public void register(EventHandler eventHandler) {
    Objects.requireNonNull(eventHandler);
    this.eventHandlers.add(eventHandler);
  }

  /**
   * Unregister an event handler from this EventBus. The event handler
   * will no longer receive events that are posted to the EventBus
   * after the event handler is unregistered.
   * <p>
   * If the event handler is not registered with this EventBus, this
   * method does nothing.
   * <p>
   * If the event handler is registered multiple times with this
   * EventBus, only one registration is removed.
   *
   * @param eventHandler the event handler to unregister
   * @return true if the event handler was unregistered, false if the
   *       event handler was not registered in the first place
   */
  public boolean unregister(EventHandler eventHandler) {
    return this.eventHandlers.remove(eventHandler);
  }

  /**
   * Post an event to this EventBus. All registered event handlers
   * will receive the event.
   *
   * @param event the event to post
   */
  public void post(Event event) {
    for (EventHandler eventHandler : this.eventHandlers) {
      eventHandler.handle(event);
    }
  }
}