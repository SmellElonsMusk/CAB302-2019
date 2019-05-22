package userInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import backend.*;

import java.io.File;

public class Controller {

    @FXML
    public void clickFileNew(ActionEvent actionEvent) {
        System.out.println("Clicked: New");
    }

    @FXML
    protected void clickFileOpen(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg")
                ,new FileChooser.ExtensionFilter("VEC Files", "*.vec")
        );

        File file = chooser.showOpenDialog(new Stage());

        System.out.println("Clicked: Open");

    }

    @FXML
    protected void clickSave(ActionEvent actionEvent) {
        System.out.println("Clicked: Save");
    }

    @FXML
    protected void clickSaveAs(ActionEvent actionEvent) {
        System.out.println("Clicked: Save as");
    }

    @FXML
    protected void clickAbout(ActionEvent actionEvent) {
        System.out.println("Clicked: about");
    }


}


