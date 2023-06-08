package no.uib.inf101.exam23v.paint.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.grid.IReadOnlyGrid;

import java.awt.Color;

/** A viewable (read-only) model for a Paint program. */
public interface ViewablePaintModel {

    /** Gets the current canvas. */
    IReadOnlyGrid<Color> getCanvas();

    /** Gets the current pen color. */
    Color getPenColor();

    /** Gets all the pixels in the image. */
    Iterable<GridCell<Color>> getPixels();
    
    /** Gets the size of the image. */
    GridDimension getImageSize();

}
