package no.uib.inf101.grid;

/**
 * A cell in a grid.
 * 
 * @param pos the position of the cell
 * @param value the value in the cell
 * @param <E> the type of values in the grid
 */
public record GridCell<E>(CellPosition pos, E value) {
}
