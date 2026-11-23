package dicegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {

    private int spielWuerfelAnzahl;
    private int aktuelleSpielWuerfelAnzahl;
    private int anzahlWuerfelSpieler1;
    private int anzahlWuerfelSpieler2;
    private Wette aktuelleWette;
    private List<Wette> moeglicheWette;
    private final Wuerfel wuerfel;
    private final List<Spieler> spielerListe = new ArrayList<>();
    private final Random random = new Random();
    public static final int WUERFEL_PRO_SPIELER = 5;
    private int spielerAmZugIndex = -1;
    private Spieler aktuellerWettsteller;
    private boolean rundeBeendet = false;
    

    public GameController(List<Spieler> spielerListe) {

        this.wuerfel = new Wuerfel();

        this.spielerListe.addAll(spielerListe);
    }

    public GameController(
            int spielWuerfelAnzahl,
            int aktuelleSpielWuerfelAnzahl,
            int anzahlWuerfelSpieler1,
            int anzahlWuerfelSpieler2,
            Wette aktuelleWette
    ) {

        this.wuerfel = new Wuerfel();

        this.spielWuerfelAnzahl = spielWuerfelAnzahl;
        this.aktuelleSpielWuerfelAnzahl = aktuelleSpielWuerfelAnzahl;
        this.anzahlWuerfelSpieler1 = anzahlWuerfelSpieler1;
        this.anzahlWuerfelSpieler2 = anzahlWuerfelSpieler2;
        this.aktuelleWette = aktuelleWette;
    }

    public List<Spieler> bestimmeReihenfolge() {

        return new ArrayList<>(spielerListe);
    }

    public void bestimmeRollen() {

        for (int i = 0; i < spielerListe.size(); i++) {
            spielerListe.get(i).setRollen(
                    i == 0,
                    i == 1
            );
        }
    }
    
    public int getSpielerAmZugIndex() {//test
    if (rundeBeendet) {
        return -1;
    }
        return spielerAmZugIndex;
    }

    // Alle Spieler würfeln
    public void wuerfeln() {
        for (Spieler spieler : spielerListe) {
            spieler.wuerfeln(
                    wuerfel,
                    spieler.getWuerfelAnzahl()
            );
        }
    }

    // Ein einzelner Spieler würfelt
    public List<Integer> wuerfeln(Spieler spieler) {
        spieler.wuerfeln(
                wuerfel,
                spieler.getWuerfelAnzahl()
        );
        return getWuerfeVonSpieler(spieler);
    }

    public List<Integer> getWuerfeVonSpieler(Spieler spieler) {
        return spieler.getGewuerfelteWuerfel();
    }

    // Gibt alle Würfe zurück
    public List<List<Integer>> getAlleWuerfe() {
        List<List<Integer>> alleWuerfe = new ArrayList<>();
        for (Spieler spieler : spielerListe) {
            alleWuerfe.add(getWuerfeVonSpieler(spieler));
        }
        return alleWuerfe;
    }

    // Zählt, wie oft eine bestimmte Augenzahl vorkommt
    public int zaehleAugenzahl(int augenzahl) {
        int treffer = 0;
        for (Spieler spieler : spielerListe) {
            for (int wurf : spieler.getGewuerfelteWuerfel()) {
                if (wurf == augenzahl) {
                    treffer++;
                }
            }
        }
        return treffer;
    }

    
     /* Prüft die Wette
     *
     * true  = Wette richtig
     * false = Wette falsch
     */
    public boolean wettBewertungsCheck(
            Wette wette,
            Spieler wettsteller,
            Spieler zweifler
    ) {

        if (wette == null || wettsteller == null || zweifler == null) {
            throw new IllegalArgumentException("Wette und Spieler duerfen nicht null sein.");
        }

        // Alle Würfel im gesamten Spiel kontrollieren
        int anzahlTreffer = zaehleAugenzahl(wette.getAugenzahl());
        // Prüfen, ob die Wette stimmt.
        boolean wetteIstRichtig =
                anzahlTreffer >= wette.getAnzahl();
        Spieler verlierer;
        if (wetteIstRichtig) {
            // Wette stimmt
            // Zweifler verliert einen Würfel
            verlierer = zweifler;
        } else {
            // Wette stimmt nicht
            // Wettsteller verliert einen Würfel
            verlierer = wettsteller;
        }
        verlierer.verliereZufaelligenWuerfel();
        return wetteIstRichtig;
    }

    /**
     * Der Bot entscheidet über die aktuelle Wette
     *
     * true  = Bot glaubt und erhöht
     * false = Bot zweifelt
     */
    public boolean botGlaubtUndErhoeht(Bot bot) {
        if (bot == null || aktuelleWette == null) {
            throw new IllegalArgumentException(
                    "Bot und aktuelle Wette muessen gesetzt sein."
            );
        }
        // Bot entscheidet.
        boolean glaubt = bot.treffeEntscheidung(
                aktuelleWette,
                getGesamtWuerfelAnzahl()
        );
        // Wenn er nicht glaubt, wird die Wette angezweifelt
        if (!glaubt) {
            return false;
        }
        // Bot versucht die Wette zu erhöhen
        Wette erhoehteWette = bot.erhoeheWette(
                aktuelleWette
        );
        // Falls die höchste Wette erreicht wurde
        if (erhoehteWette == null) {
            return false;
        }
        // Neue Wette wird gespeichert
        aktuelleWette = erhoehteWette;
        return true;
    }
    
    public void setAktuelleWette(Wette wette) {
        this.aktuelleWette = wette;
    }

    public Wette getAktuelleWette() {
        return aktuelleWette;
    }

    // Zählt alle noch vorhandenen Würfel der Spieler
    public int getGesamtWuerfelAnzahl() {
        int gesamt = 0;
        for (Spieler spieler : spielerListe) {
            gesamt += spieler.getWuerfelAnzahl();
        }
        return gesamt;
    }

    public List<Spieler> getSpielerListe() {
        return new ArrayList<>(spielerListe);
    }

    public void wuerfelAnzahlAktualisieren() {
        aktuelleSpielWuerfelAnzahl =
                getGesamtWuerfelAnzahl();
    }

public void starteWettKreislauf() {

    if (spielerListe.size() != 4) {
        throw new IllegalStateException(
                "Der Wettkreislauf benötigt genau 4 Spieler."
        );
    }

    rundeBeendet = false;
    aktuelleWette = null;

    // Der User eröffnet die erste Runde
    aktuellerWettsteller = spielerListe.get(0);
    spielerAmZugIndex = 0;
}



//Bestimmt die häufigste Augenzahl eines Spielers
private int haeufigsteAugenzahl(Spieler spieler) {
    int[] haeufigkeit = new int[7];
    for (int wurf : spieler.getGewuerfelteWuerfel()) {
        haeufigkeit[wurf]++;
    }
    int besteAugenzahl = 1;
    for (int i = 2; i <= 6; i++) {
        if (haeufigkeit[i] > haeufigkeit[besteAugenzahl]) {
            besteAugenzahl = i;
        }
    }
    return besteAugenzahl;
}



//Gibt zurück, wer gerade am Zug ist 
public Spieler getSpielerAmZug() {
    if (spielerAmZugIndex < 0) {
        return null;
    }
    return spielerListe.get(spielerAmZugIndex);
}

//Prüft, ob gerade der menschliche Spieler am Zug ist.
public boolean istSpielerAmZug() {
    return spielerAmZugIndex == 0
            && !rundeBeendet;
}


//Gibt den aktuellen Wettsteller zurück.
public Spieler getAktuellerWettsteller() {
    return aktuellerWettsteller;
}

//Gibt zurück, ob die Runde beendet wurde.
public boolean isRundeBeendet() {
    return rundeBeendet;
}


/**
 * Zum nächsten Spieler wechseln
 * Reihenfolge:
 * Bot 3 -> Bot 2 -> Bot 1 -> Spieler -> Bot 3
 */
private void geheZumNaechstenSpieler() {
    if (spielerAmZugIndex == 0) {
        spielerAmZugIndex = 3;
    } else {
        spielerAmZugIndex--;
    }
    // Spieler ohne Würfel überspringen
    int versuche = 0;
    while (spielerListe.get(spielerAmZugIndex)
            .getWuerfelAnzahl() <= 0
            && versuche < 4) {
        if (spielerAmZugIndex == 0) {
            spielerAmZugIndex = 3;
        } else {
            spielerAmZugIndex--;
        }
        versuche++;
    }
}


/**
 * Automatische Bot-Züge durchführen,
 * bis der Mensch am Zug ist oder Runde endet
 */
public String verarbeiteBotZuegeBisSpieler() {
    StringBuilder meldungen = new StringBuilder();
    int sicherheitsLimit = 50;
    
    while (!rundeBeendet
            && spielerAmZugIndex != 0
            && sicherheitsLimit > 0) {
        
        sicherheitsLimit--;
        Spieler aktuellerSpieler = getSpielerAmZug();
        if (!(aktuellerSpieler instanceof Bot)) {
            break;
        }

        Bot bot = (Bot) aktuellerSpieler;
        boolean glaubt = bot.treffeEntscheidung(aktuelleWette,getGesamtWuerfelAnzahl());

        // Bot zweifelt
        if (!glaubt) {
            boolean wetteRichtig = wettBewertungsCheck(aktuelleWette,aktuellerWettsteller,bot);
            int treffer = zaehleAugenzahl(aktuelleWette.getAugenzahl());

            Spieler verlierer = wetteRichtig ? bot : aktuellerWettsteller;

            meldungen.append(botName(bot) + " zweifelt an. " + (wetteRichtig ? "Wette stimmt! " : "Wette falsch! ") + botName(verlierer) + " verliert einen Würfel. Treffer: " + treffer + "\n");
            String neueRunde = starteNeueRundeNachVerlust(verlierer);
            meldungen.append(neueRunde);
            if (rundeBeendet || spielerAmZugIndex == 0) {
                return meldungen.toString();
            }
            // Bot eröffnet die nächste Runde
            // Der Kreislauf läuft weiter
            continue;
        }

        // Bot versucht zu erhöhen
        Wette erhoehteWette = bot.erhoeheWette(aktuelleWette);

        // Bot kann nicht erhöhen und zweifelt an.
        if (erhoehteWette == null) {
            boolean wetteRichtig = wettBewertungsCheck(aktuelleWette,aktuellerWettsteller,bot);
            int treffer = zaehleAugenzahl(aktuelleWette.getAugenzahl());
            Spieler verlierer = wetteRichtig ? bot : aktuellerWettsteller;
            meldungen.append(botName(bot) + " kann nicht erhöhen und zweifelt an. " + (wetteRichtig ? "Wette stimmt! " : "Wette falsch! ") + botName(verlierer) + " verliert einen Würfel. Treffer: " + treffer + "\n");
            String neueRunde = starteNeueRundeNachVerlust(verlierer);
            meldungen.append(neueRunde);
            if (rundeBeendet || spielerAmZugIndex == 0) {
                return meldungen.toString();
            }
            continue;
        }
        // Wette wurde erhöht
        aktuelleWette = erhoehteWette;
        aktuellerWettsteller = bot;
        meldungen.append(botName(bot) + " erhöht auf " + aktuelleWette.getAnzahl() + "x die " + aktuelleWette.getAugenzahl() + "\n");
        geheZumNaechstenSpieler();
    }

    if (rundeBeendet) {
        meldungen.append("Das Spiel ist beendet.");
    } else if (spielerAmZugIndex == 0) {
        meldungen.append("Du bist wieder am Zug!");
    }
    return meldungen.toString();
}

//Menschlicher Spieler erhöht die Wette
public boolean spielerErhoehtWette(Wette neueWette) {
    if (!istSpielerAmZug() || neueWette == null) {
        return false;
    }
    // Eröffnungswette
    if (aktuelleWette == null) {
        aktuelleWette = neueWette;
        aktuellerWettsteller = spielerListe.get(0);
        geheZumNaechstenSpieler();
        return true;
    }

    // Normale Erhöhung
    if (!istWetteHoeher(neueWette, aktuelleWette)) {
        return false;
    }
    aktuelleWette = neueWette;
    aktuellerWettsteller = spielerListe.get(0);
    geheZumNaechstenSpieler();
    return true;
}

//Menschlicher Spieler zweifelt an

public String spielerZweifeltAn() {
    if (!istSpielerAmZug() || aktuelleWette == null) {
        return "Du bist gerade nicht am Zug.";
    }
    Spieler mensch = spielerListe.get(0);
    boolean wetteRichtig = wettBewertungsCheck(aktuelleWette,aktuellerWettsteller,mensch);
    int treffer = zaehleAugenzahl(aktuelleWette.getAugenzahl());
    Spieler verlierer = wetteRichtig ? mensch : aktuellerWettsteller;
    String auswertung =(wetteRichtig ? "Wette stimmt! " : "Wette falsch! ") + botName(verlierer) + " verliert einen Würfel. Treffer: " + treffer;
    String neueRunde = starteNeueRundeNachVerlust(verlierer);

    if (rundeBeendet || spielerAmZugIndex == 0) {
        return auswertung + "\n" + neueRunde;
    }

    // Falls ein Bot die nächste Runde eröffnet,
    // führen die Bots ihre Züge automatisch aus
    return auswertung + "\n" + neueRunde + "\n" + verarbeiteBotZuegeBisSpieler();
}

private String starteNeueRundeNachVerlust(Spieler verlierer) {
    rundeBeendet = false;
    aktuelleWette = null;
    // Alle würfeln erneut, aber nur mit den verbliebenen Würfeln
    wuerfeln();
    // Prüfen, ob Spiel bereits entschieden ist
    int aktiveSpieler = 0;

    for (Spieler s : spielerListe) {
        if (s.getWuerfelAnzahl() > 0) {
            aktiveSpieler++;
        }
    }

    if (aktiveSpieler <= 1) {
        rundeBeendet = true;
        return "Spiel beendet!";
    }

    int index = spielerListe.indexOf(verlierer);

    // Falls der Verlierer keine Würfel mehr hat,
    // wird der nächste aktive Spieler Eröffner.
    if (index < 0 || verlierer.getWuerfelAnzahl() <= 0) {
        int versuche = 0;
        do {
            index--;
            if (index < 0) {
                index = spielerListe.size() - 1;
            }
            versuche++;
        } while (
                spielerListe.get(index).getWuerfelAnzahl() <= 0
                && versuche < spielerListe.size()
        );
    }
    aktuellerWettsteller = spielerListe.get(index);
    spielerAmZugIndex = index;
    // Mensch eröffnet
    if (index == 0) {
        return "Neue Runde! Du eröffnest die Wette.";
    }
    // Bot eröffnet automatisch mit einer Wette
    Bot bot = (Bot) spielerListe.get(index);
    int augenzahl = haeufigsteAugenzahl(bot);
    aktuelleWette = new Wette(augenzahl, 1);
    String meldung = botName(bot) + " eröffnet mit 1x die " + augenzahl;
    // Nach der Eröffnungswette ist der nächste Spieler dran.
    geheZumNaechstenSpieler();
    return meldung;
}



//Prüft, ob die neue Wette höher ist
private boolean istWetteHoeher(
        Wette neueWette,
        Wette alteWette
) {

    if (neueWette == null || alteWette == null) {
        return false;
    }

    return neueWette.getAnzahl() > alteWette.getAnzahl()|| (neueWette.getAnzahl() == alteWette.getAnzahl()&& neueWette.getAugenzahl()> alteWette.getAugenzahl());
}
    private String botName(Spieler spieler) {
        for (int i = 0; i < spielerListe.size(); i++) {
            if (spielerListe.get(i) == spieler) {
                if (i == 1) return "Bot 1";
                if (i == 2) return "Bot 2";
                if (i == 3) return "Bot 3";
                if (i == 0) return "Du";
            }
        }
        return "Spieler";
    }
}