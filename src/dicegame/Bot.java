package dicegame;

import java.util.Random;

public class Bot extends Spieler {

    private final Random random = new Random();

    /**
     * 50/50 Entscheidung:
     * true  = erhöhen
     * false = zweifeln
     */
    public boolean treffeEntscheidung(Wette wette, int gesamtWuerfel) {
        return random.nextBoolean();
    }

    /**
     * Erhöht die aktuelle Wette.
     */
    public Wette erhoeheWette(Wette bisherigeWette) {

        if (bisherigeWette == null) {
            return new Wette(1, 1);
        }

        int menge = bisherigeWette.getAnzahl();
        int augenzahl = bisherigeWette.getAugenzahl();

        if (menge < 20) {
            return new Wette(augenzahl, menge + 1);
        }

        if (augenzahl < 6) {
            return new Wette(augenzahl + 1, menge);
        }

        return null;
    }
}