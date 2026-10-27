/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dicegame;

/**
 *
 * @author fregi
 */
public class Wuerfel {
    private int augenzahl;
    private int seltenheit;
    private int preis;
    private String name;
    private String itemTyp;
    private int verkaufspreis;
    
    public Wuerfel(int augenzahl, int seltenheit, int preis, String name, String itemTyp, int verkaufspreis){
        this.augenzahl = augenzahl;
        this.seltenheit = seltenheit;
        this.preis = preis;
        this.name = name;
        this.itemTyp = itemTyp;
        this.verkaufspreis = verkaufspreis;
    }
    
    public int getAugenzahl(){
        return augenzahl;
    }
    
    public int getSeltenheit(){
        return seltenheit;
    }
    
    public int getPreis(){
        return preis;
    }
    
    public String getName(){
        return name;
    }
    
    public String getitemTyp(){
        return itemTyp;
    }
    
    public int verkaufsPreis(){
        return verkaufspreis;
    }
    
    public void setAugenzahl(int augenzahl){
        this.augenzahl = augenzahl;
    }
    
    public void setSeltenheit(int seltenheit){
        this.seltenheit = seltenheit;
    }
    
    public void setPreis(int preis){
        this.preis = preis;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setitemTyp(String itemTyp){
        this.itemTyp = itemTyp;
    }
    
    public void setVerkaufsPreis(int verkaufspreis){
        this.verkaufspreis = verkaufspreis;
    }
    
}
