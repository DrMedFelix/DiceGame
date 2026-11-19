package dicegame;

import java.util.ArrayList;
import java.util.List;

public class Spieler {

    private static final int STANDARD_ANZAHL_WUERFEL = 5;

    private boolean rolleSpieler1;
    private boolean rolleSpieler2;

    // Augenzahlen (1-6) der Wuerfel, die der Spieler in der aktuellen Runde gewuerfelt hat
    private final List<Integer> gewuerfelteWuerfel = new ArrayList<>();

    public Spieler() {
        this.rolleSpieler1 = false;
        this.rolleSpieler2 = false;
    }

    public void wuerfeln(Wuerfel wuerfel) {
        wuerfeln(wuerfel, STANDARD_ANZAHL_WUERFEL);
    }

    public void wuerfeln(Wuerfel wuerfel, int anzahlWuerfel) {
        gewuerfelteWuerfel.clear();
        for (int i = 0; i < anzahlWuerfel; i++) {
            gewuerfelteWuerfel.add(wuerfel.getAugenzahl());
        }
    }

    public List<Integer> getGewuerfelteWuerfel() {
        return gewuerfelteWuerfel;
    }

    // Rollen fuer die aktuelle Runde festlegen
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