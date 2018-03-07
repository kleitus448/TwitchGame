import org.sqlite.SQLiteConfig;

import java.sql.*;

public class CLEANER {
    public static void main(String[] args) throws SQLException {
        open();
        Statement statement = co.createStatement();
        String query = "DELETE FROM users" ;
        statement.execute(query);
        query = "DELETE FROM HeroesItems";
        statement.execute(query);
        close();
    }


    static Connection co = null;

    static synchronized void open() {
        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.setSharedCache(true);
            config.enableRecursiveTriggers(true);
            co = DriverManager.getConnection("jdbc:sqlite:core/database.db",config.toProperties());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void close() {
        try{
            co.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
