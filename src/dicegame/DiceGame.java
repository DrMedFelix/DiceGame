package dicegame;

public class DiceGame {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            HauptHintergrundFenster mainFrame = new HauptHintergrundFenster();
            mainFrame.setVisible(true);
        });
    }
}