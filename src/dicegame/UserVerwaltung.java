
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
        try {
            return Datenbankschnittstelle.getInstance().alleUser();
        } catch (SQLException exc) {
            exc.printStackTrace();
            return null;
        }
    }

    public boolean istLeer() {
        List<User> alle = alleUser();
        return alle != null && alle.isEmpty();
    }

    public User sucheUser(String name) {
        try {
            return Datenbankschnittstelle.getInstance()
                    .sucheUserNachName(name);
        } catch (SQLException exc) {
            exc.printStackTrace();
            return null;
        }
    }

    public boolean userExistiert(String name) {
        return sucheUser(name) != null;
    }

    public User neuerUser(String name, int waehrung) {
        if (name == null || name.trim().isEmpty()
                || name.length() > MAX_NAME_LAENGE || waehrung < 0) {
            return null;
        }

        try {
            Datenbankschnittstelle db =
                    Datenbankschnittstelle.getInstance();

            if (db.sucheUserNachName(name) != null) {
                return null;
            }

            User user = new User(name, waehrung);

            if (db.neuerUser(user)) {
                return user;
            }

        } catch (SQLException exc) {
            exc.printStackTrace();
        }

        return null;
    }

    public boolean userAendern(User user, String name, int waehrung) {
        if (user == null || name == null || name.trim().isEmpty()
                || name.length() > MAX_NAME_LAENGE || waehrung < 0) {
            return false;
        }

        try {
            Datenbankschnittstelle db =
                    Datenbankschnittstelle.getInstance();

            if (name.equals(user.getName())
                    || db.sucheUserNachName(name) == null) {

                user.setName(name);
                user.setWaehrung(waehrung);

                return db.speichereUser(user);
            }

        } catch (SQLException exc) {
            exc.printStackTrace();
        }

        return false;
    }

    public boolean userLoeschen(User user) {
        if (user == null) {
            return false;
        }

        try {
            return Datenbankschnittstelle.getInstance()
                    .loescheUser(user);
        } catch (SQLException exc) {
            exc.printStackTrace();
            return false;
        }
    }
}
