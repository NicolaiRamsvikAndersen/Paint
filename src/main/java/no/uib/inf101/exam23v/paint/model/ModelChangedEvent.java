package no.uib.inf101.exam23v.paint.model;

import no.uib.inf101.eventbus.Event;

/** An event that is fired when the model has changed. */
public record ModelChangedEvent(Object source) implements Event {
}
