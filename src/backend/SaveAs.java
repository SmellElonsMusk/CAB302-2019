package backend;

import javafx.scene.control.TextArea;

import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveAs {

    private File file;
    public SaveAs (File file, TextArea console ) throws IOException {

        this.file = file;
        if (file != null) {

            // Information from text area
            String fileContent = console.getText();

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(fileContent);
            fileWriter.close();

            // Gets only filename
            String filename = new File(file.toString()).getName();
        }

    }

}
