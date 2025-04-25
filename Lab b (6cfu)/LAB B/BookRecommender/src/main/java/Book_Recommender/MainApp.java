package Book_Recommender;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Main application class for the Book Recommender GUI.
 *
 * @author Alessio     	Gervasini 		Mat. 756181
 * @author Francesco 	Orsini Pio		Mat. 756954
 * @author Luca      	Borin        	Mat. 756563
 */
public class MainApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        // Create data directory if it doesn't exist
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        // Initialize necessary files
        if (!new File(Main.LIBRI_FILE_PATH).exists()) {
            Libricsv.generaFileLibri();
        }

        loadHomePage();
    }

    /**
     * Loads the home page view
     */
    public static void loadHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/HomePage.fxml"));
            Parent root = loader.load();
            HomePageController controller = loader.getController();

            Scene scene = new Scene(root);
            primaryStage.setTitle("Book Recommender");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the login page view
     */
    public static void loadLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/LoginPage.fxml"));
            Parent root = loader.load();
            LoginPageController controller = loader.getController();

            Scene scene = new Scene(root);
            primaryStage.setTitle("Login - Book Recommender");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the registration page view
     */
    public static void loadRegistrationPage() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/RegistrationPage.fxml"));
            Parent root = loader.load();
            RegistrationPageController controller = loader.getController();

            Scene scene = new Scene(root);
            primaryStage.setTitle("Registration - Book Recommender");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the user dashboard page view
     *
     * @param userid The ID of the logged-in user
     */
    public static void loadUserDashboard(String userid) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/UserDashboard.fxml"));
            Parent root = loader.load();
            UserDashboardController controller = loader.getController();
            controller.initData(userid);

            Scene scene = new Scene(root);
            primaryStage.setTitle("Dashboard - Book Recommender");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method that launches the application
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Returns the primary stage
     *
     * @return the primary stage
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}