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
    
    public GameController(int spielWuerfelAnzahl, int aktuelleSpielWuerfelAnzahl, int anzahlWuerfelSpieler1, int anzahlWuerfelSpieler2, Wette aktuelleWette){
        this.spielWuerfelAnzahl = spielWuerfelAnzahl;
        this.aktuelleSpielWuerfelAnzahl = aktuelleSpielWuerfelAnzahl;
        this.anzahlWuerfelSpieler1 = anzahlWuerfelSpieler1;
        this.anzahlWuerfelSpieler2 = anzahlWuerfelSpieler2;
        this.aktuelleWette = aktuelleWette;
        //liste moeglicheWetten
    }
    
    public void bestimmeRollen(){
        
    }
    public void wuerfeln(){
            new Wuerfel(1,1,1,"testwürfel","passiv",2);
    }
    public void wettBewertungsCheck(){
        
    }
    public void wuerfelAnzahlAktualisieren(){
        
    }
    public void entferneSpielerReihenfolge(){
        
    }
}
