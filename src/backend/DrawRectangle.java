package backend;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.shape.Rectangle;

/**
 * RECTANGLE Shape Drawing Functionality
 */

public class DrawRectangle extends Tool {

    private Rectangle rectangle;

    public DrawRectangle(Canvas canvas, ToggleButton fillButton, ColorPicker colorPicker) {
        super(canvas);

        this.rectangle = new Rectangle();

        canvas.setOnMousePressed(e -> {
            if (!fillButton.isSelected()) {
                canvas.getGraphicsContext2D().setStroke(colorPicker.getValue());
            }

            if (fillButton.isSelected()) {
                canvas.getGraphicsContext2D().setFill(colorPicker.getValue());
            }
            rectangle.setX(e.getX());
            rectangle.setY(e.getY());
        });

        canvas.setOnMouseReleased(e -> {
            rectangle.setWidth(Math.abs((e.getX() - rectangle.getX())));
            rectangle.setHeight(Math.abs((e.getY() - rectangle.getY())));

            if (rectangle.getX() > e.getX()) {
                rectangle.setX(e.getX());
            }

            if (rectangle.getY() > e.getY()) {
                rectangle.setY(e.getY());
            }

            if (fillButton.isSelected()) {
                canvas.getGraphicsContext2D().fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight()); // Creates filled in shape
                canvas.getGraphicsContext2D().strokeRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight()); // Creates outline of shape
            } else {
                canvas.getGraphicsContext2D().strokeRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
            }

            // Square Ratio 1:1
            String startX = String.format("%.6f", rectangle.getX() / canvas.getWidth());
            String startY = String.format("%.6f", rectangle.getY() / canvas.getHeight());
            String endX = String.format("%.6f", (rectangle.getWidth() + rectangle.getX()) / canvas.getWidth());
            String endY = String.format("%.6f", (rectangle.getHeight() + rectangle.getY()) / canvas.getHeight());

            // Output RECTANGLE coordinates: X1,Y1,X2,Y2
            System.out.println("RECTANGLE " + startX + " " + startY + " " + endX + " " + endY);
        });
    }
}