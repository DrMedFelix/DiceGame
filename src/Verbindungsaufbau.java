import java.sql.*;

public class Verbindungsaufbau {
    public void verbindungAufbauen() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException exc) {
            exc.printStackTrace();
            System.exit(-1);
        }

        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:hsqldb:file:data/DiceGameDB;ifexists=true", "SA", "");
            System.out.println("Verbindung erfolgreich!");
            conn.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
            System.exit(-1);
        }
    }
}