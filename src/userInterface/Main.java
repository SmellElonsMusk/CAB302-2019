package userInterface;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import backend.*;

import java.io.IOException;


public class Main extends Application {

    public static void main(String[] args) throws IOException {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            Parent root = FXMLLoader.load(getClass().getResource("ui_layout.fxml"));
            primaryStage.setTitle("Vec Draw" );
            Scene scene = new Scene(root,1300,800);
            scene.getStylesheets().add("userInterface/stylesheet.css");
            primaryStage.setScene(scene);

            primaryStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
