package no.uib.inf101.exam23v.paint.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Objects;

import javax.swing.JPanel;
import no.uib.inf101.graphics.CellPositionToPixelConverter;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;

public class ViewColorPicker extends JPanel {

    private static final List<Color> ALL_COLORS = List.of(
        Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
        Color.CYAN, Color.MAGENTA, Color.WHITE
    );
    private static final Grid<Color> grid = new Grid<>(1, ALL_COLORS.size()); 
    private static final int OUTER_BORDER = 5;
    private static final int INNER_BORDER = 1;
    private int CELL_SIZE = 20;
    private ViewablePaintModel model;


  /**
   * Creates a new canvas view.
   * @param model the model to view.
   */
  public ViewColorPicker(ViewablePaintModel model) {
    Objects.requireNonNull(model, "model cannot be null");
    
    for (int i = 0; i < ALL_COLORS.size(); i++) {
        CellPosition pos = new CellPosition(0, i);
        grid.set(pos, ALL_COLORS.get(i));
    }
    this.model = model;
    this.setPreferredSize(calculatePreferredSize());
  }

  private Dimension calculatePreferredSize() {
    int cols = ALL_COLORS.size();
    int rows = 1;
    int width = cols * (CELL_SIZE + INNER_BORDER) + 2 * OUTER_BORDER + INNER_BORDER;
    int height = rows * (CELL_SIZE + INNER_BORDER) + 2 * OUTER_BORDER + INNER_BORDER;
    return new Dimension(width, height);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    drawColors(g2);
  }

  private void drawColors(Graphics2D g2) {
    CellPositionToPixelConverter converter = this.getConverter();
    for (GridCell<Color> gc : grid) {
      Rectangle2D cell = converter.getBoundsForCell(gc.pos());
      g2.setColor(gc.value());
      g2.fill(cell);
      if (model.getPenColor() == gc.value()) {
        g2.setColor(Color.BLACK);
        g2.drawRect((int) cell.getX(), (int) cell.getY(), (int) cell.getWidth(), (int) cell.getHeight());
      }
    }
  }

  /**
   * Get a converter that translates between cell positions and pixel
   * coordinates.
   */
  public CellPositionToPixelConverter getConverter() {
    Rectangle2D box = new Rectangle2D.Double(OUTER_BORDER, OUTER_BORDER,
        this.getWidth() - 2 * OUTER_BORDER, this.getHeight() - 2 * OUTER_BORDER);
    return new CellPositionToPixelConverter(box, grid, INNER_BORDER);
  }

  public Color getColorAt(CellPosition pos) {
      return grid.get(pos);
  }

}
