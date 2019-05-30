package backend;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;

/**
 * PLOT SHAPE FUNCTIONALITY
 *
 * @author Waldo Fouche, n9950095 (OOP)
 * @author Kevin Duong, n9934731 (Function)
 *
 **/

public class DrawPlot extends Tool {

    public DrawPlot(Canvas canvas, ColorPicker colorPicker) {
        super(canvas, colorPicker);

        canvas.setOnMouseClicked( e -> {
            canvas.getGraphicsContext2D().setFill(colorPicker.getValue());
            canvas.getGraphicsContext2D().fillRoundRect(e.getX(),e.getY(),5,5,5,5);

            // Square Ratio 1:1
            String x1 = String.format("%.2f", e.getX()/canvas.getWidth());
            String y1 = String.format("%.2f", e.getY()/canvas.getWidth());

            // Output PLOT coordinates: X,Y
            System.out.println("PLOT " + x1 + " " + y1);
        });
    }
}
