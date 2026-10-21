package dicegame;

import javax.swing.SwingUtilities;

public class DiceGame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Hauptfenster().setVisible(true));
    }
}