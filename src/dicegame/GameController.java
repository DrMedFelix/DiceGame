/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    // Jeder Spieler bekommt 5 neue Zufallszahlen
    public void wuerfeln(){
        for (Spieler spieler : spielerListe) {
            wuerfeln(spieler);
        }
    }

    public List<Integer> wuerfeln(Spieler spieler){
        List<Integer> wuerfe = spieler.getGewuerfelteWuerfel();
        wuerfe.clear();
        for (int i = 0; i < WUERFEL_PRO_SPIELER; i++) {
            wuerfe.add(wuerfel.getAugenzahl());
        }
        return wuerfe;
    }
    
    public void wettBewertungsCheck(){
        
    }
    public void wuerfelAnzahlAktualisieren(){
        
    }
    public void entferneSpielerReihenfolge(){
        
    }
}