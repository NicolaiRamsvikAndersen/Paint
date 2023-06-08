package no.uib.inf101.grid.eventgrid;

import no.uib.inf101.eventbus.Event;
import no.uib.inf101.grid.CellPosition;

/** An event that is fired when a cell in an EventGrid changes. */
public record GridChangedEvent<E>(EventGrid<E> grid, CellPosition pos, E newValue, E oldValue) implements Event {
}
