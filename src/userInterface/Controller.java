package userInterface;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
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

    @FXML ToggleButton lineButton;
    @FXML ToggleButton plotButton;
    @FXML ToggleButton rectangleButton;
    @FXML ToggleButton ellipseButton;
    @FXML ToggleButton polygonButton;
    @FXML ToggleButton fillButton;

    // Variables for Tools
    private double xInit;
    private double yInit;
    Rectangle newRect = null;
    Group rectGroup = new Group() ;

    boolean newRectBeingDrawn = false;



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

    /**
     *
     * @Author Waldo Fouche, n9950095
     */
    @FXML
    public void handleLineButton(ActionEvent ActionEvent) {

        if (lineButton.isSelected()){
            System.out.println("LINE ON");

            // Disable other buttons
            plotButton.setDisable(true);
            rectangleButton.setDisable(true);
            ellipseButton.setDisable(true);
            polygonButton.setDisable(true);

            // LINE does not use FILL
            fillButton.setDisable(true);

            canvas.setOnMouseDragged( e -> {
                double size = 10.00;
                double x = e.getX();
                double y = e.getY();

                canvas.getGraphicsContext2D().setFill(colorpicker.getValue());
                canvas.getGraphicsContext2D().fillRoundRect(x,y,size,size,size,size);
            });

        } else {
            System.out.println("LINE OFF");

            // Deactivate function
            canvas.setOnMouseDragged(null);

            // Restore buttons
            plotButton.setDisable(false);
            rectangleButton.setDisable(false);
            ellipseButton.setDisable(false);
            polygonButton.setDisable(false);
            fillButton.setDisable(false);
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


            // Disable other buttons
            lineButton.setDisable(true);
            rectangleButton.setDisable(true);
            ellipseButton.setDisable(true);
            polygonButton.setDisable(true);

            // PLOT does not use FILL
            fillButton.setDisable(true);

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

            // Deactivate function
            canvas.setOnMouseClicked(null);

            // Restore buttons
            lineButton.setDisable(false);
            rectangleButton.setDisable(false);
            ellipseButton.setDisable(false);
            polygonButton.setDisable(false);
            fillButton.setDisable(false);
        }
    }

    /**
     * @Author Kevin Duong, n9934731
     * Updates the PEN colour
     */
    public void handlePenButton(ActionEvent event) {

        //TODO: (Optional) update pen history and replacing old pen colour
//        // If user changes colour in between before making a shape, the pen will update without deleting history
//        String array[] = console.getText().split("\n");
//        String textToSet = "";
//        for(int i=1; i<array.length; i++){
//            textToSet+=array[i-1] + "\n";
//        }
//
//        console.setText(textToSet);

        // Outputs chosen colour
        String hex = "#" + colorpicker.getValue().toString().toUpperCase().substring(2,8);

        // FILL Colour
        if (fillButton.isSelected()) {
            System.out.println("FILL "+ hex);
        }
        // PEN Colour
        else {
            // Outputs chosen colour
            System.out.println("PEN "+ hex);
        }

    }

    /**
     * @Author Kevin Duong, n9934731
     * FILL Button tool that fills colours inside shape functions
     */
    //TODO: clicking on fill button and colour to get the output FILL and colour RRGGBB. Disables LINE and PLOT
    public void handleFillButton(ActionEvent event) {

        if (fillButton.isSelected()) {

            // Disable LINE and PLOT as they're not hollow
            lineButton.setDisable(true);
            plotButton.setDisable(true);

        } else {
            // Reopen buttons
            lineButton.setDisable(false);
            plotButton.setDisable(false);
            System.out.println("FILL OFF");
        }
    }


    /**
     *
     * Takes initial x,y coridinates and final x,y coridnates and creates a rectangle between the points
     *
     * @param xInit  - Inital X coordinate
     * @param yInit - Inital Y coordinate
     * @param xFinal  Final  X coordinate
     * @param yFinal - Final y coordinate
     * @param newRect - New Rectangle
     *
     * @Author Waldo Fouche, n9950095
     */
    private void drawRect(double xInit, double yInit, double xFinal, double yFinal, Rectangle newRect) {

        newRect.setX(xInit);
        newRect.setY(yInit);
        newRect.setWidth(xFinal - xInit);
        newRect.setWidth(yFinal - yInit);

        if (newRect.getWidth() < 0 ){
            newRect.setWidth(-newRect.getArcWidth());
            newRect.setX(newRect.getX() - newRect.getWidth());
        }

        if (newRect.getHeight() < 0) {
            newRect.setHeight(-newRect.getHeight());
            newRect.setY(newRect.getY() - newRect.getHeight());
        }
    }


    /**
     * Creates Rectangle given the specified Coordinates
     *
     * @Author Waldo Fouche, n9950095
     */
    public void handleRectangleButton(ActionEvent actionEvent) {

       if (rectangleButton.isSelected()) {
            System.out.println("RECTANGLE ON");

            // Disable all other buttons
            lineButton.setDisable(true);
            plotButton.setDisable(true);
            ellipseButton.setDisable(true);
            polygonButton.setDisable(true);

           canvas.getScene().setOnMousePressed (e -> {
                xInit = e.getSceneX();
                yInit = e.getSceneY();

                System.out.println("x: " + xInit +" y: " + yInit);

                newRect = new Rectangle();

                newRect.setFill( colorpicker.getValue() ) ; // almost white color
                newRect.setStroke( colorpicker.getValue() ) ;

                rectGroup.getChildren().add(newRect);

                newRectBeingDrawn = true;
            });

           canvas.getScene().setOnMouseDragged( e-> {
               System.out.println("Mouse Dragging: ");
                if (newRectBeingDrawn) {
                    double xFinal = e.getSceneX();
                    double yFinal = e.getSceneY();

                    System.out.println("X: "+xFinal+" y: "+ yFinal);

                    drawRect(xInit, yInit, xFinal, yFinal, newRect);
                }
            });


           canvas.getScene().setOnMouseReleased( e-> {
               System.out.println("Mouse Drag Stopped");
                if (newRectBeingDrawn) {
                    newRect.setFill(colorpicker.getValue());
                    newRect = null;
                    newRectBeingDrawn = false;
                }

            });

        } else {
            System.out.println("RECTANGLE OFF");

            // Re - enable all other buttons
            lineButton.setDisable(false);
            plotButton.setDisable(false);
            ellipseButton.setDisable(false);
            polygonButton.setDisable(false);
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


