/**
 * Contiene il Main del progetto Book_Recommender.
 * Versione modificata per l'utilizzo con GUI.
 *
 * @author Alessio     	Gervasini 		Mat. 756181
 * @author Francesco 	Orsini Pio		Mat. 756954
 * @author Luca      	Borin        	Mat. 756563
 */
package Book_Recommender;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe principale per l'applicazione di Book Recommender.
 */
public class Main {
    // Definizione dei colori per la GUI
    public static final String RESET = "";
    public static final String ROSSO = "";
    public static final String VERDE = "";
    public static final String GIALLO = "";
    public static final String CIANO = "";
    public static final String VIOLA = "";
    public static final String V = "✓";
    public static final String X = "✗";

    public static final String abs = "data" + File.separator;
    public static final String librerie_path = abs + "Librerie.dati.csv";
    public static final String FILE_PATH = abs + "Data.csv";
    public static final String reg = abs + "UtentiRegistrati.csv";
    public static final String VALUTAZIONI_FILE_PATH = abs + "ValutazioniLibri.csv";
    public static final String CONSIGLI_FILE_PATH = abs + "ConsigliLibri.dati.csv";
    public static final String LIBRI_FILE_PATH = abs + "Libri.csv";
    private static final long CHECK_INTERVAL = 10000; // Intervallo di controllo in millisecondi (10 secondi)
    private static final long UPDATE_INTERVAL = 30000; // Intervallo di aggiornamento in millisecondi (30 secondi)
    private static Timer timer; // Timer per l'aggiornamento
    private static Timer timer2; // Timer per il controllo dei file

    /**
     * Metodo principale per l'avvio dell'applicazione.
     *
     * @param args Argomenti da linea di comando
     */
    public static void main(String[] args) {
        // Verifica che esista la directory dei dati, altrimenti la crea
        File dataDir = new File(abs);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        // Avvia l'aggiornamento automatico
        autoaggiornamento();

        // Avvia l'interfaccia grafica usando riflessione per evitare dipendenze dirette da javax.swing
        try {
            // Utilizza riflessione per evitare l'importazione diretta di javax.swing
            Class.forName("javax.swing.SwingUtilities").getMethod("invokeLater", Runnable.class)
                    .invoke(null, (Runnable) () -> {
                        try {
                            Object gui = Class.forName("Book_Recommender.BookRecommenderGUI").getDeclaredConstructor().newInstance();
                            gui.getClass().getMethod("setVisible", boolean.class).invoke(gui, true);
                        } catch (Exception e) {
                            System.err.println("Errore nell'avvio dell'interfaccia grafica: " + e.getMessage());
                            System.out.println("Prova a eseguire con: java --add-modules java.desktop Book_Recommender.Main");
                        }
                    });
        } catch (Exception e) {
            System.err.println("Errore nell'inizializzazione dell'interfaccia grafica: " + e.getMessage());
            System.out.println("Prova a eseguire con: java --add-modules java.desktop Book_Recommender.Main");
            System.out.println("Oppure esegui il programma con l'opzione --class-path invece che come modulo.");
        }
    }

    /**
     * Pianifica le attività di aggiornamento automatico in base alla presenza di file specifici.
     */
    public static void autoaggiornamento() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    File valutazioniFile = new File(VALUTAZIONI_FILE_PATH);
                    File consigliFile = new File(CONSIGLI_FILE_PATH);
                    if (valutazioniFile.exists() || consigliFile.exists()) {
                        timer.cancel();

                        timer2 = new Timer();
                        timer2.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                try {
                                    Libricsv.generaFileLibri();
                                } catch (Exception e) {
                                    System.err.println("Errore nell'aggiornamento automatico: " + e.getMessage());
                                }
                            }
                        }, 0, UPDATE_INTERVAL); // Esegui subito e ripeti ogni 30 secondi
                    }
                } catch (Exception e) {
                    System.err.println("Errore nel controllo dei file: " + e.getMessage());
                }
            }
        }, 0, CHECK_INTERVAL); // Controlla ogni 10 secondi se il file esiste
    }

    /**
     * Legge un file CSV e restituisce una lista di array di stringhe rappresentanti i record.
     *
     * @param filePath Il percorso del file CSV.
     * @return Una lista di array di stringhe rappresentanti i record.
     */
    public static List<String[]> leggiFileCsv(String filePath) {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // Divisione dei campi tenendo conto delle virgole all'interno delle virgolette
                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].replace("\"", "").trim(); // Rimuove le virgolette e gli spazi vuoti all'inizio e alla fine
                }
                records.add(values);
            }
        } catch (IOException e) {
            System.err.println("Errore nella lettura del file CSV: " + e.getMessage());
        }
        return records;
    }

    /**
     * Controlla se un libro esiste nel file CSV dei dati basato sul titolo.
     *
     * @param titolo Il titolo del libro da controllare.
     * @return true se il libro esiste, false altrimenti.
     */
    public static boolean libroEsisteInDataCsv(String titolo) {
        List<String[]> libri = leggiFileCsv(FILE_PATH);
        for (String[] datiLibro : libri) {
            if (datiLibro.length > 0 && datiLibro[0].equalsIgnoreCase(titolo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Controlla se una stringa è numerica.
     *
     * @param str La stringa da controllare.
     * @return true se la stringa è numerica, false altrimenti.
     */
    public static boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

    /**
     * Ferma i timer quando l'applicazione viene chiusa.
     */
    public static void stopTimers() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timer2 != null) {
            timer2.cancel();
            timer2 = null;
        }
    }
}