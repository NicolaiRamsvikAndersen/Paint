package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * A grid is a two-dimensional structure of cells, where each cell
 * contains an element of type E. The grid is indexed by CellPosition
 * objects, where the top-left cell has position (0, 0).
 * <p>
 * Example 1: basic usage
 * <pre>
 *   // Create a 3x3 grid with the value 0 in each cell
 *   Grid&lt;Integer&gt; grid = new Grid&lt;&gt;(3, 3, 0);
 *
 *   // Set the value of the cell at position (1, 2) to 42
 *   grid.set(new CellPosition(1, 2), 42);
 *
 *   // Get the value of the cell at position (1, 2)
 *   int value = grid.get(new CellPosition(1, 2));
 *   // value is now 42
 * </pre>
 *
 * Example 2: using a lambda to initialize the grid
 * <pre>
 *   // Create a 4x4 grid where each cell contains
 *   // the sum of its row and column
 *   Grid&lt;Integer&gt; grid = new Grid&lt;&gt;(4, 4, pos -&gt; pos.row() + pos.col());
 *
 *   // Get the value of the cell at position (2, 3)
 *   int value = grid.get(new CellPosition(2, 3));
 *   // value is now 5
 * </pre>
 *
 *
 * @param <E> the type of elements in the grid.
 */
public class Grid<E> implements IGrid<E>{
  
  private final List<List<E>> grid = new ArrayList<>();

  /**
   * Create a new grid with the given number of rows and columns,
   * where each cell is initialized using the given function.
   *
   * @param rows the number of rows in the grid
   * @param cols the number of columns in the grid
   * @param initializer a function that takes a CellPosition and
   *                    returns the initial value of the cell at that
   *                    position (not null)
   */
  public Grid(int rows, int cols, Function<CellPosition, E> initializer) {
    Objects.requireNonNull(initializer);
    if (rows <= 0) throw new IllegalArgumentException("rows must be positive");
    if (cols <= 0) throw new IllegalArgumentException("cols must be positive");

    for (int row = 0; row < rows; row++) {
      List<E> rowList = new ArrayList<>();
      for (int col = 0; col < cols; col++) {
        rowList.add(initializer.apply(new CellPosition(row, col)));
      }
      grid.add(rowList);
    }
  }

  /**
   * Create a new grid with the given number of rows and columns,
   * where each cell is initialized to the given default value.
   *
   * @param rows the number of rows in the grid
   * @param cols the number of columns in the grid
   * @param defaultValue the initial value of each cell
   */
  public Grid(int rows, int cols, E defaultValue) {
    this(rows, cols, pos -> defaultValue);
  }

  /**
   * Create a new grid with the given number of rows and columns,
   * where each cell is initialized to null.
   *
   * @param rows the number of rows in the grid
   * @param cols the number of columns in the grid
   */
  public Grid(int rows, int cols) {
    this(rows, cols, (E) null);
  }

  @Override
  public E get(CellPosition pos) {
    requireValidPosition(pos);
    return this.grid.get(pos.row()).get(pos.col());
  }

  @Override
  public void set(CellPosition pos, E value) {
    requireValidPosition(pos);
    this.grid.get(pos.row()).set(pos.col(), value);
  }

  private void requireValidPosition(CellPosition pos) {
    Objects.requireNonNull(pos, "pos cannot be null");
    if (!this.contains(pos)) {
      throw new IllegalArgumentException("pos " + pos + " is not in this grid, which has "
          + this.rows() + " rows and " + this.cols() + " cols");
    }
  }

  @Override
  public Iterator<GridCell<E>> iterator() {
    return new Iterator<>() {
      private int nextRow = 0;
      private int nextCol = 0;
      private final int rows = rows();
      private final int cols = cols();

      @Override
      public boolean hasNext() {
        return this.nextRow < this.rows;
      }

      @Override
      public GridCell<E> next() {
        CellPosition pos = new CellPosition(this.nextRow, this.nextCol);
        E value = get(pos);
        this.prepareForNext();
        return new GridCell<>(pos, value);
      }

      private void prepareForNext() {
        this.nextCol++;
        if (this.nextCol >= this.cols) {
          this.nextCol = 0;
          this.nextRow++;
        }
      }
    };
  }

  @Override
  public int rows() {
    return this.grid.size();
  }

  @Override
  public int cols() {
    return this.grid.get(0).size();
  }
}
