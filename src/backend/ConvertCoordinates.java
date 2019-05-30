package backend;

import javafx.scene.canvas.Canvas;

public class ConvertCoordinates {

    private Double xmin, xmax, ymin,ymax;

    public ConvertCoordinates (Canvas canvas) {
        xmin = 0.0 ;
        xmax = canvas.getHeight() / (double)canvas.getHeight();
        System.out.println("Adjusted xmin " + xmin + " Adjusted xmax: " + xmax);
    }

}
