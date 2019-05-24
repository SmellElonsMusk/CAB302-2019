package backend;

import java.io.*;

/**
 * @author Waldo Fouche, n9950095
 */
public class loadFromFile {
    private String[] fileData;
    /**
     *
     * TODO: Test File - not sure if it works or not.
     *
     * Loads in the selected file from disk, then stores the data in an array List to be processed
     * @param fileName
     */
    public loadFromFile (String fileName) throws IOException {

        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String line;
        int i = 0;
        while((line = br.readLine()) != null){

            //process the line
            fileData[i] = line;
            i+=1;
        }
        br.close();
    }

    /**
     * TODO : Fix toString Method
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
