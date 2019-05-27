package userInterface;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.net.URL;

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
    @FXML private ColorPicker colorpicker; // Colour wheel
    @FXML private Canvas canvas;

    @FXML
    ToggleButton lineButton;
    @FXML
    ToggleButton plotButton;


    /**
     * Initliases the Print stream for the GUi console
     *
     * Initliasises Drawing Canvas and the tools
     *
     * TODO: implement other tools,  create spererate classes
     *
     * @Author Waldo Fouche, n9950095
     */
    public void initialize(){
        ps = new PrintStream(new Console(console)) ;
        System.setOut(ps); // sets the console output to gui display
        System.setErr(ps); // Sets the error output to gui display
    }

    @FXML
    public void handleLineButton(ActionEvent ActionEvent) {

        if (lineButton.isSelected()){
            System.out.println("LINE ON");

            canvas.setOnMouseDragged( e -> {
                double size = 10.00;
                double x = e.getX();
                double y = e.getY();

                canvas.getGraphicsContext2D().setFill(colorpicker.getValue());
                canvas.getGraphicsContext2D().fillRoundRect(x,y,size,size,size,size);
            });

        } else {
            System.out.println("LINE OFF");

            canvas.setOnMouseDragged(null);
        }
    }

    /**
     * @Author Kevin Duong
     * Plot Button Function
     * @param actionEvent
     */
    @FXML
    public void handlePlotButton(ActionEvent actionEvent) {

        if (plotButton.isSelected()){
            System.out.println("PLOT ON");

            // PEN update colour
            colorpicker.setOnMouseClicked( e -> {

                //TODO: Convert colour value to Web colour
                System.out.println("PEN "+ colorpicker.getValue());

            });

            canvas.setOnMouseClicked( e -> {
                double size = 5.00;
                double x = e.getX();
                double y = e.getY();

                canvas.getGraphicsContext2D().setFill(colorpicker.getValue());
                canvas.getGraphicsContext2D().fillRoundRect(x,y,size,size,size,size);

                // PLOT Output
                System.out.println("PLOT " + x + " " + y);
            });

        } else {
            System.out.println("PLOT OFF");

            canvas.setOnMouseClicked(null);
        }
    }


    /**
     * Streams the text being sent from the console to the GUI console display
     *
     * @author Waldo Fouche, n9950095
     **/
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


    /**
     * @author Kevin Duong, n9934731
     * clickFileNew - Multi-image support. When creating new image, it loads a new image in a separate window
     * @param actionEvent
     */
    @FXML
    public void clickFileNew(ActionEvent actionEvent) {
        try {
            // Launch new window
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ui_layout.fxml"));

            // Implement stylesheet
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            scene.getStylesheets().add("userInterface/stylesheet.css");
            scene.getStylesheets().add("userInterface/menuBarStylesheet.css");

            Stage stage = new Stage();
            stage.setTitle("Vec Draw - Untitled");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }


    /**
     * @author Kevin Duong, n9934731
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

        /*
            If the user successfully finds a file, then a new window will open with the selected image
            Otherwise if the user cancels or exits the dialog, do not open a new window
         */

        // File directory address and opening dialog
        File file_path = chooser.showOpenDialog(null);

        if (file_path != null) {

            try {
                // Gets only filename
                String filename = new File(file_path.toString()).getName();

                // Launch new window
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ui_layout.fxml"));

                // Implement stylesheet
                Scene scene = new Scene(fxmlLoader.load(), 900, 600);
                scene.getStylesheets().add("userInterface/stylesheet.css");
                scene.getStylesheets().add("userInterface/menuBarStylesheet.css");

                Stage stage = new Stage();
                stage.setTitle("Vec Draw - " + filename);
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }

            // Reading file...
            try {
                FileReader fr = new FileReader(file_path);
                BufferedReader br = new BufferedReader(fr);

                String str;
                while ((str = br.readLine()) != null) {
                    System.out.println(str);
                }

                br.close();
            } catch (IOException e) {
                System.out.println("File not found");
            }
        }
    }

    /**
     * @author Kevin Duong, n9934731
     * File is saved, by updating the image code to the .vec file.
     * @param actionEvent
     */
    public void clickFileSave(ActionEvent actionEvent) throws IOException {
        //TODO: Need to find a way to grab an existing file's name so I can get its directory path and save it there. Also make it a save as when it is a new file
    }

    /**
     * @author Kevin Duong, n9934731
     * Image is saved as a new .vec file with a name and directory location. File contains image code from drawing.
     *
     * @param actionEvent
     */
    @FXML
    public void clickFileSaveAs(ActionEvent actionEvent) throws IOException {

        FileChooser chooser = new FileChooser();

        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("VEC Files", "*.vec")
        );

        File save_path = chooser.showSaveDialog(null);

        if (save_path != null) {

            // Information from text area
            String fileContent = console.getText();

            FileWriter fileWriter = new FileWriter(save_path);
            fileWriter.write(fileContent);
            fileWriter.close();
        }
    }


    /**
     * Closes the program from File -> Close when clicked.
     * @param actionEvent
     */
    public void clickFileClose(ActionEvent actionEvent) {
        Platform.exit();
    }
}


