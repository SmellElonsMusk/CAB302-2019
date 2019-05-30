package userInterface;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import backend.*;

/**
 *
 * TODO: Fix Canvas Drawing
 *
 * @Author: Waldo Fouche, Kevin Doung
 */
public class Controller implements Initializable {


    @FXML private TextArea console; // Console on GUI display
    @FXML private ColorPicker colorpicker; // Colour wheel
    private File save_path;
    private File currentFile;
    private String openFile;

    @FXML ToggleButton lineButton;
    @FXML ToggleButton plotButton;
    @FXML ToggleButton rectangleButton;
    @FXML ToggleButton ellipseButton;
    @FXML ToggleButton polygonButton;
    @FXML ToggleButton fillButton;

    @FXML Button undoButton;

    @FXML
    BorderPane borderPane;

    Color fillColour;
    Color strokeColour;

    @FXML private Canvas canvas;

    @FXML
    public GraphicsContext gc;

    public void initGraphics() {
        gc = canvas.getGraphicsContext2D();
    }

    public void drawClicked() {

        gc.setFill(Color.RED);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

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
        initGraphics();

        borderPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> {
            canvas.setWidth(newValue.doubleValue()-350);
            drawClicked();
        });

        borderPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> {
            canvas.setHeight(newValue.doubleValue()-100);
            drawClicked();
        });

        ConsoleGUI gui = new ConsoleGUI(console);
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
     *
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
            EnableButtons enableButtons = new EnableButtons(lineButton,plotButton,rectangleButton,ellipseButton,polygonButton);
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
            DisableButtons disable = new DisableButtons(plotButton,lineButton,rectangleButton,ellipseButton,polygonButton);
            // PLOT does not use FILL
            fillButton.setDisable(true);
            DrawPlot newPlot = new DrawPlot(canvas, colorpicker);
        } else {
            deActivateDrawing();
            EnableButtons enableButtons = new EnableButtons(plotButton,lineButton,rectangleButton,ellipseButton,polygonButton);
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
        } else {
            deActivateDrawing();
            EnableButtons enableButtons = new EnableButtons(rectangleButton,lineButton,plotButton,ellipseButton,polygonButton);
        }
    }

    /**
     * @Author Kevin Duong, n9934731
     * ELLIPSE function.
     */
    public void handleEllipseButton(ActionEvent event) {
        if (ellipseButton.isSelected()) {
            DisableButtons disableButtons = new DisableButtons(ellipseButton,lineButton,plotButton,rectangleButton,polygonButton);
            DrawEllipse newEllipse = new DrawEllipse(canvas,fillButton,colorpicker);
        } else {
            deActivateDrawing();
            EnableButtons enableButtons = new EnableButtons(ellipseButton,lineButton,plotButton,rectangleButton,polygonButton);
        }
    }

    /**
     * @Author Kevin Duong, n9934731
     * POLYGON function.
     */
    public void handlePolygonButton(ActionEvent event) {
        if (polygonButton.isSelected()){
            DisableButtons disableButtons = new DisableButtons(polygonButton,lineButton,rectangleButton,plotButton,ellipseButton);
            DrawPolygon newPolygon = new DrawPolygon(canvas,colorpicker);
        } else {
            deActivateDrawing();
            EnableButtons enableButtons = new EnableButtons(polygonButton,lineButton,rectangleButton,plotButton,ellipseButton);
        }
    }

    /**
     * @Author Kevin Duong, n9934731
     * Updates the PEN colour
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
     * @Author Kevin Duong, n9934731
     * Undo Button - removes one step at a time
     * @param event
     */

    public void handleUndoButton(ActionEvent event) {
        String array[] = console.getText().split("\n");
        String textToSet = "";
        for(int i=1; i<array.length; i++){
            textToSet+=array[i-1] + "\n";
        }
        console.setText(textToSet);
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
            stage.setTitle("VEC Paint - Untitled");
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
        save_path = chooser.showOpenDialog(null);
        openFile = save_path.getAbsolutePath();

        //TEST;
        System.out.println("Location of file opened: " + save_path);

        if (save_path != null) {

            try {
                // Gets only filename
                String filename = new File(save_path.toString()).getName();

                // Launch new window
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ui_layout.fxml"));

                // Implement stylesheet
                Scene scene = new Scene(fxmlLoader.load(), 950, 680);
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
                FileReader fr = new FileReader(save_path);
                BufferedReader br = new BufferedReader(fr);

                String str;
                while ((str = br.readLine()) != null) {
                    System.out.println(str);
                }

                br.close();
            } catch (IOException e) {
                System.out.println("File not found");
            }

            //TODO: Attempting to load image based on code
        }
    }

    /**
     * @author Kevin Duong, n9934731 Waldo Fouche
     * File is saved, by updating the image code to the .vec file.
     * @param actionEvent
     */
    public void clickFileSave(ActionEvent actionEvent) throws IOException {
        //TODO: Need to find a way to grab an existing file's name so I can get its directory path and save it there. Also make it a save as when it is a new file
        StringBuilder sb = new StringBuilder();

        if (save_path == null ) {
            FileChooser chooser = new FileChooser();

            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("VEC Files", "*.vec")
            );

            save_path = chooser.showSaveDialog(null).getAbsoluteFile();
            String newContent = console.getText();
            sb.append(newContent);
            //System.out.println("File Saved!");

        } else {
            String newContent = console.getText();
            sb.append(newContent);
            //System.out.println("File Saved!");
        }

        FileWriter fileWriter = new FileWriter(save_path);
        fileWriter.write(sb.toString());
        fileWriter.close();
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

        save_path = chooser.showSaveDialog(null).getAbsoluteFile();

        if (save_path != null) {

            // Information from text area
            String fileContent = console.getText();

            FileWriter fileWriter = new FileWriter(save_path);
            fileWriter.write(fileContent);
            fileWriter.close();

            // Gets only filename
            String filename = new File(save_path.toString()).getName();
        }
    }

    /**
     * Closes the program from File -> Close when clicked.
     * @Author Waldo Fouche
     */
    public void clickFileClose(ActionEvent actionEvent) {
        Platform.exit();
    }
}


