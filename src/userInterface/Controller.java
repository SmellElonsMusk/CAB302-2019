/**
 * CAB302 - ALIGNMENT 02 - VEC DRAW
 * The program allows the user to draw various shapes using tools such as:
 * LINE, PLOT, RECTANGLE, ELLIPSE, POLYGON.
 *
 * Each of the group members have added in a comment and stated their contribution:
 *
 * @Author Waldo Fouche, n9950095
 * @Author Kevin Doung,
 *
 */


package userInterface;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import backend.*;

/**
 * CONTROLLER FOR JAVAFX FXML
 * @author: Waldo Fouche, Kevin Duong
 */
public class Controller implements Initializable {

    //*********************************************************/

    // JAVAFX ID's
    @FXML private Canvas canvas;
    @FXML private TextArea console; // Console on GUI display
    @FXML private ColorPicker colorpicker; // Colour Palette

    @FXML BorderPane borderPane;

    // JAVAFX Button's
    @FXML ToggleButton lineButton;
    @FXML ToggleButton plotButton;
    @FXML ToggleButton rectangleButton;
    @FXML ToggleButton ellipseButton;
    @FXML ToggleButton polygonButton;
    @FXML ToggleButton fillButton;
    @FXML Button undoButton;

    // GLOBAL VARIABLES TO STORE COLOUR
    Color fillColour;
    Color strokeColour;

    // FILE MANAGEMENT
    private File openFile;
    private File currentFile;


    //*******************************************************/

    /**
     * Initliases the Print stream for the GUi console
     *
     * Initliasises Drawing Canvas and the tools
     *
     * TODO: implement other tools,  create spererate classes
     *
     * @Author Waldo Fouche, n9950095
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        canvas.getGraphicsContext2D();

        borderPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> {
            canvas.setWidth(newValue.doubleValue()-350);
            //gc.setFill(Color.RED);
            //gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });

        borderPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> {
            canvas.setHeight(newValue.doubleValue()-100);
            //gc.setFill(Color.GREEN);
            //gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });

        ConsoleGUI gui = new ConsoleGUI(console);
    }

    /**
     * Creates a new window and sets the title based on the filename
     *
     * @Author Kevin Duong , Waldo Fouche, n9950095
     * @param filename
     */
    public void newWindow (String filename) {
        if (filename == null) {
            filename = "Untitled";
        }
        try {
            // Launch new window
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ui_layout.fxml"));

            // Implement stylesheet
            Scene scene = new Scene(fxmlLoader.load(), 950, 700);
            scene.getStylesheets().add("userInterface/stylesheet.css");
            scene.getStylesheets().add("userInterface/menuBarStylesheet.css");

            Stage stage = new Stage();
            stage.setTitle("VEC Paint - " + filename);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    /**
     * Deactivates the drawing function
     *
     * @Author Waldo Fouche
     */
    public void deActivateDrawing() {
        canvas.setOnMousePressed(null);
        canvas.setOnMouseDragged(null);
        canvas.setOnMouseReleased(null);
        canvas.setOnMouseClicked(null);
    }

    /**
     * LINE TOOL FUNCTION
     * @Author Waldo Fouche, n9950095
     */
    @FXML
    public void handleLineButton(ActionEvent ActionEvent) {
        if (lineButton.isSelected()){
            DisableButtons disable = new DisableButtons(lineButton,plotButton,rectangleButton,ellipseButton,polygonButton);
            // LINE does not use FILL
            fillButton.setDisable(true);
            DrawLine newLine = new DrawLine(canvas,colorpicker);
        } else {
            deActivateDrawing();
            ReEnableButtons reEnableButtons = new ReEnableButtons(lineButton,plotButton,rectangleButton,ellipseButton,polygonButton);
            fillButton.setDisable(false);
        }
    }

    /**
     * PLOT TOOL FUNCTION
     * @Author Kevin Duong
     * @param actionEvent
     */
    @FXML
    public void handlePlotButton(ActionEvent actionEvent) {
        if (plotButton.isSelected()){
            DisableButtons disable = new DisableButtons(plotButton,lineButton,rectangleButton,ellipseButton,polygonButton);
            // PLOT does not use FILL
            fillButton.setDisable(true);
            DrawPlot newPlot = new DrawPlot(canvas, colorpicker);
        } else {
            deActivateDrawing();
            ReEnableButtons reEnableButtons = new ReEnableButtons(plotButton,lineButton,rectangleButton,ellipseButton,polygonButton);
            fillButton.setDisable(false);
        }
    }

    /**
     * Creates DrawRectangle given the specified Coordinates
     *
     * @Author Waldo Fouche, n9950095
     * @Author Kevin Duong
     */
    public void handleRectangleButton(ActionEvent actionEvent) {
        if (rectangleButton.isSelected()) {
            DisableButtons disableButtons = new DisableButtons(rectangleButton,lineButton,plotButton,ellipseButton,polygonButton);
            DrawRectangle newRectangle = new DrawRectangle(canvas,fillButton,colorpicker);

//            undoButton.setOnMouseClicked(e-> {
//                this.coordinates = newRectangle.returnCoords();
//                for (int i=0;  i< this.coordinates.size(); i++) {
//                    System.out.println(this.coordinates.get(i));
//                }
//            });


        } else {
            deActivateDrawing();
            ReEnableButtons reEnableButtons = new ReEnableButtons(rectangleButton,lineButton,plotButton,ellipseButton,polygonButton);
        }
    }

    /**
     *  ELLIPSE TOOL FUNCTION
     * @Author Kevin Duong, n9934731
     * ELLIPSE function.
     */
    public void handleEllipseButton(ActionEvent event) {
        if (ellipseButton.isSelected()) {
            DisableButtons disableButtons = new DisableButtons(ellipseButton,lineButton,plotButton,rectangleButton,polygonButton);
            DrawEllipse newEllipse = new DrawEllipse(canvas,fillButton,colorpicker);
        } else {
            deActivateDrawing();
            ReEnableButtons reEnableButtons = new ReEnableButtons(ellipseButton,lineButton,plotButton,rectangleButton,polygonButton);
        }
    }

    /**
     * POLYGON FUNCTION
     *
     * @Author Kevin Duong, n9934731
     * POLYGON function.
     */
    public void handlePolygonButton(ActionEvent event) {
        if (polygonButton.isSelected()){
            DisableButtons disableButtons = new DisableButtons(polygonButton,lineButton,rectangleButton,plotButton,ellipseButton);
            DrawPolygon newPolygon = new DrawPolygon(canvas,fillButton,colorpicker);
        } else {
            deActivateDrawing();
            ReEnableButtons reEnableButtons = new ReEnableButtons(polygonButton,lineButton,rectangleButton,plotButton,ellipseButton);
        }
    }

    /**
     * UPDATES PEN COLOUR
     * @Author Kevin Duong, n9934731
     *
     */
    public void handlePenButton(ActionEvent event) {

        //TODO: (Optional) update pen history and replacing old pen colour

        // Outputs chosen colour
        String hex = "#" + colorpicker.getValue().toString().toUpperCase().substring(2,8);

        // FILL Colour
        if (fillButton.isSelected()) {
            fillColour = colorpicker.getValue(); // Saves the selected fill colour
            System.out.println("FILL "+ hex);
        }
        // PEN Colour
        else {
            // Outputs chosen colour
            strokeColour = colorpicker.getValue(); // Saves the selected pen colour
            System.out.println("PEN "+ hex);
        }
    }

    /**
     * FILL Button tool that fills colours inside shape functions
     * @Author Kevin Duong, n9934731
     *
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
     * Undo Button - removes one step at a time
     * @Author Kevin Duong, n9934731
     *
     * @param event
     */
    public void handleUndoButton(ActionEvent event) {

        // TODO: Problem #1: You have to click twice to undo at first? no idea why but must be fixed

        // TODO: Implement CTRL + Z

        String array[] = console.getText().split("\n");
        String textToSet = "";
        int history;
        for(history = 1; history <array.length; history++) {
            textToSet += array[history-1] + "\n";
        }

        if(array[history-1] != null) {
            canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());

            for (int i = 0; i < array.length; i++){
                Draw draw = new Draw(canvas, array[i]);
                console.setText(textToSet);
            }
        }
    }

    /**
     * clickFileNew - Multi-image support. When creating new image, it loads a new image in a separate window
     * @author Kevin Duong, n9934731
     *
     * @param actionEvent
     */
    @FXML
    public void clickFileNew(ActionEvent actionEvent) {
        newWindow(null);
    }

    /**
     *  Event handler for click action on the File -> open menu item
     * @author Kevin Duong, n9934731
     *
     * @param actionEvent
     *
     * @Author
     */
    @FXML
    protected void clickFileOpen(ActionEvent actionEvent) throws IOException {
        fileChooser fc = new fileChooser();
        fc.Open();
        if (fc.getFile() != null) {
            String filename = fc.getFileName();
            //newWindow(filename);// TODO: Fix drawing open in new window not on previous
            fileReader read = new fileReader(fc.getFile());

            //TODO: Attempting to load image based on code
            DrawFromFile newDraw = new DrawFromFile (canvas,fc.getFile());

        }

    }

    /**
     * File is saved, by updating the image code to the .vec file.
     * @author Kevin Duong, n9934731 Waldo Fouche, n9950095
     *
     * @param actionEvent
     */
    public void clickFileSave(ActionEvent actionEvent) throws IOException {
        //TODO: Need to find a way to grab an existing file's name so I can get its directory path and save it there. Also make it a save as when it is a new file
        StringBuilder sb = new StringBuilder();
        String newContent;
//        try {
//            Save newSave = new Save(openFile, console);
//        } catch (IOException e) {
//            System.out.println("File Save Error");
//        }

        if (openFile == null ) {
            fileChooser fc = new fileChooser();
            fc.Save();

            openFile = fc.getFile().getAbsoluteFile();
            newContent = console.getText();
            sb.append(newContent);
        }

        else {
            newContent = console.getText();
            sb.append(newContent);
        }

        FileWriter fileWriter = new FileWriter(openFile);
        fileWriter.write(sb.toString());
        fileWriter.close();


    }

    /**
     * Image is saved as a new .vec file with a name and directory location. File contains image code from drawing.
     * @author Kevin Duong, n9934731
     * @param actionEvent
     */
    @FXML
    public void clickFileSaveAs(ActionEvent actionEvent) throws IOException {

        fileChooser fc = new fileChooser();
        fc.Save();
        Save save = new Save(fc.getFile(),console);
        save.As();

        //newWindow(fc.getFileName());
    }

    /**
     * Closes the program from File -> Close when clicked.
     * @Author Waldo Fouche
     */
    public void clickFileClose(ActionEvent actionEvent) {
        Platform.exit();
    }
}


