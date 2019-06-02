package backend;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Line;

/**
 * LINE Shape Drawing Functionality
 */

public class DrawLine extends Tool {

    private Line line;

    public DrawLine(Canvas canvas, ColorPicker colorPicker) {
        super(canvas, colorPicker);

        this.line = new javafx.scene.shape.Line();

        canvas.setOnMousePressed(e -> {
            canvas.getGraphicsContext2D().setStroke(colorPicker.getValue());
            line.setStartX(e.getX());
            line.setStartY(e.getY());
        });

        canvas.setOnMouseReleased(e -> {
            line.setEndX(e.getX());
            line.setEndY(e.getY());
            canvas.getGraphicsContext2D().strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());

            // Square Ratio 1:1
            String startX = String.format("%.6f", line.getStartX() / canvas.getWidth());
            String startY = String.format("%.6f", line.getStartY() / canvas.getHeight());
            String endX = String.format("%.6f", line.getEndX() / canvas.getWidth());
            String endY = String.format("%.6f", line.getEndY() / canvas.getHeight());

            //Output LINE coordinates X1,Y1,X2,Y2
            System.out.println("LINE " + startX + " " + startY + " " + endX + " " + endY);
        });
    }
}