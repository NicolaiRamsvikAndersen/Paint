package no.uib.inf101.exam23v.paint;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.eventbus.EventBus;
import no.uib.inf101.exam23v.paint.model.PaintModel;
import no.uib.inf101.grid.CellPosition;

public class TestPaintController {

    private PaintModel model;

    @BeforeEach
    void prepareStandardModel() {
      EventBus eventBus = new EventBus();
      this.model = new PaintModel(eventBus);
    }

    @Test
    void testDraw() {

        CellPosition pos = new CellPosition(1, 1);

        model.draw(pos);
        
        assertEquals(model.getCanvas().get(pos), model.getPenColor());
    }

    @Test
    void testClear() {

        CellPosition pos = new CellPosition(1, 1);

        model.draw(pos);
        model.clear();
        
        assertEquals(model.getCanvas().get(pos), Color.WHITE);
    }

    @Test
    void testSetPenColor() {

        CellPosition pos = new CellPosition(1, 1);

        model.setPenColor(Color.RED);
        model.draw(pos);
        
        assertEquals(model.getCanvas().get(pos), Color.RED);
    }

    @Test
    void testGetPenColor() {

        assertEquals(model.getPenColor(), Color.BLACK);
    }
    
}
