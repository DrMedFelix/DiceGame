package dicegame;

import javax.swing.SwingUtilities;

public class DiceGame {

    public static void main(String[] args) {
        // Oberflaeche im Swing-Thread starten
        SwingUtilities.invokeLater(() -> new Hauptfenster().setVisible(true));

        // Zum Testen im Terminal stattdessen: new Hauptmenue().starten();
    }
}