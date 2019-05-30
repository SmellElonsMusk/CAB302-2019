package userInterface;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage){
        try{
            BorderPane root = FXMLLoader.load(getClass().getResource("ui_layout.fxml"));
            primaryStage.setTitle("VEC Paint - Untitled");
            Scene scene = new Scene(root,950,680);

            // Stylesheet for icons, design
            scene.getStylesheets().add("userInterface/stylesheet.css");
            scene.getStylesheets().add("userInterface/menuBarStylesheet.css");

            // Display GUI
            primaryStage.setScene(scene);
            primaryStage.show();

            root.prefWidthProperty().bind(scene.widthProperty());
            root.prefHeightProperty().bind(scene.heightProperty());

            // Exit
            primaryStage.setOnCloseRequest(e -> Platform.exit());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//    public static void main ( String args[] )
//    {
//        String input = "text 3.454 sometext5.567568more_text";
//        ArrayList < Double > myDoubles = new ArrayList < Double >();
//        Matcher matcher = Pattern.compile( "[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?" ).matcher( input );
//
//        while ( matcher.find() )
//        {
//            double element = Double.parseDouble( matcher.group() );
//            myDoubles.add( element );
//        }
//
//        for ( double element: myDoubles )
//            System.out.println( element );
//    }