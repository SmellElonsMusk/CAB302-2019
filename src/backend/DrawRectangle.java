package backend;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.shape.Rectangle;

/**
 * RECTANGLE SHAPE FUNCTIONALITY
 *
 * @author Waldo Fouche, n9950095 (OOP)
 * @author Kevin Duong, n9934731 (Function)
 *
 **/

public class DrawRectangle extends Tool{

    private Rectangle rectangle;

    public DrawRectangle(Canvas canvas, ToggleButton fillButton, ColorPicker colorPicker) {
        super(canvas);

        this.rectangle = new Rectangle();

        canvas.setOnMousePressed(e -> {
            if (!fillButton.isSelected()) {
                canvas.getGraphicsContext2D().setStroke(colorPicker.getValue());
            }

            if  (fillButton.isSelected()) {
                canvas.getGraphicsContext2D().setFill(colorPicker.getValue());
            }
            rectangle.setX(e.getX());
            rectangle.setY(e.getY());
        });

        canvas.setOnMouseDragged(e -> {
            //TODO: Show realtime Rectangle drag when in process of creating Rectangle
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

            if (fillButton.isSelected()){
                canvas.getGraphicsContext2D().fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight()); // Creates filled in shape
                canvas.getGraphicsContext2D().strokeRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight()); // Creates outline of shape
            } else {
                canvas.getGraphicsContext2D().strokeRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
            }

//            // Square Ratio 1:1
//            String startX = String.format("%.2f", rectangle.getX()/canvas.getWidth());
//            String startY = String.format("%.2f", rectangle.getY()/canvas.getWidth());
//            String endX = String.format("%.2f", (rectangle.getWidth()+rectangle.getX())/canvas.getWidth());
//            String endY = String.format("%.2f",(rectangle.getHeight()+rectangle.getY())/canvas.getWidth());

//            // If END of width (X1) reaches beyond left border
//            if (rectangle.getX()/canvas.getWidth() < 0) {
//                startX = "0.0";
//            }
//
//            // If END of width (X2) reaches beyond right border
//            if ((rectangle.getWidth()+rectangle.getX())/canvas.getWidth() > 1) {
//                endX = "1.0";
//            }
//
//            // IF Y1 reaches above the top border
//            if (rectangle.getY()/canvas.getWidth() < 0) {
//                startY = "0.0";
//            }
//
//            // IF Y2 reaches below the bottom border
//            if ((rectangle.getHeight()+rectangle.getY())/canvas.getWidth() > 1) {
//                endY = "1.0";
//            }

            // Output RECTANGLE coordinates: X1,Y1,X2,Y2
            //System.out.println("RECTANGLE " + startX + " " + startY + " " + endX + " " + endY);
            System.out.println("RECTANGLE " + rectangle.getX()/canvas.getWidth() + " " + rectangle.getY()/canvas.getWidth() + " " + (rectangle.getWidth()+rectangle.getX())/canvas.getWidth() + " " + (rectangle.getHeight()+rectangle.getY())/canvas.getWidth());
        });
    }
}
