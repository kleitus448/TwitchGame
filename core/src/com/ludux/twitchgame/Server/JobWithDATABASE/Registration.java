package com.ludux.twitchgame.Server.JobWithDATABASE;


import java.sql.*;
import java.util.ArrayList;

import com.ludux.twitchgame.Server.Connection.Rebuilder;
import com.ludux.twitchgame.Server.Items.Item;
import com.ludux.twitchgame.Server.Items.Kleidung.OldHauberk;
import com.ludux.twitchgame.Server.Items.Magic.HealingRing;
import org.sqlite.SQLiteConfig;


public class Registration {
    static Connection co = null;

    public synchronized boolean CheckLogin(String name, String pass) throws SQLException {
        open();
        System.out.println("Я чекаю логин " + name + " " + pass);
        if (Check(name)){
            if (CheckPass(name,pass)){
                System.out.println("Я чекаю пароль");
                close();
                return true;
            }

        }
        close();
        return false;
    }

    public synchronized boolean CheckPass(String name, String pass)  {
        boolean res = false;
        Statement statement = null;
        try {
            statement = co.createStatement();
            String query = "SELECT * FROM users WHERE user = '" + name + "' ";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String r = rs.getString("password");
                if (pass.equals(r)){
                    res = true;
                    break;
                }
            }
            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }


    public synchronized void ChangeStatus(String name, String status) throws SQLException {
        open();
        if (Check(name)){
            System.out.println("Я в изменении статуса");
            Statement statement = co.createStatement();
            String query = "UPDATE users SET inGame = '"+ status+"' WHERE user = '" + name + "'";
            statement.executeUpdate(query);
            System.out.println("отправил команду");
            query = "SELECT * FROM users WHERE user = '" + name + "' ";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()){
                System.out.println(rs.getString("inGame"));
            }
            rs.close();
            statement.executeUpdate(query);
            System.out.println("Изменил статус");
            statement.close();
        }
        close();
    }

    public synchronized  boolean Check(String username){
        boolean  res = QQ(username);
        System.out.println("Я чекнул имя и результат " + res);
        return res;
    }

    public synchronized void add(String User_name, String name, String rasa, String pass){
        open();
        include(User_name, name,rasa, pass);
        close();
    }

    private synchronized void include(String User_name, String name, String rasa, String pass) {
        try {
            Statement statement = co.createStatement();
            String query = "INSERT INTO users(user, nickname, password, rasa, level, inGame) VALUES ('" + User_name + "', '" + name + "', '" + pass + "', '" + rasa + "', 1, 'out')";
            statement.executeUpdate(query);
            ArrayList<Item> a = new ArrayList<>();
            a.add(new HealingRing());
            OldHauberk oldHauberk = new OldHauberk();
            oldHauberk.setLevelup(60);
            a.add(oldHauberk);
            query = "INSERT INTO HeroesItems(Hero, HeroInventory) VALUES ('" + User_name + "elf', " + "'"+ new Rebuilder().ArrayToString(new Rebuilder().DeConvert(a)) +"')";
            statement.executeUpdate(query);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
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

    static synchronized boolean QQ(String user) {
        boolean res;
        try {
            Statement statement = co.createStatement();
            String query = "SELECT * FROM users WHERE user = '" + user + "' ";
            ResultSet rs = statement.executeQuery(query);
            res =  rs.next();
            rs.close();
            statement.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            res = false;
        }
        //System.out.println(res);
        return res;
    }
    static synchronized void close() {
        try{
            co.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
   public synchronized boolean checkStatus(String name) {
        boolean result = false;
       try {
           open();
           System.out.println("Я в чек статусе");
           Statement statement = co.createStatement();
           String query = "SELECT inGame FROM users WHERE user = '" + name + "'";
           ResultSet rs = statement.executeQuery(query);
           System.out.println("Отправил запрос");
           if (rs.next()) {
               String flag = rs.getString("inGame");
               System.out.println(flag);
               if (flag.equals("in")) {
                   result = false;
               } else result = true;
           }
           close();
       } catch (SQLException e) {
           result = true;
           e.printStackTrace();
       }
        return result;
   }


}