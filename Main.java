package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * @author - Andrew Grim
 * @version - 1.0.0
 * @since - 11/30/2022
 *
 * C195 Software II : Scheduling Application
 *
 * This program allows a user to schedule, add, update, delete, and view appointments and customers.
 *
 * This class is the Main class which extends the Application class.
 * The Main class loads the Login page.
 */
public class Main extends Application {
    /**
     * Opens the Login page
     * @param stage - stage is the argument taken by the start method
     * @throws IOException - start method throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle("Scheduler Program");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    /**
     *
     * @param args - main method launches the program
     */
    public static void main(String[] args) {
        launch();
    }
}