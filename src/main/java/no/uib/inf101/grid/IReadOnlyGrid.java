package no.uib.inf101.grid;

/**
 * A read-only grid. The implementing methods should be non-mutating.
 *
 * @param <E> the type of values in the grid
 */
public interface IReadOnlyGrid<E> extends GridDimension, Iterable<GridCell<E>> {

  /**
   * Get the value at the given position.
   * 
   * @param pos the position
   * @return the value at the given position
   */
  E get(CellPosition pos);
}
