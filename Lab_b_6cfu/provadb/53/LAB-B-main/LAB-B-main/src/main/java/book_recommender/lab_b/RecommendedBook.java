package book_recommender.lab_b;

/**
 * Classe di supporto per gestire le informazioni sui libri consigliati.
 * Utilizzata principalmente per organizzare i dati relativi ai consigli di libri
 * durante la visualizzazione e la gestione dei consigli.
 */
public class RecommendedBook {
    public String userId;       // ID dell'utente che ha fatto il consiglio
    public int sourceBookId;    // ID del libro per cui è stato fatto il consiglio
    public int recommendedBookId; // ID del libro consigliato
    public String bookTitle;    // Titolo del libro consigliato
    public String authorName;   // Nome dell'autore del libro consigliato
    public String category;     // Categoria del libro consigliato

    /**
     * Costruttore base con solo gli ID.
     * Utilizzato quando si hanno solo le informazioni relazionali di base
     * (chi ha consigliato cosa e per quale libro).
     *
     * @param userId ID dell'utente che ha fatto il consiglio
     * @param sourceBookId ID del libro per cui è stato fatto il consiglio
     * @param recommendedBookId ID del libro consigliato
     */
    public RecommendedBook(String userId, int sourceBookId, int recommendedBookId) {
        this.userId = userId;
        this.sourceBookId = sourceBookId;
        this.recommendedBookId = recommendedBookId;
        this.bookTitle = "";
        this.authorName = "";
        this.category = "";
    }

    /**
     * Costruttore semplificato con titolo.
     * Utilizzato principalmente per la visualizzazione rapida dei consigli
     * quando non sono necessari i dettagli completi o gli ID del database.
     *
     * @param userId ID dell'utente che ha fatto il consiglio
     * @param bookTitle Titolo del libro consigliato
     */
    public RecommendedBook(String userId, String bookTitle) {
        this.userId = userId;
        this.bookTitle = bookTitle;
        this.sourceBookId = 0;
        this.recommendedBookId = 0;
        this.authorName = "";
        this.category = "";
    }

    /**
     * Costruttore completo con tutti i dettagli.
     * Utilizzato quando si hanno a disposizione tutte le informazioni sul consiglio
     * e sul libro consigliato, utile per visualizzazioni dettagliate.
     *
     * @param userId ID dell'utente che ha fatto il consiglio
     * @param sourceBookId ID del libro per cui è stato fatto il consiglio
     * @param recommendedBookId ID del libro consigliato
     * @param bookTitle Titolo del libro consigliato
     * @param authorName Nome dell'autore del libro consigliato
     * @param category Categoria del libro consigliato
     */
    public RecommendedBook(String userId, int sourceBookId, int recommendedBookId,
                           String bookTitle, String authorName, String category) {
        this.userId = userId;
        this.sourceBookId = sourceBookId;
        this.recommendedBookId = recommendedBookId;
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.category = category;
    }
}