package backend;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;

/**
 * PLOT Shape Drawing Functionality
 */

public class DrawPlot extends Tool {

    public DrawPlot(Canvas canvas, ColorPicker colorPicker) {
        super(canvas, colorPicker);

        canvas.setOnMouseClicked(e -> {
            canvas.getGraphicsContext2D().setFill(colorPicker.getValue());
            canvas.getGraphicsContext2D().fillRoundRect(e.getX(), e.getY(), 1, 1, 1, 1);

            //Square Ratio 1:1
            String x1 = String.format("%.6f", e.getX() / canvas.getWidth());
            String y1 = String.format("%.6f", e.getY() / canvas.getHeight());

            // Output PLOT coordinates: X,Y
            System.out.println("PLOT " + x1 + " " + y1);
        });
    }
}