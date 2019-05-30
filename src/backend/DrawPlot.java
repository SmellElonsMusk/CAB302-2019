package backend;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;

public class DrawPlot extends Tool {

    public DrawPlot(Canvas canvas, ColorPicker colorPicker) {
        super(canvas, colorPicker);

        canvas.setOnMouseClicked( e -> {

            canvas.getGraphicsContext2D().setFill(colorPicker.getValue());
            canvas.getGraphicsContext2D().fillRoundRect(e.getX(),e.getY(),5,5,5,5);

            // PLOT Output
            System.out.println("PLOT " + e.getX() + " " + e.getY());
        });

    }
}
