package dicegame;

/**
 * Fachklasse: Container fuer alle Daten eines Users.
 * Wird in der Tabelle Spieler gespeichert.
 */
public class User extends Spieler {

    private static final int NO_ID = -1;

    private int id;
    private String name;
    private int waehrung;
    private int spielzeit;
    private int gewonneneRunden;
    private int verloreneRunden;
    private int winstreak;

    // Neuer User ohne Name und Waehrung
    public User() {
        super();
        this.id = NO_ID;
    }

    // Neuer User mit Name und Waehrung
    public User(String name, int waehrung) {
        super();
        this.id = NO_ID;
        this.name = name;
        this.waehrung = waehrung;
    }

    // Bereits gespeicherter User
    public User(int id, String name, int waehrung) {
        this(name, waehrung);
        this.id = id;
    }

    // Bereits gespeicherter User mit allen Statistikwerten
    public User(int id, String name, int waehrung, int spielzeit,
                int gewonneneRunden, int verloreneRunden, int winstreak) {
        this(id, name, waehrung);
        this.spielzeit = spielzeit;
        this.gewonneneRunden = gewonneneRunden;
        this.verloreneRunden = verloreneRunden;
        this.winstreak = winstreak;
    }

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

    public void rundeGewonnen() {
        gewonneneRunden++;
        winstreak = winstreak > 0 ? winstreak + 1 : 1;
    }

    public void rundeVerloren() {
        verloreneRunden++;
        winstreak = winstreak < 0 ? winstreak - 1 : -1;
    }

    public boolean kannBezahlen(int betrag) {
        return betrag >= 0 && waehrung >= betrag;
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