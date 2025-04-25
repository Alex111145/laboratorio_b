package book_recommender.lab_b;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageController {
    @FXML
    private VBox titlePage;

    @FXML
    private VBox authorPage;

    @FXML
    private VBox authorYearPage;

    @FXML
    private Button titleTabButton;

    @FXML
    private Button authorTabButton;

    @FXML
    private Button authorYearTabButton;

    public void entrainlogin(ActionEvent actionEvent) {
        try {
            // Stampa un messaggio di debug
            System.out.println("Tentativo di apertura della pagina login...");

            // Carica il file login.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();

            // Ottieni lo stage corrente
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Imposta la nuova scena
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login - Book Recommender");
            stage.show();

        } catch (IOException e) {
            System.err.println("Errore nel caricamento della pagina di login: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void entrainregistrazione(ActionEvent actionEvent) {
        try {
            // Stampa un messaggio di debug
            System.out.println("Tentativo di apertura della pagina di registrazione...");

            // Carica il file registrazione.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registrazione.fxml"));
            Parent root = loader.load();

            // Ottieni lo stage corrente
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Imposta la nuova scena
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Registrazione - Book Recommender");
            stage.show();

        } catch (IOException e) {
            System.err.println("Errore nel caricamento della pagina di registrazione: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void onTitleTabClicked(ActionEvent actionEvent) {
        // Mostra la pagina del titolo, nascondi le altre
        titlePage.setVisible(true);
        authorPage.setVisible(false);
        authorYearPage.setVisible(false);

        // Aggiorna lo stile dei pulsanti
        titleTabButton.setStyle("-fx-background-color: #4E90E2; -fx-text-fill: white;");
        authorTabButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        authorYearTabButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
    }

    public void onAuthorTabClicked(ActionEvent actionEvent) {
        // Mostra la pagina dell'autore, nascondi le altre
        titlePage.setVisible(false);
        authorPage.setVisible(true);
        authorYearPage.setVisible(false);

        // Aggiorna lo stile dei pulsanti
        titleTabButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        authorTabButton.setStyle("-fx-background-color: #4E90E2; -fx-text-fill: white;");
        authorYearTabButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
    }

    public void onAuthorYearTabClicked(ActionEvent actionEvent) {
        // Mostra la pagina autore/anno, nascondi le altre
        titlePage.setVisible(false);
        authorPage.setVisible(false);
        authorYearPage.setVisible(true);

        // Aggiorna lo stile dei pulsanti
        titleTabButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        authorTabButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        authorYearTabButton.setStyle("-fx-background-color: #4E90E2; -fx-text-fill: white;");
    }

    public void cercalibro(ActionEvent actionEvent) {
        // Funzionalità di ricerca libro implementata in futuro
        System.out.println("Search button clicked");
    }

    public void visualizzalibro(ActionEvent actionEvent) {
        // Funzionalità di visualizzazione dettagli libro implementata in futuro
        System.out.println("View book button clicked");
    }

    // Metodo di inizializzazione che viene chiamato dopo il caricamento del FXML
    @FXML
    public void initialize() {
        // Imposta la pagina del titolo come predefinita
        titlePage.setVisible(true);
        authorPage.setVisible(false);
        authorYearPage.setVisible(false);
    }
}