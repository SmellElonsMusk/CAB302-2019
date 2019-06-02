package backend;

import javafx.scene.control.ToggleButton;

public class ReEnableButtons {

    /**
     * Re-Enables all other buttons if a button is turned off
     *
     * @param clickedButton the button that is toggled off
     * @param b1 the button that will be enabled
     * @param b2 the button that will be enabled
     * @param b3 the button that will be enabled
     * @param b4 the button that will be enabled
     */

    public ReEnableButtons(ToggleButton clickedButton, ToggleButton b1, ToggleButton b2, ToggleButton b3, ToggleButton b4) {
        b1.setDisable(false);
        b2.setDisable(false);
        b3.setDisable(false);
        b4.setDisable(false);
    }
}