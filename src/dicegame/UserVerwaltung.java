package dicegame;

import java.sql.SQLException;
import java.util.List;

/**
 * Logikebene: verarbeitet Eingaben der Praesentationsschicht und
 * speichert/liest ueber die Datenbankschnittstelle. Sie ist ein Singleton.
 * Um die Behandlung der SQLException kuemmert sich diese Schicht.
 */
public class UserVerwaltung {

    // So lang darf ein Name hoechstens sein (VARCHAR(50) in der Datenbank)
    public static final int MAX_NAME_LAENGE = 50;

    private static UserVerwaltung instance;

    public static UserVerwaltung getInstance() {
        if (instance == null) {
            instance = new UserVerwaltung();
        }
        return instance;
    }

    private UserVerwaltung() {
    }

    /**
     * @return alle User (leere Liste = Datenbank leer), null bei Datenbankfehler
     */
    public List<User> alleUser() {
        List<User> alle = null;
        try {
            alle = Datenbankschnittstelle.getInstance().alleUser();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return alle;
    }

    /** @return true, wenn die Datenbank erreichbar und leer ist */
    public boolean istLeer() {
        List<User> alle = alleUser();
        return alle != null && alle.isEmpty();
    }

    /** @return der User mit diesem Namen oder null (nicht gefunden / Fehler) */
    public User sucheUser(String name) {
        User user = null;
        try {
            user = Datenbankschnittstelle.getInstance().sucheUserNachName(name);
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return user;
    }

    public boolean userExistiert(String name) {
        return sucheUser(name) != null;
    }

    /**
     * Legt einen neuen User an.
     *
     * @return der neue User (mit id) oder null, wenn der Name leer ist, schon
     *         vergeben ist, die Waehrung negativ ist oder ein Datenbankfehler auftrat
     */
    public User neuerUser(String name, int waehrung) {
        User user = null;
        if (name == null || name.trim().isEmpty() || name.length() > MAX_NAME_LAENGE || waehrung < 0) {
            return null;
        }
        try {
            if (Datenbankschnittstelle.getInstance().sucheUserNachName(name) == null) {
                user = new User(name, waehrung);
                if (!Datenbankschnittstelle.getInstance().neuerUser(user)) {
                    user = null;
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return user;
    }

    /**
     * Aendert Name und Waehrung eines bestehenden Users. Zulaessig, wenn der
     * Name nicht leer ist und sich nicht vom bisherigen unterscheidet oder
     * noch von keinem anderen User benutzt wird.
     *
     * @return true bei Erfolg
     */
    public boolean userAendern(User user, String name, int waehrung) {
        boolean result = false;
        if (name == null || name.trim().isEmpty() || name.length() > MAX_NAME_LAENGE || waehrung < 0) {
            return false;
        }
        try {
            Datenbankschnittstelle db = Datenbankschnittstelle.getInstance();
            if (name.equals(user.getName()) || db.sucheUserNachName(name) == null) {
                user.setName(name);
                user.setWaehrung(waehrung);
                result = db.speichereUser(user);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }

    /** @return true, wenn der User geloescht wurde */
    public boolean userLoeschen(User user) {
        boolean result = false;
        try {
            result = Datenbankschnittstelle.getInstance().loescheUser(user);
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }
}