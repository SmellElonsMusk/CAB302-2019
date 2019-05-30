package backend;

import javafx.scene.control.TextArea;
import backend.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author Waldo Fouche, n9950095
 */
public class Save {
    private File file;
    private StringBuilder sb;
    private String newContent;

    private TextArea textArea;

    public Save (File file, TextArea textArea) throws IOException {
        this.sb = new StringBuilder();
        this.file = file;

        if (file == null ) {
            fileChooser fc = new fileChooser();
            fc.Save();

           file = fc.getFile().getAbsoluteFile();
            this.newContent = textArea.getText();
            sb.append(newContent);
        }

        else {
            String newContent = textArea.getText();
            sb.append(newContent);
        }

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(sb.toString());
        fileWriter.close();

    }

    public void As () throws IOException {
        this.file = file;
        if (file != null) {

            // Information from text area
            String fileContent = textArea.getText();

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(fileContent);
            fileWriter.close();

            // Gets only filename
            String filename = new File(file.toString()).getName();
        }
    }
}
