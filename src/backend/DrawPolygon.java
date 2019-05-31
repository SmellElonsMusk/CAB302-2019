package backend;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * POLYGON SHAPE FUNCTIONALITY
 *
 * @author Waldo Fouche, n9950095 (Function & OOP)
 * @author Kevin Duong, n9934731 (Function)
 *
 **/

public class DrawPolygon extends Tool {

    private Polygon polygon;

    private Matcher strings;
    private Matcher doubles;

    StringBuilder sb;


    private String regexDoubles = "[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?";
    private String regexStrings = "[a-zA-Z]+";

    List x = new ArrayList();
    List y = new ArrayList();

    List xy = new ArrayList();

    int count;


    public DrawPolygon(Canvas canvas, ToggleButton fillButton, ColorPicker colorPicker) {
        super(canvas, colorPicker);

        canvas.setOnMousePressed( e -> {

            if (e.getButton() == MouseButton.PRIMARY) {
                if (!fillButton.isSelected()) {
                    canvas.getGraphicsContext2D().setStroke(colorPicker.getValue());
                }

                if  (fillButton.isSelected()) {
                    canvas.getGraphicsContext2D().setFill(colorPicker.getValue());
                }

                this.x.add(e.getX());
                this.y.add(e.getY());
            } else {
                polygon = null;
            }
        });

        canvas.setOnMouseReleased(e->{

            count = (int)x.size();

            double[] dx =  new double[count];
            double[] dy =  new double[count];

            for (int i = 0; i < x.size(); i++) {


                dx[i] = (double) x.get(i);
                dy[i] = (double) y.get(i);

                xy.add(dx[i]);
                xy.add(dy[i]);

                //System.out.println(dx[i]);



            }

            if (fillButton.isSelected()) {
                canvas.getGraphicsContext2D().setFill(colorPicker.getValue());
                canvas.getGraphicsContext2D().fillPolygon(dx,dy,count);
                canvas.getGraphicsContext2D().strokePolygon(dx,dy,count);
            } else {
                canvas.getGraphicsContext2D().setFill(Color.valueOf("#f4f4f4"));
                canvas.getGraphicsContext2D().fillPolygon(dx,dy,count);
                canvas.getGraphicsContext2D().strokePolygon(dx,dy,count);
            }

            // TODO: Format output (remove commas and nrackets)
            // Output POLYGON coordinates


            doubles = Pattern.compile(regexDoubles).matcher(xy.toString());
            

            System.out.println("POLYGON " + sb);


        });

    }
}
