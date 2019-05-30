package backend;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.shape.Ellipse;

/**
 * ELLIPSE SHAPE FUNCTIONALITY
 *
 * @author Waldo Fouche, n9950095 (OOP)
 * @author Kevin Duong, n9934731 (Function)
 *
 **/

public class DrawEllipse extends Tool {

    private Ellipse ellipse;

    public DrawEllipse(Canvas canvas, ToggleButton fillButton , ColorPicker colorPicker) {
        super(canvas,fillButton);

        this.ellipse = new Ellipse();

        canvas.setOnMousePressed(e -> {
            if (!fillButton.isSelected()) {
                canvas.getGraphicsContext2D().setStroke(colorPicker.getValue());
            }

            if  (fillButton.isSelected()) {
                canvas.getGraphicsContext2D().setFill(colorPicker.getValue());
            }
            ellipse.setCenterX(e.getX());
            ellipse.setCenterY(e.getY());
        });

        canvas.setOnMouseDragged(e -> {
            //TODO: Show realtime Ellipse drag when in process of creating Ellipse
        });

        canvas.setOnMouseReleased(e -> {
            ellipse.setRadiusX(Math.abs(e.getX() - ellipse.getCenterX()));
            ellipse.setRadiusY(Math.abs(e.getY() - ellipse.getCenterY()));

            if(ellipse.getCenterX() > e.getX()) {
                ellipse.setCenterX(e.getX());
            }
            if(ellipse.getCenterY() > e.getY()) {
                ellipse.setCenterY(e.getY());
            }

            if (fillButton.isSelected()) {
                canvas.getGraphicsContext2D().fillOval(ellipse.getCenterX(), ellipse.getCenterY(), ellipse.getRadiusX(), ellipse.getRadiusY());
                canvas.getGraphicsContext2D().strokeOval(ellipse.getCenterX(), ellipse.getCenterY(), ellipse.getRadiusX(), ellipse.getRadiusY());
            } else {
                canvas.getGraphicsContext2D().strokeOval(ellipse.getCenterX(), ellipse.getCenterY(), ellipse.getRadiusX(), ellipse.getRadiusY());
            }

            // Square Ratio 1:1
            String centreX = String.format("%.2f", ellipse.getCenterX()/canvas.getWidth());
            String centreY = String.format("%.2f", ellipse.getCenterY()/canvas.getWidth());
            String radiusX = String.format("%.2f", (ellipse.getRadiusX()+ellipse.getCenterX())/canvas.getWidth());
            String radiusY = String.format("%.2f", (ellipse.getRadiusY()+ellipse.getCenterY())/canvas.getWidth());

            //TODO: FIX ELLIPSE BOUNDARIES

//            // If X1 reaches beyond left border
//            if (ellipse.getCenterX()<0) {
//                centreX = "0.0";
//            }
//
//            // If X2 reaches beyond right border
//            if ((ellipse.getRadiusX()+ellipse.getCenterX()) > 1) {
//                radiusX = "1.0";
//            }
//
//            // If Y1 reaches beyond left border
//            if (ellipse.getCenterY()<0) {
//                centreY = "0.0";
//            }
//
//            // If Y2 reaches beyond right border
//            if ((ellipse.getRadiusY()+ellipse.getCenterY()) > 1) {
//                radiusY = "1.0";
//            }

            // Output ELLIPSE coordinates: X1,Y1,X2,Y2
            System.out.println("ELLIPSE " + centreX + " " + centreY + " " + radiusX + " " +radiusY);
        });
    }
}
