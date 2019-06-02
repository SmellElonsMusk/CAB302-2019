package userInterface;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import backend.*;

/**
 * CAB302 - ASSIGNMENT 02 - VEC PAINT
 * The program allows the user to draw various shapes using tools such as:
 * LINE, PLOT, RECTANGLE, ELLIPSE, POLYGON.
 *
 * Each of the group members have added in a comment and stated their contribution:
 *
 * @Author Waldo Fouche, n9950095
 * @Author Kevin Doung, n9934731
 */

public class Controller {

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
     * Initialises the Print stream for the GUI console
     * Resizable Drawing Canvas
     * Key event for undo
     */

    public void initialize() {
        canvas.getGraphicsContext2D();

        borderPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> {
            canvas.setWidth(newValue.doubleValue() - 350);
        });

        borderPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> {
            canvas.setHeight(newValue.doubleValue() - 100);
        });

        // Transfer Console into TextArea
        new ConsoleGUI(console);

        // CTRL + Z Listener for UNDO
        borderPane.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler < KeyEvent > () {
            final KeyCombination keyComb = new KeyCodeCombination(KeyCode.Z,
                    KeyCombination.CONTROL_DOWN);
            public void handle(KeyEvent ke) {
                if (keyComb.match(ke)) {
                    Undo();
                    ke.consume(); // <-- stops passing the event to next node
                }
            }
        });
    }

    /**
     * Creates a new window and sets the title based on the filename
     * @param filename name of .VEC file
     */
    public void newWindow(String filename) {
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
     */
    public void deActivateDrawing() {
        canvas.setOnMousePressed(null);
        canvas.setOnMouseDragged(null);
        canvas.setOnMouseReleased(null);
        canvas.setOnMouseClicked(null);
    }

    /**
     * Undo function - deletes last drawing
     */
    public void Undo() {
        String array[] = console.getText().split("\n");
        String textToSet = "";
        int history;
        for (history = 1; history < array.length; history++) {
            textToSet += array[history - 1] + "\n";
        }

        if (array[history - 1] != null) {
            canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            for (int i = 0; i < array.length; i++) {
                new Draw(canvas, array[i]);
                console.setText(textToSet);
            }
        }
    }

    /**
     * Line button trigger event
     * @param ActionEvent
     */
    @FXML
    public void handleLineButton(ActionEvent ActionEvent) {
        if (lineButton.isSelected()) {
            // Disable other buttons that interfere
            new DisableButtons(lineButton, plotButton, rectangleButton, ellipseButton, polygonButton);
            // LINE does not use FILL
            fillButton.setDisable(true);
            // Activate drawing method on canvas
            new DrawLine(canvas, colorpicker);
        } else {
            // De-activate drawing method on canvas
            deActivateDrawing();
            // Enable all buttons
            new ReEnableButtons(lineButton, plotButton, rectangleButton, ellipseButton, polygonButton);
            // Unlock Fill Button
            fillButton.setDisable(false);
        }
    }

    /**
     * Plot button trigger event
     * @param actionEvent
     */
    @FXML
    public void handlePlotButton(ActionEvent actionEvent) {
        if (plotButton.isSelected()) {
            // Disable other buttons that interfere
            new DisableButtons(plotButton, lineButton, rectangleButton, ellipseButton, polygonButton);
            // PLOT does not use FILL
            fillButton.setDisable(true);
            // Activate drawing method on canvas
            new DrawPlot(canvas, colorpicker);
        } else {
            // De-activate drawing method on canvas
            deActivateDrawing();
            // Enable all buttons
            new ReEnableButtons(plotButton, lineButton, rectangleButton, ellipseButton, polygonButton);
            // Unlock Fill Button
            fillButton.setDisable(false);
        }
    }

    /**
     * Rectangle button trigger event
     * @param actionEvent
     */
    public void handleRectangleButton(ActionEvent actionEvent) {
        if (rectangleButton.isSelected()) {
            // Disable other buttons that interfere
            new DisableButtons(rectangleButton, lineButton, plotButton, ellipseButton, polygonButton);
            // Activate drawing method on canvas
            new DrawRectangle(canvas, fillButton, colorpicker);
        } else {
            // De-activate drawing method on canvas
            deActivateDrawing();
            // Enable all buttons
            new ReEnableButtons(rectangleButton, lineButton, plotButton, ellipseButton, polygonButton);
        }
    }

    /**
     * Ellipse button trigger event
     * @param event
     */
    public void handleEllipseButton(ActionEvent event) {
        if (ellipseButton.isSelected()) {
            // Disable other buttons that interfere
            new DisableButtons(ellipseButton, lineButton, plotButton, rectangleButton, polygonButton);
            // Activate drawing method on canvas
            new DrawEllipse(canvas, fillButton, colorpicker);
        } else {
            // De-activate drawing method on canvas
            deActivateDrawing();
            // Enable all buttons
            new ReEnableButtons(ellipseButton, lineButton, plotButton, rectangleButton, polygonButton);
        }
    }

    /**
     * Polygon button trigger event
     * @param event
     */
    public void handlePolygonButton(ActionEvent event) {
        if (polygonButton.isSelected()) {
            // Disable other buttons that interfere
            new DisableButtons(polygonButton, lineButton, rectangleButton, plotButton, ellipseButton);
            // Activate drawing method on canvas
            new DrawPolygon(canvas, fillButton, colorpicker);
        } else {
            // De-activate drawing method on canvas
            deActivateDrawing();
            // Enable all buttons
            new ReEnableButtons(polygonButton, lineButton, rectangleButton, plotButton, ellipseButton);
        }
    }

    /**
     * PEN colour trigger event
     * @param event
     */
    public void handlePenButton(ActionEvent event) {

        // Outputs chosen colour
        String hex = "#" + colorpicker.getValue().toString().toUpperCase().substring(2, 8);

        // FILL Colour
        if (fillButton.isSelected()) {
            fillColour = colorpicker.getValue(); // Saves the selected fill colour
            System.out.println("FILL " + hex);
        }
        // PEN Colour
        else {
            // Outputs chosen colour
            strokeColour = colorpicker.getValue(); // Saves the selected pen colour
            System.out.println("PEN " + hex);
        }
    }

    /**
     * FILL colour trigger event
     * @param event
     */
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
     * Event handler for click action on the File -> open menu item
     * @param actionEvent
     */
    @FXML
    protected void clickFileOpen(ActionEvent actionEvent) throws IOException {
        fileChooser fc = new fileChooser();
        fc.Open();
        if (fc.getFile() != null) {
            String filename = fc.getFileName();
            newWindow(filename); // TODO: Fix drawing open in new window not on previous
            new fileReader(fc.getFile());

            //TODO: Attempting to load image based on code
            new DrawFromFile(canvas, fc.getFile());
        }
    }

    /**
     * File is saved, by updating the image code to the existing .vec file.
     * @param actionEvent
     */
    public void clickFileSave(ActionEvent actionEvent) throws IOException {
        //TODO: Need to find a way to grab an existing file's name so I can get its directory path and save it there. Also make it a save as when it is a new file
        StringBuilder sb = new StringBuilder();
        String newContent;

        if (openFile == null) {
            fileChooser fc = new fileChooser();
            fc.Save();

            openFile = fc.getFile().getAbsoluteFile();
            newContent = console.getText();
            sb.append(newContent);
        } else {
            newContent = console.getText();
            sb.append(newContent);
        }

        FileWriter fileWriter = new FileWriter(openFile);
        fileWriter.write(sb.toString());
        fileWriter.close();
    }

    /**
     * Image is saved as a new .vec file with a name and directory location. File contains image code from drawing.
     * @param actionEvent
     */
    @FXML
    public void clickFileSaveAs(ActionEvent actionEvent) throws IOException {
        fileChooser fc = new fileChooser();
        new Save(fc.getFile(), console);
    }

    /**
     * Undo trigger event - removes one drawing at a time
     * @param event
     */
    public void handleUndoButton(ActionEvent event) {
        Undo();
    }

    /**
     * New file: Multi-image support. Loads a new canvas in a separate window
     * @param actionEvent
     */
    @FXML
    public void clickFileNew(ActionEvent actionEvent) {
        newWindow(null);
    }

    /**
     * Closes the program from File -> Close when clicked.
     * @param actionEvent
     */
    public void clickFileClose(ActionEvent actionEvent) {
        Platform.exit();
    }
}