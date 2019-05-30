package backend;

import javafx.scene.control.ToggleButton;

/**
 * Disables all other buttons if a button is clicked
 *
 * @author Waldo Fouche, n9950095
 **/

public class DisableButtons {
    public DisableButtons(ToggleButton clickedButton, ToggleButton b1,ToggleButton b2,ToggleButton b3,ToggleButton b4) {

        if (clickedButton.isSelected()) {
            b1.setDisable(true);
            b2.setDisable(true);
            b3.setDisable(true);
            b4.setDisable(true);
        }
    }
}
