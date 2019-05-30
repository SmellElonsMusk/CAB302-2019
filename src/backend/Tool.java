package backend;

import javafx.scene.control.ColorPicker;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ToggleButton;

/**
 * TOOL FUNCTIONALITY
 * @Author Waldo Fouche, n9934731
 */

public class Tool {

    private Canvas canvas;
    private ColorPicker color;

    public Tool (Canvas canvas) {
        this.canvas = canvas;
    }

    public Tool(Canvas canvas, ColorPicker colorPicker) {
        this.canvas = canvas;
        this.color = colorPicker;
    }

    public Tool(Canvas canvas, ToggleButton fillButton) {
        this.canvas = canvas;
    }

    public Tool(Canvas canvas, ColorPicker colorPicker, ToggleButton fillButton) {
        this.canvas = canvas;
    }
}
