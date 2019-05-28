package userInterface;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private File save_path;

    @FXML ToggleButton lineButton;
    @FXML ToggleButton plotButton;
    @FXML ToggleButton rectangleButton;
    @FXML ToggleButton ellipseButton;
    @FXML ToggleButton polygonButton;
    @FXML ToggleButton fillButton;

    Color fillColour;
    Color strokeColour;

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

        Line line = new Line();

        if (lineButton.isSelected()){

            // Disable other buttons
            plotButton.setDisable(true);
            rectangleButton.setDisable(true);
            ellipseButton.setDisable(true);
            polygonButton.setDisable(true);

            // LINE does not use FILL
            fillButton.setDisable(true);

            // Canvas drawing
            canvas.setOnMousePressed( e -> {
                canvas.getGraphicsContext2D().setStroke(colorpicker.getValue());
                line.setStartX(e.getX());
                line.setStartY(e.getY());
            });

            canvas.setOnMouseDragged(e->{
                canvas.getGraphicsContext2D().lineTo(e.getX(), e.getY());
                //TODO: Show realtime line drag when making line
            });

            canvas.setOnMouseReleased(e->{
                line.setEndX(e.getX());
                line.setEndY(e.getY());
                canvas.getGraphicsContext2D().strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());

                // Output LINE coordinates
                System.out.println("LINE " + line.getStartX() +  " " + line.getStartY() +  " " + line.getEndX() +  " " + line.getEndY());
            });

        } else {
            // Deactivate function
            canvas.setOnMousePressed(null);
            canvas.setOnMouseDragged(null);
            canvas.setOnMouseReleased(null);

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

                canvas.getGraphicsContext2D().setFill(colorpicker.getValue());
                canvas.getGraphicsContext2D().fillRoundRect(e.getX(),e.getY(),5,5,5,5);

                // PLOT Output
                System.out.println("PLOT " + e.getX() + " " + e.getY());
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
     * Creates Rectangle given the specified Coordinates
     *
     * @Author Waldo Fouche, n9950095
     * @Author Kevin Duong
     */
    public void handleRectangleButton(ActionEvent actionEvent) {

        Rectangle rectangle = new Rectangle();

        if (rectangleButton.isSelected()) {

            // Disable all other buttons
            lineButton.setDisable(true);
            plotButton.setDisable(true);
            ellipseButton.setDisable(true);
            polygonButton.setDisable(true);

            canvas.setOnMousePressed(e -> {
                canvas.getGraphicsContext2D().setStroke(strokeColour);
                if (fillButton.isSelected()) {

                    canvas.getGraphicsContext2D().setFill(fillColour);
                }
                rectangle.setX(e.getX());
                rectangle.setY(e.getY());
            });

            canvas.setOnMouseDragged(e -> {
                canvas.getGraphicsContext2D().lineTo(e.getX(), e.getY());
            });

            canvas.setOnMouseReleased(e -> {
                rectangle.setWidth(Math.abs((e.getX() - rectangle.getX())));
                rectangle.setHeight(Math.abs((e.getY() - rectangle.getY())));

                if (rectangle.getX() > e.getX()) {
                    rectangle.setX(e.getX());
                }

                if (rectangle.getY() > e.getY()) {
                    rectangle.setY(e.getY());
                }

                if (fillButton.isSelected()){
                    canvas.getGraphicsContext2D().fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight()); // Creates filled in shape
                    canvas.getGraphicsContext2D().strokeRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight()); // Creates outline of shape
                } else {
                    canvas.getGraphicsContext2D().strokeRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
                }

                // Output RECTANGLE coordinates: X1,Y1,X2,Y2
                System.out.println("RECTANGLE " + rectangle.getX() + " " + rectangle.getY() + " " + rectangle.getWidth() + " " + rectangle.getHeight());
            });
        } else {

            // Deactivate function
            canvas.setOnMousePressed(null);
            canvas.setOnMouseDragged(null);
            canvas.setOnMouseReleased(null);

            // Re - enable all other buttons
            lineButton.setDisable(false);
            plotButton.setDisable(false);
            ellipseButton.setDisable(false);
            polygonButton.setDisable(false);
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

        //TEST
        String path = file_path.getAbsolutePath();
        System.out.println(path);

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
        StringBuilder sb = new StringBuilder();

        if (save_path == null) {
            FileChooser chooser = new FileChooser();

            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("VEC Files", "*.vec")
            );

            save_path = chooser.showSaveDialog(null).getAbsoluteFile();
            System.out.println("File Saved!");
        } else {
            String newContent = console.getText();

            // Reading file...
            try {
                FileReader fr = new FileReader(save_path);
                BufferedReader br = new BufferedReader(fr);

                String str;
                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }

                sb.append(newContent);
                System.out.println("File Saved!");

                br.close();


            } catch (IOException e) {
                System.out.println("File not found");
            }


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

        save_path = chooser.showSaveDialog(null);

        if (save_path != null) {

            // Information from text area
            String fileContent = console.getText();

            FileWriter fileWriter = new FileWriter(save_path);
            fileWriter.write(fileContent);
            fileWriter.close();

            console.clear(); // clears console after save
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


