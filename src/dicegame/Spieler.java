
package dicegame;

public class Spieler {

    private boolean rolleSpieler1;
    private boolean rolleSpieler2;

    public Spieler() {
        this.rolleSpieler1 = false;
        this.rolleSpieler2 = false;
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
