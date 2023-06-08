package no.uib.inf101.grid.eventgrid;

import java.util.Iterator;
import java.util.Objects;

import no.uib.inf101.eventbus.EventBus;
import no.uib.inf101.eventbus.EventHandler;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.IGrid;

/**
 * A wrapper around an IGrid that fires events when the grid is
 * changed. Equality is determined by the equals method of the
 * elements, so if the new value is equal to the old value when
 * the set method is invoked, then no event will be fired.
 *
 * <p>
 * Example usage:
 * <pre>
 *   // create an EventGrid wrapping any IGrid
 *   EventGrid&lt;String&gt; eventGrid = new EventGrid&lt;&gt;(new Grid&lt;&gt;(10, 10, ""));
 *
 *   // listener setup
 *   eventGrid.register(event -&gt; System.out.println(event));
 *
 *   // changing any element in the grid will result in an event being fired
 *   eventGrid.set(new CellPosition(0, 0), "Hello world!");
 *   // prints "GridChangedEvent[grid=no.uib.inf101.grid.eventgrid.EventGrid@668bc3d5,
 *   //         pos=CellPosition[row=0, col=0], newValue=Hello world!, oldValue=]"
 *  </pre>
 *
 * @param <E> the type of elements in the grid.
 *           If the element type is mutable, then the mutation of an
 *           element in the grid will not result in an event being fired.
 *           If the equals method of the element type is overridden, then
 *           the equality of elements in the grid will be determined by
 *           the equals method, and an event will not be fired if the
 *           new value is equal to the old value.
 *
 * @see GridChangedEvent
 * @see IGrid
 */
public class EventGrid<E> implements IGrid<E> {
  
  private final EventBus eventBus = new EventBus();
  private final IGrid<E> grid;
  
  /**
   * Create a new EventGrid that wraps the given grid.
   * @param grid the grid to wrap
   */
  public EventGrid(IGrid<E> grid) {
    Objects.requireNonNull(grid, "grid cannot be null");
    this.grid = grid;
  }

  /**
   * Register an event handler to listen for changes to the grid.
   *
   * @param handler the event handler to register (non-null)
   */
  public void register(EventHandler handler) {
    Objects.requireNonNull(handler, "handler cannot be null");
    eventBus.register(handler);
  }

  /**
   * Unregister an event handler. If the same handler is registered
   * multiple times, then only one registration will be removed. If
   * the handler is not registered, then nothing will happen.
   *
   * @param handler the event handler to unregister
   * @return true if the handler was unregistered, false if the handler
   *         was not registered in the first place
   */
  public boolean unregister(EventHandler handler) {
    Objects.requireNonNull(handler, "handler cannot be null");
    return eventBus.unregister(handler);
  }
  
  @Override
  public void set(CellPosition pos, E newValue) {
    E oldValue = get(pos);
    this.grid.set(pos, newValue);
    if (!Objects.equals(oldValue, newValue)) {
      eventBus.post(new GridChangedEvent<E>(this, pos, newValue, oldValue));
    }
  }
  
  @Override
  public int rows() {
    return this.grid.rows();
  }
  
  @Override
  public int cols() {
    return this.grid.cols();
  }
  
  @Override
  public Iterator<GridCell<E>> iterator() {
    return this.grid.iterator();
  }
  
  @Override
  public E get(CellPosition pos) {
    return this.grid.get(pos);
  }
  
}
