package userInterface;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import backend.*;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ui_layout.fxml"));
        primaryStage.setTitle("Hello World" );
        Scene scene = new Scene(root,1024,768);
        scene.getStylesheets().add("userInterface/stylesheet.css");
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
