package dicegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spieler {

    private static final int STANDARD_ANZAHL_WUERFEL = 5;

    private boolean rolleSpieler1;
    private boolean rolleSpieler2;

    private int wuerfelAnzahl = STANDARD_ANZAHL_WUERFEL;

    private final List<Integer> gewuerfelteWuerfel = new ArrayList<>();
    private final Random random = new Random();

    public Spieler() {
        rolleSpieler1 = false;
        rolleSpieler2 = false;
    }

    public void wuerfeln(Wuerfel wuerfel) {
        wuerfeln(wuerfel, wuerfelAnzahl);
    }

    public void wuerfeln(Wuerfel wuerfel, int anzahlWuerfel) {
        gewuerfelteWuerfel.clear();

        int tatsaechlicheAnzahl = Math.min(anzahlWuerfel, wuerfelAnzahl);

        for (int i = 0; i < tatsaechlicheAnzahl; i++) {
            gewuerfelteWuerfel.add(wuerfel.getAugenzahl());
        }
    }

    public List<Integer> getGewuerfelteWuerfel() {
        return gewuerfelteWuerfel;
    }

    public int getWuerfelAnzahl() {
        return wuerfelAnzahl;
    }

    public Integer verliereZufaelligenWuerfel() {
        if (gewuerfelteWuerfel.isEmpty() || wuerfelAnzahl <= 0) {
            return null;
        }

        int index = random.nextInt(gewuerfelteWuerfel.size());

        Integer verloren = gewuerfelteWuerfel.remove(index);

        wuerfelAnzahl--;

        return verloren;
    }

    public void setWuerfelAnzahl(int anzahl) {
        if (anzahl < 0) {
            throw new IllegalArgumentException(
                    "Die Wuerfelanzahl darf nicht negativ sein."
            );
        }

        wuerfelAnzahl = anzahl;

        if (gewuerfelteWuerfel.size() > anzahl) {
            gewuerfelteWuerfel.subList(anzahl, gewuerfelteWuerfel.size()).clear();
        }
    }

    public void setRollen(boolean rolleSpieler1, boolean rolleSpieler2) {
        this.rolleSpieler1 = rolleSpieler1;
        this.rolleSpieler2 = rolleSpieler2;
    }

    public boolean isRolleSpieler1() {
        return rolleSpieler1;
    }

    public boolean isRolleSpieler2() {
        return rolleSpieler2;
    }

    public boolean wetteBewerten() {
        return true;
    }

    public boolean wetteErhoehen() {
        return true;
    }

    public boolean wetteAbschliessen() {
        return true;
    }

    public boolean alleWuerfelWuerfeln() {
        return true;
    }
}