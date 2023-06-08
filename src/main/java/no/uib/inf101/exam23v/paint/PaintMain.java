package no.uib.inf101.exam23v.paint;

import javax.swing.JFrame;

import no.uib.inf101.eventbus.EventBus;
import no.uib.inf101.exam23v.paint.controller.ControllerCanvas;
import no.uib.inf101.exam23v.paint.model.PaintModel;
import no.uib.inf101.exam23v.paint.view.ViewMain;

import java.awt.*;

public class PaintMain {
  public static void main(String[] args) {

    EventBus eventBus = new EventBus();
    PaintModel model = new PaintModel(eventBus);
    ViewMain view = new ViewMain(eventBus, model);
    new ControllerCanvas(model, view);

    JFrame frame = new JFrame();
    frame.setMinimumSize(new Dimension(200, 90));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("INF101 Paint");
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
  }
}
