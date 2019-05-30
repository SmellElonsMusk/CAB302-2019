package backend;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Line;

/**
 * LINE SHAPE FUNCTIONALITY
 *
 * @author Waldo Fouche, n9950095 (OOP)
 * @author Kevin Duong, n9934731 (Function)
 *
 **/

public class DrawLine extends Tool {

    private Line line;

    public DrawLine(Canvas canvas, ColorPicker colorPicker) {
        super(canvas,colorPicker);

        this.line = new javafx.scene.shape.Line();

        canvas.setOnMousePressed(e->{
            canvas.getGraphicsContext2D().setStroke(colorPicker.getValue());
            line.setStartX(e.getX());
            line.setStartY(e.getY());
        });

        canvas.setOnMouseDragged(e->{
            //TODO: Show realtime Line drag when in process of creating Line
        });

        canvas.setOnMouseReleased(e->{
            line.setEndX(e.getX());
            line.setEndY(e.getY());
            canvas.getGraphicsContext2D().strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());

            // Output LINE coordinates
            System.out.println("LINE " + line.getStartX() +  " " + line.getStartY() +  " " + line.getEndX() +  " " + line.getEndY());
        });
    }
}
