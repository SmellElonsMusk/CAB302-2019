package backend;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;

/**
 * POLYGON SHAPE FUNCTIONALITY
 *
 * @author Waldo Fouche, n9950095 (OOP)
 * @author Kevin Duong, n9934731 (Function)
 *
 **/

public class DrawPolygon extends Tool {
    public DrawPolygon(Canvas canvas, ColorPicker colorPicker) {
        super(canvas, colorPicker);

        canvas.setOnMousePressed( e -> {
            canvas.getGraphicsContext2D().setStroke(colorPicker.getValue());
        });

        canvas.setOnMouseDragged(e->{
            //TODO: Show realtime Line drag when in process of creating Line
        });

        canvas.setOnMouseReleased(e->{
            // Output POLYGON coordinates
            System.out.println("POLYGON ");
        });

    }
}
