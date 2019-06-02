package backend;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.shape.Ellipse;

/**
 * ELLIPSE Shape Drawing Functionality
 */

public class DrawEllipse extends Tool {

    private Ellipse ellipse;

    public DrawEllipse(Canvas canvas, ToggleButton fillButton, ColorPicker colorPicker) {
        super(canvas, fillButton);

        this.ellipse = new Ellipse();

        canvas.setOnMousePressed(e -> {
            if (!fillButton.isSelected()) {
                canvas.getGraphicsContext2D().setStroke(colorPicker.getValue());
            }

            if (fillButton.isSelected()) {
                canvas.getGraphicsContext2D().setFill(colorPicker.getValue());
            }
            ellipse.setCenterX(e.getX());
            ellipse.setCenterY(e.getY());
        });

        canvas.setOnMouseReleased(e -> {
            ellipse.setRadiusX(Math.abs(e.getX() - ellipse.getCenterX()));
            ellipse.setRadiusY(Math.abs(e.getY() - ellipse.getCenterY()));

            if (ellipse.getCenterX() > e.getX()) {
                ellipse.setCenterX(e.getX());
            }
            if (ellipse.getCenterY() > e.getY()) {
                ellipse.setCenterY(e.getY());
            }

            if (fillButton.isSelected()) {
                canvas.getGraphicsContext2D().fillOval(ellipse.getCenterX(), ellipse.getCenterY(), ellipse.getRadiusX(), ellipse.getRadiusY());
                canvas.getGraphicsContext2D().strokeOval(ellipse.getCenterX(), ellipse.getCenterY(), ellipse.getRadiusX(), ellipse.getRadiusY());
            } else {
                canvas.getGraphicsContext2D().strokeOval(ellipse.getCenterX(), ellipse.getCenterY(), ellipse.getRadiusX(), ellipse.getRadiusY());
            }

            // Square Ratio 1:1
            String centreX = String.format("%.6f", ellipse.getCenterX() / canvas.getWidth());
            String centreY = String.format("%.6f", ellipse.getCenterY() / canvas.getWidth());
            String radiusX = String.format("%.6f", (ellipse.getRadiusX() + ellipse.getCenterX()) / canvas.getWidth());
            String radiusY = String.format("%.6f", (ellipse.getRadiusY() + ellipse.getCenterY()) / canvas.getWidth());

            // Output ELLIPSE coordinates: X1,Y1,X2,Y2
            System.out.println("ELLIPSE " + centreX + " " + centreY + " " + radiusX + " " + radiusY);
        });
    }
}