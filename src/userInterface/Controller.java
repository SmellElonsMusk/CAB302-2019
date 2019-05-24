package userInterface;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

import backend.*;

import javax.xml.catalog.Catalog;

/**
 *
 * TODO: Fix Canvas Drawing
 *
 * @Author: Waldo Fouche, Kevin Doung
 */
public class Controller {


    @FXML private TextArea console; // Console on GUI display
    @FXML private PrintStream ps; // Streams to console on GUI
    @FXML private Canvas canvas;
    @FXML private GraphicsContext gc;




    // Streams the text being sent from the console to the GUI console display
    public class Console extends OutputStream {
        private TextArea console;

        public Console(TextArea console) {
            this.console = console;
        }

        public void appendText(String valueOf) {
            Platform.runLater(() -> console.appendText(valueOf));
        }

        public void write(int b) throws IOException {
            appendText(String.valueOf((char)b));
        }
    }

    // Initializes the console stream
    public void initialize(){
        ps = new PrintStream(new Console(console)) ;

        System.setOut(ps); // sets the console output to gui display
        System.setErr(ps); // Sets the error output to gui display
    }

    /**
     *
     * @param actionEvent
     */
    @FXML
    public void clickFileNew(ActionEvent actionEvent) {
        System.out.println("Clicked: New");
    }

    /**
     * Event handler for click action on the File -> open menu item
     * @param actionEvent
     *
     * @Author
     */
    @FXML
    protected void clickFileOpen(ActionEvent actionEvent) throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg")
                ,new FileChooser.ExtensionFilter("VEC Files", "*.vec")
        );

        File file = chooser.showOpenDialog(new Stage());
        System.out.println("Clicked: Open");


        // Open selected file:

        try {
            loadFromFile loadedFile = new loadFromFile("file");
            loadedFile.toString(); // Converts text to string
        }
        catch(Exception e) {}
    }

    /**
     * Closes the program from File -> Close when clicked.
     * @param actionEvent
     */
    public void clickFileClose(ActionEvent actionEvent) {
        Platform.exit();
    }
}


