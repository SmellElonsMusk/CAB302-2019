package backend;

import javafx.scene.control.ToggleButton;

public class EnableButtons {
    ToggleButton b1;
    ToggleButton b2;
    ToggleButton b3;
    ToggleButton b4;

    public EnableButtons(ToggleButton clickedButton, ToggleButton b1,ToggleButton b2,ToggleButton b3,ToggleButton b4) {

        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;

        b1.setDisable(false);
        b2.setDisable(false);
        b3.setDisable(false);
        b4.setDisable(false);

    }
}
