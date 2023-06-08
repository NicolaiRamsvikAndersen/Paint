package no.uib.inf101.exam23v.paint.controller;
import no.uib.inf101.exam23v.paint.model.PaintModel;
import no.uib.inf101.exam23v.paint.view.ViewCanvas;
import no.uib.inf101.exam23v.paint.view.ViewColorPicker;
import no.uib.inf101.exam23v.paint.view.ViewMain;
import no.uib.inf101.graphics.CellPositionToPixelConverter;
import no.uib.inf101.grid.CellPosition;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * A controller for the ColorWhack game. The controller listens for
 * events on the view, such as clicks on the board, and updates the
 * model accordingly. Furthermore, the controller has a timer that
 * periodically pops up new colors on the board, and reacts to
 * clicks on the reset button.
 */
public class ControllerCanvas {
  
  private final PaintModel model;

  /**
   * Creates a new controller for the ColorWhack game.
   *
   * @param model the model to control
   * @param view the view on which to listen for events
   */
  public ControllerCanvas(PaintModel model, ViewMain view) {
    Objects.requireNonNull(model, "model cannot be null");
    Objects.requireNonNull(view, "view cannot be null");

    this.model = model;

    this.setupColorPicker(view.getColorPicker());
    this.setupMouseListenerOnCanvas(view.getCanvasView());
    this.setupClearButton(view.getClearButton());
    this.setupSaveButton(view.getSaveButton());
  }

  private void setupColorPicker(ViewColorPicker colorPicker) {
    colorPicker.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent event) {
        CellPositionToPixelConverter converter = colorPicker.getConverter();
        CellPosition pos = converter.getCellPositionOfPoint(event.getPoint());
        if (pos != null) {
          Color color = colorPicker.getColorAt(pos);
          model.setPenColor(color);
        }
      }
    });
  }

  private void setupMouseListenerOnCanvas(ViewCanvas viewCanvas) {
    viewCanvas.addMouseListener(new MouseAdapter() {

      @Override
      public void mousePressed(MouseEvent event) {
        CellPositionToPixelConverter converter = viewCanvas.getConverter();
        CellPosition pos = converter.getCellPositionOfPoint(event.getPoint());
        if (pos != null) {
          model.draw(pos);
        }
      }
    });
    viewCanvas.addMouseMotionListener(new MouseAdapter() {
      
      @Override
      public void mouseDragged(MouseEvent event) {
        CellPositionToPixelConverter converter = viewCanvas.getConverter();
        CellPosition pos = converter.getCellPositionOfPoint(event.getPoint());
        if (pos != null) {
          model.draw(pos);
        }
      }
    });
  }

  private void setupClearButton(JButton clearButton) {
    clearButton.addActionListener(event -> this.model.clear());
  }

  private void setupSaveButton(JButton saveButton) {
    saveButton.addActionListener(event -> this.model.save());
  }
}
