package backend;

import javafx.stage.FileChooser;
import java.io.File;

/**
 * Custom File chooser command. Loads up the file chooser and can
 * set it to save or open dynamically
 */

public class fileChooser {
    private FileChooser chooser;
    private File file;
    private String path;

    public fileChooser() {
        this.chooser = new FileChooser();

        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("VEC Files", "*.vec")
        );
    }

    /**
     * Sets the file chooser to open
     */
    public void Open() {
        this.file = chooser.showOpenDialog(null);
        this.path = file.getAbsolutePath();

        //TEST;
        System.out.println("Location of file opened: " + path);
    }

    /**
     * Sets the file chooser to save
     */
    public void Save() {
        this.file = chooser.showSaveDialog(null);
        this.path = file.getAbsolutePath();

        //TEST;
        System.out.println("Location of file opened: " + path);
    }

    /**
     * Returns the file opened
     * @return
     */
    public File getFile() {
        return file;
    }

    /**
     * Returns the file path
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     * Returns the filename
     * @return
     */
    public String getFileName() {
        return file.getName();
    }
}