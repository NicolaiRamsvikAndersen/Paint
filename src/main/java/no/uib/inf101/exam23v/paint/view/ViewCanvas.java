package no.uib.inf101.exam23v.paint.view;

import no.uib.inf101.graphics.CellPositionToPixelConverter;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

/**
 * A view for the canvas in the Paint program.
 * The canvas is drawn as a grid of colored cells.
 */
public class ViewCanvas extends JPanel {
  private static final int OUTER_BORDER = 0;
  private static final int INNER_BORDER = 0;
  private static final int CELL_SIZE = 1;
  private ViewablePaintModel model;

  /**
   * Creates a new canvas view.
   * @param model the model to view.
   */
  public ViewCanvas(ViewablePaintModel model) {
    Objects.requireNonNull(model, "Model cannot be null");

    this.model = model;
    this.setPreferredSize(calculatePreferredSize());
  }

  private Dimension calculatePreferredSize() {
    GridDimension dim = this.model.getCanvas();
    int width = dim.cols() * (CELL_SIZE + INNER_BORDER) + 2 * OUTER_BORDER + INNER_BORDER;
    int height = dim.rows() * (CELL_SIZE + INNER_BORDER) + 2 * OUTER_BORDER + INNER_BORDER;
    return new Dimension(width, height);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    drawCanvas(g2);
  }

  private void drawCanvas(Graphics2D g2) {
    CellPositionToPixelConverter converter = this.getConverter();
    for (GridCell<Color> gc : this.model.getCanvas()) {
      Rectangle2D cell = converter.getBoundsForCell(gc.pos());
      g2.setColor(gc.value());
      g2.fill(cell);
    }
  }

  /**
   * Get a converter that translates between cell positions and pixel
   * coordinates.
   */
  public CellPositionToPixelConverter getConverter() {
    Rectangle2D box = new Rectangle2D.Double(OUTER_BORDER, OUTER_BORDER,
        this.getWidth() - 2 * OUTER_BORDER, this.getHeight() - 2 * OUTER_BORDER);
    return new CellPositionToPixelConverter(box, this.model.getCanvas(), INNER_BORDER);
  }
  
}
