package backend;

import java.io.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Loads Text from VEC file, Processes for Tool Commands, parses the data to a Regex command that
 * extracts all necessary coordinates, Then pushes the coordinates to drawing commands and displays
 * on the current window
 *
 * TODO: Add ability to recognise Tool Commands (LINE, RECTANGLE, ETC) and draw them seperately - Working
 * TODO: Scan each line of the file and grab the Tool command with its corresponding coords - Working
 *
 * @Author Waldo Fouche, n995095
 */
public class DrawFromFile {

    private ArrayList<String> line;
    private ArrayList<Double> myDoubles;

    private String hex[];

    private Matcher doubles;

    String regexDoubles = "[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?";

    public DrawFromFile(Canvas canvas, File file) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String str;
            this.line = new ArrayList<String>();
            while ((str = br.readLine()) != null) {
                this.line.add(str);

                // Check for colour
                if (str.contains("FILL") || str.contains("PEN")) {
                    hex = str.split("\\s+");
                }

                // TODO: Detect FILL OFF
                // If FILL is OFF
                if (str.contains("OFF")) {
                    //hex[1] = "#000000";
                }

                // Check for drawing commands
                if (str.contains("LINE") ) {
                    // Scans for Doubles
                    myDoubles = new ArrayList<Double>();
                    doubles = Pattern.compile(regexDoubles).matcher(str);

                    while (doubles.find()) {
                        double element = Double.parseDouble(doubles.group());
                        myDoubles.add(element);
                    }

                    Double x1 = myDoubles.get(0) * canvas.getWidth();
                    Double y1 = myDoubles.get(1) * canvas.getHeight();
                    Double x2 = myDoubles.get(2) * canvas.getWidth();
                    Double y2 = myDoubles.get(3) * canvas.getHeight();

                    canvas.getGraphicsContext2D().strokeLine(x1,y1,x2,y2);

                } else if (str.contains("PLOT")) {

                    // Scans for Doubles
                    myDoubles = new ArrayList<Double>();
                    doubles = Pattern.compile(regexDoubles).matcher(str);

                    while (doubles.find()) {
                        double element = Double.parseDouble(doubles.group());
                        myDoubles.add(element);
                    }

                    Double x1 = myDoubles.get(0)*canvas.getWidth();
                    Double y1 = myDoubles.get(1)*canvas.getWidth();

                    canvas.getGraphicsContext2D().setFill(Color.valueOf(hex[1]));
                    canvas.getGraphicsContext2D().fillRoundRect(x1,y1,1,1,1,1);
                  
                } else if (str.contains("RECTANGLE")) {

                    // Scans for Doubles
                    myDoubles = new ArrayList<Double>();
                    doubles = Pattern.compile(regexDoubles).matcher(str);

                    while (doubles.find()) {
                        double element = Double.parseDouble(doubles.group());
                        myDoubles.add(element);
                    }

                    Double x1 = myDoubles.get(0)*canvas.getWidth();
                    Double y1 = myDoubles.get(1)*canvas.getWidth();
                    Double x2 = (myDoubles.get(2))*canvas.getWidth();
                    Double y2 = (myDoubles.get(3)*canvas.getWidth());

                    canvas.getGraphicsContext2D().setFill(Color.valueOf(hex[1]));
                    canvas.getGraphicsContext2D().setStroke(Color.valueOf(hex[1]));

                    canvas.getGraphicsContext2D().fillRect(x1,y1,(x2-x1),(y2-y1));
                    canvas.getGraphicsContext2D().strokeRect(x1,y1,(x2-x1),(y2-y1));

                    myDoubles.clear();

                } else if (str.contains("ELLIPSE")) {

                    // Scans for Doubles
                    myDoubles = new ArrayList<Double>();
                    doubles = Pattern.compile(regexDoubles).matcher(str);

                    while (doubles.find()) {
                        double element = Double.parseDouble(doubles.group());
                        myDoubles.add(element);
                    }

                    Double x1 = myDoubles.get(0) * canvas.getWidth();
                    Double y1 = myDoubles.get(1) * canvas.getHeight();
                    Double x2 = myDoubles.get(2) * canvas.getWidth();
                    Double y2 = myDoubles.get(3) * canvas.getHeight();

                    canvas.getGraphicsContext2D().setFill(Color.valueOf(hex[1]));
                    canvas.getGraphicsContext2D().setStroke(Color.valueOf(hex[1]));

                    canvas.getGraphicsContext2D().fillOval(x1,y1,x2-x1,y2-y1);
                    canvas.getGraphicsContext2D().strokeOval(x1,y1,x2-x1,y2-y1);

                } else if (str.contains("POLYGON")) {
                    //System.out.println("I am a poly");
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
