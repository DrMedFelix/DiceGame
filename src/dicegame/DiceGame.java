package dicegame;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class DiceGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Datenbankschnittstelle db = null;

        try {
            db = new Datenbankschnittstelle();
            boolean laeuft = true;

            while (laeuft) {
                System.out.println();
                System.out.println("=== Benutzerverwaltung ===");
                System.out.println("1 - Benutzernamen speichern");
                System.out.println("2 - Alle Benutzernamen anzeigen");
                System.out.println("3 - Benutzernamen loeschen");
                System.out.println("0 - Beenden");
                System.out.print("Auswahl: ");

                switch (scanner.nextLine().trim()) {
                    case "1":
                        System.out.print("Neuer Benutzername: ");
                        String neu = scanner.nextLine().trim();
                        if (neu.isEmpty()) {
                            System.out.println("Fehler: Der Name darf nicht leer sein!");
                        } else if (db.existiert(neu)) {
                            System.out.println("Fehler: '" + neu + "' existiert schon!");
                        } else {
                            db.speichereBenutzername(neu);
                            System.out.println("Gespeichert: " + neu);
                        }
                        break;

                    case "2":
                        if (db.istLeer()) {
                            System.out.println("Datenbank leer!");
                        } else {
                            List<String> namen = db.ladeAlleBenutzernamen();
                            System.out.println("Gespeicherte Benutzer (" + namen.size() + "):");
                            for (String n : namen) {
                                System.out.println(" - " + n);
                            }
                        }
                        break;

                    case "3":
                        if (db.istLeer()) {
                            System.out.println("Datenbank leer - es gibt nichts zu loeschen!");
                        } else {
                            System.out.print("Welcher Benutzername soll geloescht werden? ");
                            String weg = scanner.nextLine().trim();
                            if (db.loescheBenutzername(weg)) {
                                System.out.println("Geloescht: " + weg);
                            } else {
                                System.out.println("Fehler: '" + weg + "' gibt es nicht!");
                            }
                        }
                        break;

                    case "0":
                        laeuft = false;
                        break;

                    default:
                        System.out.println("Ungueltige Auswahl!");
                }
            }
        } catch (SQLException exc) {
            System.out.println("Datenbankfehler: " + exc.getMessage());
        } finally {
            try {
                if (db != null) {
                    db.beenden();
                }
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
            scanner.close();
            System.out.println("geschlossen");
        }
    }
}