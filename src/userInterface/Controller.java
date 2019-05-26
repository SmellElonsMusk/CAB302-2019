package userInterface;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    /**
     * Line function tool - activates when toggled.
     * @param mouseEvent
     */

    public void pressLineFunction(MouseEvent mouseEvent) {

    }


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
     * clickFileNew - Multi-image support. When creating new image, it loads a new image in a separate window
     * @param actionEvent
     */
    @FXML
    public void clickFileNew(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ui_layout.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            scene.getStylesheets().add("userInterface/stylesheet.css");
            scene.getStylesheets().add("userInterface/menuBarStylesheet.css");

            Stage stage = new Stage();
            stage.setTitle("Vec Draw");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
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
                new FileChooser.ExtensionFilter("VEC Files", "*.vec")
        );

        // Show file Dialog
        File file_path = chooser.showOpenDialog(new Stage());

        // File directory address
        System.out.println(file_path);

        // Gets only filename
        String filename = new File(file_path.toString()).getName();
        System.out.println(filename);

        // Reading file...
        try {
            FileReader fr = new FileReader(file_path);
            BufferedReader br = new BufferedReader(fr);

            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str + "\n");
            }

            br.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }


        /*
            If the user successfully finds a file, then a new window will open with the selected image
            Otherwise if the user cancels or exits the dialog, do not open a new window
         */


    }

    /**
     * Closes the program from File -> Close when clicked.
     * @param actionEvent
     */
    public void clickFileClose(ActionEvent actionEvent) {
        Platform.exit();
    }
}


