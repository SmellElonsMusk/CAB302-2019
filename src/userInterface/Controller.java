package userInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.ZoomEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

import backend.*;

import javax.xml.catalog.Catalog;

/**
 *
 * TODO: Fix Canvas Drawing
 *
 * @Author: Waldo Fouche, Kevin Doung
 */
public class Controller {

    @FXML private Canvas canvas;
    private GraphicsContext gc;
    public int mouseX;
    public int moouseY;

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
     *
     * @param actionEvent
     */
    @FXML
    protected void clickSave(ActionEvent actionEvent) {
        System.out.println("Clicked: Save");
    }

    /**
     *
     * @param actionEvent
     */
    @FXML
    protected void clickSaveAs(ActionEvent actionEvent) {
        System.out.println("Clicked: Save as");
    }

    /**
     *
     * @param actionEvent
     */
    @FXML
    protected void clickAbout(ActionEvent actionEvent) {
        System.out.println("Clicked: about");
    }

    @FXML
    public void canvasMouseClick(MouseEvent mouseEvent) {
        System.out.println("Mouse Clicked");

    }

    @FXML
    public void canvasMouseDrag(MouseEvent mouseEvent) {
        System.out.println("Mouse drag");
    }

    @FXML
    public void canvasMouseScrollStart(ScrollEvent scrollEvent) {
        System.out.println("Mouse Scroll Start");
    }

    @FXML
    public void canvasMouseScrollStop(ScrollEvent scrollEvent) {
        System.out.println("Mouse Scroll Stop");
    }

    @FXML
    public void canvasZoom(ZoomEvent zoomEvent) {
    }

    @FXML
    public void canvasZoomStopped(ZoomEvent zoomEvent) {
        System.out.println("Mouse zoom stopped");
    }

    @FXML
    public void canvasZoomStarted(ZoomEvent zoomEvent) {
        System.out.println("Mouse zoom start");
    }
}


