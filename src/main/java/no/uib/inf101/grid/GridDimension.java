package no.uib.inf101.grid;

/** 
 * A GridDimension is the dimension of a grid. It has methods for
 * retrieving the number of rows and the number of columns.
 */
public interface GridDimension {

  /** Number of rows in the grid */
  int rows();

  /** Number of columns in the grid */
  int cols();

  /**
   * Returns true if the given position is within the grid.
   *
   * @param pos the position to check (may be null)
   * @return true if the position is not null and is within the grid
   */
  default boolean contains(CellPosition pos) {
    return pos != null
        && pos.row() >= 0
        && pos.row() < rows()
        && pos.col() >= 0
        && pos.col() < cols();
  }

  /**
   * A simplest possible GridDimension object, in the form of a
   * plain record. To create such a record, use the syntax
   * {@code new GridDimension.Record(rows, cols)}.
   *
   * @param rows the number of rows
   * @param cols the number of columns
   */
  public record Record(int rows, int cols) implements GridDimension {}
}
