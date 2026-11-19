package dicegame;

import java.util.*;

/**
 *
 * @author fregi
 */
public class GameController {
    private int spielWuerfelAnzahl;
    private int aktuelleSpielWuerfelAnzahl;
    private int anzahlWuerfelSpieler1;
    private int anzahlWuerfelSpieler2;
    private Wette aktuelleWette;
    private List<Wette> moeglicheWette;
    private Wuerfel wuerfel;

    public static final int WUERFEL_PRO_SPIELER = 5;
    private final List<Spieler> spielerListe = new ArrayList<>();

    public GameController(List<Spieler> spielerListe){
        this.wuerfel = new Wuerfel();
        this.spielerListe.addAll(spielerListe);
    }

    public GameController(int spielWuerfelAnzahl, int aktuelleSpielWuerfelAnzahl, int anzahlWuerfelSpieler1, int anzahlWuerfelSpieler2, Wette aktuelleWette){
        this.wuerfel = new Wuerfel();
        this.spielWuerfelAnzahl = spielWuerfelAnzahl;
        this.aktuelleSpielWuerfelAnzahl = aktuelleSpielWuerfelAnzahl;
        this.anzahlWuerfelSpieler1 = anzahlWuerfelSpieler1;
        this.anzahlWuerfelSpieler2 = anzahlWuerfelSpieler2;
        this.aktuelleWette = aktuelleWette;
        //liste moeglicheWetten
    }

    public List<Spieler> bestimmeReihenfolge(){
        return null;
    }

    public void bestimmeRollen(){

    }

    // Jeder Spieler wuerfelt selbst seine Wuerfel
    public void wuerfeln(){
        for (Spieler spieler : spielerListe) {
            spieler.wuerfeln(wuerfel, WUERFEL_PRO_SPIELER);
        }
    }

    public List<Integer> wuerfeln(Spieler spieler){
        spieler.wuerfeln(wuerfel, WUERFEL_PRO_SPIELER);
        return getWuerfeVonSpieler(spieler);
    }

    public List<Integer> getWuerfeVonSpieler(Spieler spieler){
        return spieler.getGewuerfelteWuerfel();
    }

    public List<List<Integer>> getAlleWuerfe(){
        List<List<Integer>> alleWuerfe = new ArrayList<>();
        for (Spieler spieler : spielerListe) {
            alleWuerfe.add(getWuerfeVonSpieler(spieler));
        }
        return alleWuerfe;
    }

    public void wettBewertungsCheck(){

    }

    public void wuerfelAnzahlAktualisieren(){

    }

    public void entferneSpielerReihenfolge(){

    }
}