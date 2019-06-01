package backend;

import javafx.scene.control.ToggleButton;

/**
 * Re-Enables and Disables all other buttons if a button is turned off
 *
 * @author Waldo Fouche, n9950095
 **/

public class HandleButtons {
    ToggleButton clicked;
    ToggleButton b1;
    ToggleButton b2;
    ToggleButton b3;
    ToggleButton b4;
    public HandleButtons(ToggleButton clickedButton, ToggleButton b1, ToggleButton b2, ToggleButton b3, ToggleButton b4) {
       this.b1 = b1;
        this.b1 = b2;
        this.b1 = b3;
        this.b1 = b4;
    }

    public void enable() {
        b1.setDisable(false);
        b2.setDisable(false);
        b3.setDisable(false);
        b4.setDisable(false);
    }

    public  void disable() {
        if (clicked.isSelected()) {
            b1.setDisable(true);
            b2.setDisable(true);
            b3.setDisable(true);
            b4.setDisable(true);
        }
    }




}
