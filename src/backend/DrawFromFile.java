package backend;

import java.io.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.ArrayList;

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
    public DrawFromFile(Canvas canvas, File file) {

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String str;
            this.line = new ArrayList<String>();
            while ((str = br.readLine()) != null) {
                this.line.add(str);
                Draw draw = new Draw(canvas, str); // Draw shapes
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
