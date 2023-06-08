package no.uib.inf101.exam23v.paint.view;

import no.uib.inf101.eventbus.Event;
import no.uib.inf101.eventbus.EventBus;
import no.uib.inf101.eventbus.EventHandler;
import no.uib.inf101.exam23v.paint.model.ModelChangedEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.Objects;

/**
 * The main view for the ColorWhack game. This view is divided into
 * three parts: a score view, a board view, and a buttons view.
 */
public class ViewMain extends JPanel implements EventHandler {
  
  private final ViewColorPicker colorPicker;
  private final ViewCanvas canvas;
  private final ViewButtons viewButtons;
  private final ViewablePaintModel model;

  /**
   * Creates a new main view for the ColorWhack game.
   *
   * @param eventBus an event bus on which ModelChangedEvents are
   *                 posted whenever the model changes (non-null)
   * @param model the model to display (non-null)
   */
  public ViewMain(EventBus eventBus, ViewablePaintModel model) {
    Objects.requireNonNull(eventBus, "Event bus cannot be null");
    Objects.requireNonNull(model, "Model cannot be null");

    this.model = model;
    eventBus.register(this);
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    this.colorPicker = new ViewColorPicker(model);
    this.canvas = new ViewCanvas(model);
    this.viewButtons = new ViewButtons();

    this.add(this.colorPicker);
    this.add(this.canvas);
    this.add(this.viewButtons);
  }

  public ViewColorPicker getColorPicker() {
    return this.colorPicker;
  }

  /** Gets the canvas view. */
  public ViewCanvas getCanvasView() {
    return this.canvas;
  }

  /** Gets the clear button. */
  public JButton getClearButton() {
    return this.viewButtons.getClearButton();
  }

  /** Gets the save button. */
  public JButton getSaveButton() {
    return this.viewButtons.getSaveButton();
  }

  @Override
  public void handle(Event event) {
    if (event instanceof ModelChangedEvent modelChangedEvent) {
      if (Objects.equals(this.model, modelChangedEvent.source())) {
        this.repaint();
      }
    }
  }
}
