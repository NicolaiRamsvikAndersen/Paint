package no.uib.inf101.exam23v.paint.view;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * A view for the buttons in the ColorWhack game.
 * This view contains a reset button.
 */
public class ViewButtons extends JPanel {
  
  private final JButton clear;
  private final JButton save;

  /** Creates a new buttons view. */
  public ViewButtons() {
    this.clear = new JButton("Clear");
    this.save = new JButton("Save");
    this.add(this.clear);
    this.add(this.save);
  }

  /** Gets the reset button. */
  public JButton getClearButton() {
    return this.clear;
  }

  /** Gets the save button. */
  public JButton getSaveButton() {
    return this.save;
  }
  
}
