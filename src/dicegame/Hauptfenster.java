package dicegame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Hauptfenster extends JFrame {

    private static final Color GRUEN = new Color(0, 130, 0);
    private static final Color ROT = new Color(190, 0, 0);

    private final UserVerwaltung verwaltung = UserVerwaltung.getInstance();

    private final JTextField nameFeld = new JTextField(20);
    private final JButton speichernButton = new JButton("Starten");
    private final JButton suchenButton = new JButton("Suche nach");
    private final JLabel feedbackLabel = new JLabel(" ", SwingConstants.CENTER);

    public Hauptfenster() {
        super("DiceGame");
        baueOberflaeche();
    }

    private void baueOberflaeche() {
        JPanel knoepfe = new JPanel(new GridLayout(1, 2, 10, 0));
        knoepfe.add(speichernButton);
        knoepfe.add(suchenButton);

        JPanel oben = new JPanel(new GridLayout(3, 1, 0, 10));
        oben.add(new JLabel("Benutzername:"));
        oben.add(nameFeld);
        oben.add(knoepfe);

        feedbackLabel.setPreferredSize(new Dimension(300, 150));

        JPanel inhalt = new JPanel(new BorderLayout(0, 10));
        inhalt.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inhalt.add(oben, BorderLayout.NORTH);
        inhalt.add(feedbackLabel, BorderLayout.CENTER);
        setContentPane(inhalt);

        speichernButton.addActionListener(e -> userSpeichern());
        suchenButton.addActionListener(e -> userSuchen());
        getRootPane().setDefaultButton(speichernButton); 

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); 
    }

    private void userSpeichern() {
        String name = nameFeld.getText().trim();

        if (name.isEmpty()) {
            zeigeFehler("Bitte einen Namen eingeben!");
            return;
        }
        
        User vorhanden = verwaltung.sucheUser(name);
        if (vorhanden != null) {
            zeigeErfolg("Willkommen, " + vorhanden.getName() + "!");
            return;
        }

        User neu = verwaltung.neuerUser(name, 0);
        if (neu != null) {
            zeigeErfolg("Name gespeichert: " + neu.getName());
        } else if (name.length() > UserVerwaltung.MAX_NAME_LAENGE) {
            zeigeFehler("Der Name ist zu lang (max. " + UserVerwaltung.MAX_NAME_LAENGE + " Zeichen)!");
        } else {
            zeigeFehler("Der Name konnte nicht gespeichert werden!");
        }
    }

    private void userSuchen() {
        String name = nameFeld.getText().trim();

        if (name.isEmpty()) {
            zeigeFehler("Bitte einen Namen eingeben!");
            return;
        }

        User gefunden = verwaltung.sucheUser(name);
        if (gefunden != null) {
            zeigeErfolg("<html><center>Gefunden:<br>"
                    + "Name: " + gefunden.getName() + "<br>"
                    + "ID: " + gefunden.getId() + "<br>"
                    + "W\u00e4hrung: " + gefunden.getWaehrung() + "<br>"
                    + "Spielzeit: " + formatSpielzeit(gefunden.getSpielzeit()) + "<br>"
                    + "Runden: " + gefunden.getGewonneneRunden() + " gewonnen, "
                    + gefunden.getVerloreneRunden() + " verloren<br>"
                    + "Winstreak: " + formatStreak(gefunden.getWinstreak())
                    + "</center></html>");
        } else {
            zeigeFehler("Kein User mit dem Namen '" + name + "' gefunden!");
        }
    }

    private String formatSpielzeit(int sekunden) {
        int h = sekunden / 3600;
        int min = (sekunden % 3600) / 60;
        int s = sekunden % 60;
        return h + " h " + String.format("%02d", min) + " min " + String.format("%02d", s) + " s";
    }

    private String formatStreak(int streak) {
        return streak > 0 ? "+" + streak : String.valueOf(streak);
    }


    private void zeigeErfolg(String text) {
        feedbackLabel.setForeground(GRUEN);
        feedbackLabel.setText(text);
    }

    private void zeigeFehler(String text) {
        feedbackLabel.setForeground(ROT);
        feedbackLabel.setText(text);
    }
}