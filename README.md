# JavaFX 11.02
Download Link: https://gluonhq.com/products/javafx/

Bit of a pain to setup, so here is the steps:

Windows:
1) Extract JavaFX zip to a permanent location.

In IntelliJ:

1) File -> Project Structure -> Project, set the project SDK to 11 and the language level to 11.
2) File -> Project Structure -> Libraries, add the JavaFX sdk as a library, point towards the lib folder within the JavaFX SDK.

3) This should allow IntelliJ to see the classes now.

4) If the project is run, then you should get this error: "Error: JavaFX runtime components are missing, and are required to run this application"

5) Fix this by going to Run -> Edit Configurations... and add the following VM option (Only for the user-interface module): --module-path "\path\to\javafx-sdk-12.0.1\lib" --add-modules javafx.controls,javafx.fxml

Should work fine now

# Contributors
**Kevin Doung**, n9934731

**Waldo Fouche**, n9950095


