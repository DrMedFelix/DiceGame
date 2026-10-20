package dicegame;

import java.sql.SQLException;
import java.util.List;

public class UserVerwaltung {

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

    public List<User> alleUser() {
        List<User> alle = null;
        try {
            alle = Datenbankschnittstelle.getInstance().alleUser();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return alle;
    }

    public boolean istLeer() {
        List<User> alle = alleUser();
        return alle != null && alle.isEmpty();
    }

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