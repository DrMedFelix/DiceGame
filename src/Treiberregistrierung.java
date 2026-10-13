
import java.sql.*;

public class Treiberregistrierung {

    public static void treiberRegistrieren(String args[]) {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException exc) {
            exc.printStackTrace();
            System.exit(-1);
        }
    }
}
