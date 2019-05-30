package backend;

import javafx.scene.control.ToggleButton;

/**
 * Re-Enables all other buttons if a button is turned off
 *
 * @author Waldo Fouche, n9950095
 **/

public class ReEnableButtons {
    public ReEnableButtons(ToggleButton clickedButton, ToggleButton b1, ToggleButton b2, ToggleButton b3, ToggleButton b4) {
        b1.setDisable(false);
        b2.setDisable(false);
        b3.setDisable(false);
        b4.setDisable(false);
    }
}
