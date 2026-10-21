/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dicegame;

/**
 *
 * @author fregi
 */
public class Wette {
    private int augenzahl;
    private int anzahl;
    
    public Wette(int augenzahl, int anzahl){
        this.anzahl = anzahl;
        this.augenzahl = augenzahl;
    }
    
    public int getAugenzahl(){
        return augenzahl;
    }
    
    public int getAnzahl(){
        return anzahl;
    }
    
    public void setAugenzahl(int augenzahl){
        this.augenzahl = augenzahl;
    }
    
    public void setAnzahl(int anzahl){
        this.anzahl = anzahl;
    }
}
