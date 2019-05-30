package backend;

import java.io.*;
import javafx.scene.canvas.Canvas;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Loads Text from VEC file, Processes for Tool Commands, parses the data to a Regex command that
 * extracts all necessary coordinates, Then pushes the coordinates to drawing commands and displays
 * on the current window
 *
 * TODO: Add ability to recognise Tool Commands (LINE, RECTANGLE, ETC) and draw them seperately
 * TODO: Scan each line of the file and grab the Tool command with its corresponding coords
 *
 * @Author Waldo Fouche, n995095
 */
public class DrawFromFile {

    private  ArrayList<String> line;
    private ArrayList<Double> myDoubles;

    private Matcher strings;
    private Matcher doubles;

    String regexDoubles = "[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?";
    String regexStrings = "[a-zA-Z]";

    public DrawFromFile(Canvas canvas, File file) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String str;
            line = new ArrayList<String>();
            while ((str = br.readLine()) != null) {
                this.line.add(str);

                this.myDoubles = new ArrayList<Double>();
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
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
