package backend;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;

public class DrawPolygon extends Tool {
    public DrawPolygon(Canvas canvas, ColorPicker colorPicker) {
        super(canvas, colorPicker);

        // Canvas drawing
        canvas.setOnMousePressed( e -> {
            canvas.getGraphicsContext2D().setStroke(colorPicker.getValue());

        });

        canvas.setOnMouseDragged(e->{
            canvas.getGraphicsContext2D().lineTo(e.getX(), e.getY());
            //TODO: Show realtime DrawLine drag when making DrawLine
        });

        canvas.setOnMouseReleased(e->{
            // Output POLYGON coordinates
            System.out.println("POLYGON ");
        });

    }
}
