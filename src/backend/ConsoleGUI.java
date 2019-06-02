package backend;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Streams the text being sent from the console to the GUI TextArea
 */

public class ConsoleGUI {

    private PrintStream ps; // Streams to console on GUI

    public class Console extends OutputStream {
        private TextArea console;

        public Console(TextArea console) {
            this.console = console;
        }
        public void appendText(String valueOf) {
            Platform.runLater(() -> console.appendText(valueOf));
        }
        public void write(int b) throws IOException {
            appendText(String.valueOf((char) b));
        }
    }

    public ConsoleGUI(TextArea console) {
        this.ps = new PrintStream(new Console(console));
        System.setOut(ps); // sets the console output to gui display
        System.setErr(ps); // Sets the error output to gui display
    }
}