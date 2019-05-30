package backend;

import javafx.scene.control.TextArea;
import backend.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    private File File;
    private StringBuilder sb;
    private String newContent;
    private File openfile;

    public Save (File file, TextArea textArea) throws IOException {
        this.sb = new StringBuilder();
        this.openfile = file;

        if (openfile == null ) {
            fileChooser fc = new fileChooser();
            fc.Save();

            openfile = fc.getFile().getAbsoluteFile();
            this.newContent = textArea.getText();
            sb.append(newContent);
        }

        else {
            String newContent = textArea.getText();
            sb.append(newContent);
        }

        FileWriter fileWriter = new FileWriter(openfile);
        fileWriter.write(sb.toString());
        fileWriter.close();

    }

}
