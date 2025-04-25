package book_recommender.lab_b;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField userIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorMessage;

    @FXML
    public void handleLogin(ActionEvent event) {
        // Implementazione futura della logica di login
        System.out.println("Login con userID: " + userIdField.getText());

        // Esempio di controllo login (da modificare con la tua logica)
        if (userIdField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            errorMessage.setVisible(true);
        } else {
            // Login riuscito - vai alla prossima pagina (da implementare)
            errorMessage.setVisible(false);
            System.out.println("Login riuscito!");
        }
    }

    @FXML
    public void handleBack(ActionEvent event) {
        try {
            // Torna alla pagina principale
            FXMLLoader loader = new FXMLLoader(getClass().getResource("homepage.fxml"));
            Parent root = loader.load();

            // Ottieni lo stage corrente
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Imposta la nuova scena
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Book Recommender");
            stage.show();

        } catch (IOException e) {
            System.err.println("Errore nel caricamento della homepage: " + e.getMessage());
            e.printStackTrace();
        }
    }
}