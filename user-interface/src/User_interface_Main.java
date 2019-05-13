import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import javafx.event.EventHandler;

import javafx.stage.Stage;
import javafx.stage.FileChooser;


import java.io.File;

/**
 * TODO
 * Create the UI responsive to the size of the window
 */
public class User_interface_Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private void initDraw(GraphicsContext gc){
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        gc.setFill(Color.LIGHTGRAY);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);

        gc.fill();
        gc.strokeRect(
                0,              //x of the upper left corner
                0,              //y of the upper left corner
                canvasWidth,    //width of the rectangle
                canvasHeight);  //height of the rectangle

        gc.setFill(Color.RED);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);

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
        Canvas canvas = new Canvas(640, 400);
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        initDraw(graphicsContext);

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>(){

                    @Override
                    public void handle(MouseEvent event) {
                        graphicsContext.beginPath();
                        graphicsContext.moveTo(event.getX(), event.getY());
                        graphicsContext.stroke();
                    }
                });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>(){

                    @Override
                    public void handle(MouseEvent event) {
                        graphicsContext.lineTo(event.getX(), event.getY());
                        graphicsContext.stroke();
                    }
                });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>(){

                    @Override
                    public void handle(MouseEvent event) {

                    }
                });

        // Create the Pane
        Pane root = new Pane();
        // Add the Canvas to the Pane
        root.getChildren().add(canvas);

        // Creating the Scene
        VBox vBox = new VBox(menuBar, root);

        Scene scene = new Scene(vBox, 640, 480);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
