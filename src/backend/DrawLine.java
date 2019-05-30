package backend;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.shape.Line;

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
