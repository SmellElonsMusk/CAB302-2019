package backend;

import javafx.application.Application;
import javafx.scene.input.MouseEvent;
import javafx.scene.* ;
import javafx.stage.Stage;
import javafx.geometry.* ; // Point2D
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle ;

public class DrawingRectangle extends tool {

    double x1, y1;

    Group rectangle_group = new Group();

    Rectangle new_rectangle = null;

    boolean new_rectangle_is_being_drawn = false;

    // TODO: implement colour picker to draw as lines
    Color[] colours = { Color.BLACK } ;

    int color_index = 0 ;

    void adjust_rectangle_properties( double x1, double y1, double x2, double y2, Rectangle given_rectangle )
    {
        given_rectangle.setX( x1 ) ;
        given_rectangle.setY( y1 ) ;
        given_rectangle.setWidth( x2 - x1 ) ;
        given_rectangle.setHeight( y2 - y1 ) ;

        if ( given_rectangle.getWidth() < 0 )
        {
            given_rectangle.setWidth( - given_rectangle.getWidth() ) ;
            given_rectangle.setX( given_rectangle.getX() - given_rectangle.getWidth() ) ;
        }

        if ( given_rectangle.getHeight() < 0 )
        {
            given_rectangle.setHeight( - given_rectangle.getHeight() ) ;
            given_rectangle.setY( given_rectangle.getY() - given_rectangle.getHeight() ) ;
        }
    }

    public void start( Stage stage )
    {
        stage.setTitle( "DrawingRectanglesFX.java" ) ;

        Scene scene = new Scene( rectangle_group, 800, 600 ) ;

        scene.setFill( Color.BEIGE ) ;

        scene.setOnMousePressed( ( MouseEvent event ) ->
        {
            if ( new_rectangle_is_being_drawn == false )
            {
                x1 = event.getSceneX() ;
                y1 = event.getSceneY() ;

                new_rectangle = new Rectangle() ;

                // A non-finished rectangle has always the same color.
                new_rectangle.setFill( Color.SNOW ) ; // almost white color
                new_rectangle.setStroke( Color.BLACK ) ;

                rectangle_group.getChildren().add( new_rectangle ) ;

                new_rectangle_is_being_drawn = true ;
            }
        } ) ;

        scene.setOnMouseDragged( ( MouseEvent event ) ->
        {
            if ( new_rectangle_is_being_drawn == true )
            {
                double current_ending_point_x = event.getSceneX() ;
                double current_ending_point_y = event.getSceneY() ;

                adjust_rectangle_properties( x1,
                        y1,
                        current_ending_point_x,
                        current_ending_point_y,
                        new_rectangle ) ;
            }
        } ) ;

        scene.setOnMouseReleased( ( MouseEvent event ) ->
        {
            if ( new_rectangle_is_being_drawn == true )
            {
                // Now the drawing of the new rectangle is finished.
                // Let's set the final color for the rectangle.

                new_rectangle.setFill( colours[ color_index ] ) ;

                color_index ++ ;  // Index for the next color to use.

                // If all colors have been used we'll start re-using colors from the
                // beginning of the array.

                if ( color_index >= colours.length )
                {
                    color_index = 0 ;
                }

                new_rectangle = null ;
                new_rectangle_is_being_drawn = false ;
            }
        } ) ;

        stage.setScene( scene ) ;
        stage.show();
    }
}
