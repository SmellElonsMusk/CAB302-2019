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

            // Square Ratio 1:1
            String startX = String.format("%.6f", line.getStartX()/canvas.getWidth());
            String startY = String.format("%.6f", line.getStartY()/canvas.getWidth());
            String endX = String.format("%.6f", line.getEndX()/canvas.getWidth());
            String endY = String.format("%.6f",line.getEndY()/canvas.getWidth());


            // If (X2) reaches beyond left border
            if (line.getEndX()/canvas.getWidth() < 0) {
                endX = "0.0";
            }

            // If (X2) reaches beyond right border
            if (line.getEndX()/canvas.getWidth() > 1) {
                endX = "1.0";
            }

            // IF (Y2) reaches above the top border
            if (line.getEndY()/canvas.getWidth() < 0 || line.getEndY()/canvas.getWidth() > 1) {
                endY = "0.0";
            }

            // IF (Y2) reaches the bottom border
            if (line.getEndY()/canvas.getWidth() > 1) {
                endY = "1.0";
            }

            //Output LINE coordinates X1,Y1,X2,Y2
            System.out.println("LINE " + startX + " " + startY +  " " + endX +  " " + endY);
        });
    }
}
