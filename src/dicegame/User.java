package dicegame;

/**
 * Fachklasse: Container fuer alle Daten eines Users (Spielers).
 * Wird in der Tabelle Spieler gespeichert.
 */
public class User {

    private static final int NO_ID = -1;

    private int id;
    private String name;
    private int waehrung;
    private int spielzeit;         // in Sekunden
    private int gewonneneRunden;
    private int verloreneRunden;
    private int winstreak;         // > 0: Siege in Folge, < 0: Niederlagen in Folge

    /**
     * Standardkonstruktor: stellt id auf NO_ID ein. NO_ID ist eine Konstante,
     * die keiner gueltigen Datenbank-ID entspricht.
     */
    public User() {
        id = NO_ID;
    }

    /**
     * Neuer User (noch nicht in der Datenbank), id ist NO_ID,
     * alle Statistiken starten bei 0.
     */
    public User(String name, int waehrung) {
        this();
        this.name = name;
        this.waehrung = waehrung;
    }

    /** Bereits gespeicherter User mit id, Statistiken bei 0. */
    public User(int id, String name, int waehrung) {
        this(name, waehrung);
        this.id = id;
    }

    /** Vollstaendiger Konstruktor, zum Beispiel beim Laden aus der Datenbank. */
    public User(int id, String name, int waehrung, int spielzeit,
            int gewonneneRunden, int verloreneRunden, int winstreak) {
        this(id, name, waehrung);
        this.spielzeit = spielzeit;
        this.gewonneneRunden = gewonneneRunden;
        this.verloreneRunden = verloreneRunden;
        this.winstreak = winstreak;
    }

    /**
     * Legt die id eines bereits erstellten User-Objekts auf einen neuen Wert
     * fest, zum Beispiel wenn der User neu in der Datenbank angelegt wurde.
     */
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setWaehrung(int waehrung) {
        this.waehrung = waehrung;
    }

    public int getWaehrung() {
        return waehrung;
    }

    public void setSpielzeit(int spielzeit) {
        this.spielzeit = spielzeit;
    }

    public int getSpielzeit() {
        return spielzeit;
    }

    public void setGewonneneRunden(int gewonneneRunden) {
        this.gewonneneRunden = gewonneneRunden;
    }

    public int getGewonneneRunden() {
        return gewonneneRunden;
    }

    public void setVerloreneRunden(int verloreneRunden) {
        this.verloreneRunden = verloreneRunden;
    }

    public int getVerloreneRunden() {
        return verloreneRunden;
    }

    public void setWinstreak(int winstreak) {
        this.winstreak = winstreak;
    }

    public int getWinstreak() {
        return winstreak;
    }

    /**
     * Zaehlt eine gewonnene Runde und passt die Winstreak an:
     * nach Niederlagen (oder bei 0) startet sie bei +1, sonst wird sie groesser.
     * Gespeichert wird hier NICHT - dafuer ist die Verwaltung zustaendig.
     */
    public void rundeGewonnen() {
        gewonneneRunden++;
        winstreak = winstreak > 0 ? winstreak + 1 : 1;
    }

    /**
     * Zaehlt eine verlorene Runde und passt die Winstreak an:
     * nach Siegen (oder bei 0) startet sie bei -1, sonst wird sie kleiner.
     */
    public void rundeVerloren() {
        verloreneRunden++;
        winstreak = winstreak < 0 ? winstreak - 1 : -1;
    }

    // Kann der User diesen Betrag bezahlen? (spaeter fuer den Shop)
    public boolean kannBezahlen(int betrag) {
        return waehrung >= betrag;
    }

    @Override
    public String toString() {
        return name + " (id=" + id + ", Waehrung=" + waehrung
                + ", Spielzeit=" + spielzeit + "s"
                + ", gewonnen=" + gewonneneRunden
                + ", verloren=" + verloreneRunden
                + ", Winstreak=" + winstreak + ")";
    }
}