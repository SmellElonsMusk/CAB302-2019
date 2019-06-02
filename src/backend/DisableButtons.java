package backend;

import javafx.scene.control.ToggleButton;

public class DisableButtons {

    /**
     * Disables all other buttons if a button is clicked
     *
     * @param clickedButton the button that is toggled on
     * @param b1 the button that will be disabled
     * @param b2 the button that will be disabled
     * @param b3 the button that will be disabled
     * @param b4 the button that will be disabled
     */
    public DisableButtons(ToggleButton clickedButton, ToggleButton b1, ToggleButton b2, ToggleButton b3, ToggleButton b4) {

        if (clickedButton.isSelected()) {
            b1.setDisable(true);
            b2.setDisable(true);
            b3.setDisable(true);
            b4.setDisable(true);
        }
    }
}