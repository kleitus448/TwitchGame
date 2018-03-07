package com.ludux.twitchgame.Server.JobWithDATABASE;

import com.ludux.twitchgame.Server.Connection.Rebuilder;
import com.ludux.twitchgame.Server.Items.Item;
import com.ludux.twitchgame.Server.Monsters.Monster;
import org.sqlite.SQLiteConfig;
import java.sql.*;
import java.util.ArrayList;


public class Search {

    public synchronized static Someone search(String user) {
        Search.open();
        Someone result = Search.selectPlayer(user);
        Search.close();
        return result;
    }

    public synchronized static Someone searchItems(String hero) {
        Search.open();
        Someone result = Search.selecthero(hero);
        Search.close();
        return result;
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

    static Someone selectPlayer(String user){
        Someone res =  new Someone();
        try{
            Statement statement = co.createStatement();
            String query = "SELECT * FROM users WHERE user = '" +  user + "' " ;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                //System.out.println("in select player");
                res.name = rs.getString("nickname");
                res.rasa = rs.getString("rasa");
                res.level = rs.getInt("level");
                res.Heroes = new Rebuilder().StringToArrayList(rs.getString("Heroes"));
            }
            rs.close();
            statement.close();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public synchronized void addItem(Monster monster,ArrayList<Item> invent) throws SQLException {
        open();
        Rebuilder rebuilder =  new Rebuilder();
        String JInventory = rebuilder.ArrayToString(rebuilder.DeConvert(invent));
        Statement statement = co.createStatement();
        String query = "UPDATE HeroesItems SET HeroInventory = '"+ JInventory +"' WHERE Hero = '" + monster.getname() + "'";
        statement.executeUpdate(query);
        statement.close();
        close();
    }

    public static synchronized Someone selecthero(String hero){
        Someone res =  new Someone();
        System.out.println(hero);
        try{
            Statement statement = co.createStatement();
            String query = "SELECT * FROM HeroesItems WHERE Hero = '" +  hero + "' " ;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.println("in select hero");
                Rebuilder rebuilder = new Rebuilder();
                String q = rs.getString("HeroInventory");
                System.out.println(q);
                ArrayList<String> as = rebuilder.StringToArrayList(q);
                for (String s:as) {
                    System.out.println(s);
                }
                res.Inventory = rebuilder.Convert(rebuilder.StringToArrayList(q));
                System.out.println(res.Inventory);
            }
            rs.close();
            statement.close();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
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
