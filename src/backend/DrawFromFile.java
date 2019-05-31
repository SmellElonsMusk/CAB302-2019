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

    private  ArrayList<String> line;
    private ArrayList<Double> myDoubles;


    private Matcher strings;
    private Matcher doubles;

    String regexDoubles = "[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?";
    String regexStrings = "[a-zA-Z]+";

    Color stroke;
    Color fill;

    Boolean isFill = false;



    public DrawFromFile(Canvas canvas, File file, Color s, Color f) {

        this.stroke = s;
        this.fill = f;

        canvas.getGraphicsContext2D().setStroke(stroke);
        canvas.getGraphicsContext2D().setFill(fill);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String str;
            this.line = new ArrayList<String>();
            while ((str = br.readLine()) != null) {
                this.line.add(str);

//                // Scans for Commands, LINE, RECTANGLE, ETC
//                strings = Pattern.compile(regexStrings).matcher(str);
//                while (strings.find()) {
//                    String element = strings.group();
//                    System.out.println(element);


                // Check for colour
                    //TODO Gab the pen and fill commands, set colour as specified:
                if (str.contains("FILL")) {
                    //this.fill =

                    this.isFill = true;

                    System.out.println("I am a Fill command: ");
                } else  {
                    this.isFill = false;
                }

                if (str.contains("PEN")) {
                    //this.stroke =

                    System.out.println("I am a Stroke command: ");
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

                    if (isFill) {

                    } else {
                        canvas.getGraphicsContext2D().strokeLine(x1,y1,x2,y2);
                    }

                    System.out.println("I am a line");

                } else if (str.contains("PLOT")) {
                  System.out.println("I am a plot");
                  
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

                    if (isFill) {

                    } else {
                        canvas.getGraphicsContext2D().strokeRect(x1,y1,(x2-x1),(y2-y1));
                    }


                    myDoubles.clear();

                    //System.out.println("I am a rec");

                } else if (str.contains("ELLIPSE")) {

                    // Scans for Doubles
                    myDoubles = new ArrayList<Double>();
                    doubles = Pattern.compile(regexDoubles).matcher(str);

                    while (doubles.find()) {
                        double element = Double.parseDouble(doubles.group());
                        myDoubles.add(element);
                    }

                    //RECTANGLE 0.10 0.08 0.60 0.48

                    Double x1 = myDoubles.get(0) * canvas.getWidth();
                    Double y1 = myDoubles.get(1) * canvas.getHeight();
                    Double x2 = myDoubles.get(2) * canvas.getWidth();
                    Double y2 = myDoubles.get(3) * canvas.getHeight();

                    if (isFill) {
                        canvas.getGraphicsContext2D().setFill(fill);
                        canvas.getGraphicsContext2D().fillOval(x1,y1,x2-x1,y2-y1);
                        canvas.getGraphicsContext2D().strokeOval(x1,y1,x2-x1,y2-y1);

                    } else {
                        canvas.getGraphicsContext2D().strokeOval(x1,y1,x2-x1,y2-y1);

                    }


                    System.out.println("I am an ellipse");

                } else if (str.contains("POLYGON")) {
                    System.out.println("I am a poly");
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
