package backend;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

/**
 * Custom Filechooser command. Loads up the filechooser and can
 * set it to save or open dynamically
 *
 * @Author Waldo Fouche, n9950095
 * @Date:
 */
public class fileChooser {
    public FileChooser chooser;
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
    public void Open(){
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
    public File getFile () {
        return file;
    }

    /**
     * Returns the file path
     * @return
     */
    public String getPath () {
        return path;
    }

    /**
     * Returns the filename
     * @return
     */
    public String getFileName () {
        return file.getName();
    }

}
