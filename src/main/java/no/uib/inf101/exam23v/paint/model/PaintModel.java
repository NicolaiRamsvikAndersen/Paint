package no.uib.inf101.exam23v.paint.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Objects;

import javax.swing.JFileChooser;

import no.uib.inf101.eventbus.EventBus;
import no.uib.inf101.exam23v.paint.view.ViewablePaintModel;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.grid.IReadOnlyGrid;
import no.uib.inf101.grid.eventgrid.EventGrid;

public class PaintModel implements ViewablePaintModel {

    //Constants
    private static final int ROWS = 200;
    private static final int COLS = 200;
    private static final Color EMPTY_COLOR = Color.WHITE;

    
    private final EventGrid<Color> grid;
    private final EventBus eventBus;
    private Color penColor = Color.BLACK;
    
    /**
     * Creates a new ColorWhackModel.
     *
     * @param eventBus the event bus to use
     * @param random  the random number generator to use
     */
    public PaintModel(EventBus eventBus) {
        Objects.requireNonNull(eventBus, "eventBus cannot be null");
        this.eventBus = eventBus;
        this.grid = new EventGrid<>(new Grid<>(ROWS, COLS));
        this.clear();
    }

    /**
     * Sets the pen color.
     *
     * @param color the new pen color
     */
    public void setPenColor(Color color) {
        this.penColor = color;
        this.eventBus.post(new ModelChangedEvent(this));
    }

    
    /**
     * Sets a given color on a given position.
     *
     * @param pos the position to set.
     */
    public void draw(CellPosition pos) {
        Objects.requireNonNull(pos, "pos cannot be null");
        this.grid.set(pos, this.penColor);
        this.eventBus.post(new ModelChangedEvent(this));
    }

    /** Clears the canvas */
    public void clear() {
        this.grid.fill(EMPTY_COLOR);
        this.eventBus.post(new ModelChangedEvent(this));
    }

    /** Saves the image */
    public void save() {
        BufferedImage image = new BufferedImage(COLS, ROWS, BufferedImage.TYPE_INT_RGB);
        for (GridCell<Color> cell : this.grid) {
            image.setRGB(cell.pos().col(), cell.pos().row(), cell.value().getRGB());
        }
        Graphics2D g2 = image.createGraphics();
        g2.dispose();

        //Denne delen ble skrevet med hjelp av CoPilot
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            try {
                javax.imageio.ImageIO.write(image, "png", new java.io.File(fileToSave.getAbsolutePath()));
            } catch (java.io.IOException e) {
                System.out.println("Could not save image");
            }
        }


    }

    @Override
    public IReadOnlyGrid<Color> getCanvas() {
        return this.grid;
    }

    @Override
    public Color getPenColor() {
        return this.penColor;
    }

    @Override
    public Iterable<GridCell<Color>> getPixels() {
        return this.grid;
    }

    @Override
    public GridDimension getImageSize() {
        return grid;
    }
}
