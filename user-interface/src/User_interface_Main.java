import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

import java.io.File;

public class User_interface_Main extends Application {

    /**
     *
     *
      * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX App");

        // File -> open -> launches File chooser
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg")
                ,new FileChooser.ExtensionFilter("VEC Files", "*.VEC")
        );

        // Add Menu Bar
        MenuBar menuBar = new MenuBar();

        // Main Menu Bar selections
        Menu menuFile = new Menu("File");
        Menu menuHome = new Menu("Home");
        Menu menuView = new Menu("View");

        // Sub menu selections
        MenuItem menuItemNew = new MenuItem("New");
        SeparatorMenuItem separator = new SeparatorMenuItem();
        MenuItem menuItemOpen = new MenuItem("Open");

        // Populating MenuBar
        menuBar.getMenus().add(menuFile);
        menuBar.getMenus().add(menuHome);
        menuBar.getMenus().add(menuView);

        // Populating Menu Sub Menus
        menuFile.getItems().add(menuItemNew);
        menuFile.getItems().add(separator);
        menuFile.getItems().add(menuItemOpen);

        // Listeners for Menu items

        menuItemNew.setOnAction(e -> {
            System.out.println("Menu Item 'New' Selected");
        });

        menuItemOpen.setOnAction(e -> {
            System.out.println("Menu Item 'Open' Selected");

            // Shows open file dialgoue box
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
        });

        // Create drawing canvas

        // Create the Canvas
        Canvas canvas = new Canvas(200, 200);
        // Set the width of the Canvas
        canvas.setWidth(200);
        // Set the height of the Canvas
        canvas.setHeight(200);

        // Get the graphics context of the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Draw a Text
        gc.strokeText("Hello Canvas", 150, 100);

        // Create the Pane
        Pane root = new Pane();
        // Set the Style-properties of the Pane
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        // Add the Canvas to the Pane
        root.getChildren().add(canvas);

        // Create the Scene
        //Scene scene = new Scene(root);
        // Add the Scene to the Stage
        //primaryStage.setScene(scene);
        // Set the Title of the Stage
        //primaryStage.setTitle("Creation of a Canvas");
        // Display the Stage
        //primaryStage.show();

        // Creating the Scene

        VBox vBox = new VBox(menuBar);

        Scene scene = new Scene(vBox, 640, 480);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

//    public void start(Stage primaryStage) throws Exception{
//        String javaVersion = System.getProperty("java.version");
//        String javafxVersion = System.getProperty("javafx.version");
//        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
//        Scene scene = new Scene(new StackPane(l), 640, 480);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

}
