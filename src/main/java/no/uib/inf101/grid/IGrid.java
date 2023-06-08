package no.uib.inf101.grid;

/**
 * A grid of cells. Each cell has a position and a value.
 */
public interface IGrid<E> extends IReadOnlyGrid<E> {

  /**
   * Set the value at the given position.
   *
   * @param pos the position
   * @param value the new value to set
   */
  void set(CellPosition pos, E value);

  /**
   * Fill the entire grid with the given value.
   * 
   * @param value the value to fill with
   */
  default void fill(E value) {
    for (GridCell<E> gc : this) {
      this.set(gc.pos(), value);
    }
  }
}
