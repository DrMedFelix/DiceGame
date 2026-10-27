/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dicegame;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author kracc
 */
public class WuerfelVerwaltung {
    private static WuerfelVerwaltung instance;

    public static WuerfelVerwaltung getInstance() {
        if (instance == null) {
            instance = new WuerfelVerwaltung();
        }
        return instance;
    }
    
private WuerfelVerwaltung() {
    }

public List<Wuerfel> alleWuerfel() {
        try {
            return Datenbankschnittstelle.getInstance().alleWuerfel();
        } catch (SQLException exc) {
            exc.printStackTrace();
            return null;
        }
    }    
    
    
    
    
    
    
}
