package backend;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * POLYGON Shape Drawing Functionality
 */

public class DrawPolygon extends Tool {

    private Polygon polygon;

    List x = new ArrayList();
    List y = new ArrayList();

    List xy = new ArrayList();

    int count;

    public DrawPolygon(Canvas canvas, ToggleButton fillButton, ColorPicker colorPicker) {
        super(canvas, colorPicker);

        canvas.setOnMousePressed(e -> {

            if (e.getButton() == MouseButton.PRIMARY) {
                if (!fillButton.isSelected()) {
                    canvas.getGraphicsContext2D().setStroke(colorPicker.getValue());
                }

                if (fillButton.isSelected()) {
                    canvas.getGraphicsContext2D().setFill(colorPicker.getValue());
                }

                this.x.add(e.getX());
                this.y.add(e.getY());
            } else {
                polygon = null;
            }
        });

        canvas.setOnMouseReleased(e -> {

            count = x.size();

            double[] dx = new double[count];
            double[] dy = new double[count];

            for (int i = 0; i < x.size(); i++) {

                dx[i] = (double) x.get(i);
                dy[i] = (double) y.get(i);

                xy.add(dx[i]);
                xy.add(dy[i]);
            }

            if (fillButton.isSelected()) {
                canvas.getGraphicsContext2D().setFill(colorPicker.getValue());
                canvas.getGraphicsContext2D().fillPolygon(dx, dy, count);
                canvas.getGraphicsContext2D().strokePolygon(dx, dy, count);
            } else {
                // Use colour to hide lines internally
                canvas.getGraphicsContext2D().setFill(Color.valueOf("#f4f4f4"));
                canvas.getGraphicsContext2D().fillPolygon(dx, dy, count);
                canvas.getGraphicsContext2D().strokePolygon(dx, dy, count);
            }

   /*
       Converting coordinates to normal values
    */

            // Remove brackets and commas
            String coordinates = xy.toString().replace("[", "").replace("]", "").replace(",", "");

            // Split coordinates up
            String[] splitted = coordinates.split("\\s+");

            // Convert to doubles for further conversion
            double[] doubleValues = Arrays.stream(splitted).mapToDouble(Double::parseDouble).toArray();

            //Square Ratio 1:1
            double[] normal = new double[doubleValues.length];
            for (int a = 0; a < doubleValues.length; a++) {
                normal[a] = doubleValues[a] / canvas.getWidth();
            }

            String final_coordinates = Arrays.toString(normal).replace("[", "").replace("]", "").replace(",", "");

            // OUTPUT coordinates: X(n), (Yn)
            System.out.println("POLYGON " + final_coordinates);

        });

    }
}