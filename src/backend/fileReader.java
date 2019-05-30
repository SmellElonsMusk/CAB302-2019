package backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 */
public class fileReader {
    private File file;
    public fileReader (File file) {

        this.file = file;

        // Reading file...
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }

            br.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
