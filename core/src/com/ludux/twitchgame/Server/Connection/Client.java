package com.ludux.twitchgame.Server.Connection;

import com.badlogic.gdx.Gdx;
import com.ludux.twitchgame.*;
import com.ludux.twitchgame.Sended;
import com.ludux.twitchgame.command.CommandListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.ParseException;

public class Client implements Runnable {


    private Socket connection;
    private OutputSerial output;
    private ObjectInputStream input;
    public String name;
    private boolean enter;
    public String unicName;

    public void setFlag(Boolean flag) {
        this.flag.set(flag);
    }

    private ThreadLocal<Boolean> flag = new ThreadLocal<>();

    public OutputSerial getOutput() {
        return output;
    }
    public Socket getConnection() {
        return connection;
    }
    public Client(Sended q, String uniq) throws ParseException {
        unicName = uniq;
        try {
            connection = new Socket(InetAddress.getByName("127.0.0.1"), 8000);
            output = new OutputSerial(connection.getOutputStream());
            input = new ObjectInputStream(connection.getInputStream());
            this.enter = true;
            this.name = q.sender;
            sendData(q);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Main app = ((Main) Gdx.app.getApplicationListener());
        flag.set(true);
        try {
            while (flag.get()) {
                System.out.println("Я слушаю сервер");
                Sended ans = (Sended) input.readObject();
                //System.out.println(ans.action);
                switch (ans.action){
                    case "true":{
                        System.out.println(this.name);
                        //sendData(new Sended(this.name,"change",this.name));
                        app.setCommandListener(new CommandListener(this));
                        app.getCommandListener().setCommand(Const.CHANGE_SCREEN, Const.TO_MENU_SCREEN);
                        sendData(new Sended(this.name,"break",""));//закрывает всё
                        this.Close();
                        System.out.println("ЗАКРЫЛ СОЕДИНЕНИЕ НА КЛИЕНТЕ");
                        break;
                    }

                    case "false":{
                        app.getStage().addActor(Message.WRONG_LOGIN());
                        sendData(new Sended(this.name,"break",""));
                        this.Close();
                        System.out.println("ЗАКРЫЛ СОЕДИНЕНИЕ НА КЛИЕНТЕ");
                        break;
                    }

                    case "You are in battle": {
                        System.out.println("\n" + ans.battle.getBattleQueueOutputs() + "\n");
                        if (this.enter){
                            System.out.println("получил сообщение что в битве");
                            enter=false;
                            System.out.println("I am here");
                            app.getCommandListener().setBattle(ans.battle);
                            app.getCommandListener().setClient(this);
                            app.getCommandListener().setCommand(Const.CHANGE_SCREEN, Const.TO_BATTLE_SCREEN);
                        }
                        break;
                    }

                    case Const.IN_BATTLE : {
                        System.out.println("Working");
                        app.getCommandListener().setBattle(ans.battle);
                        System.out.println(ans.battle.First.getError() + " CF");
                        System.out.println(ans.battle.Second.getError() + " CS");
                        if (!ans.action.isEmpty() && !ans.information.isEmpty()) {
                            System.out.println("translatingmessage");
                            app.getCommandListener().setCommand(Const.IN_BATTLE, ans.information);
                        }
                        break;
                    }

                    case "break":
                        Close();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Close(){
        this.flag.set(false);
        try {
            output.close();
            input.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendData(Object obj){
        try {
            System.out.println(this.name);
            System.out.println(this.unicName);
            System.out.println(((Sended)obj).action);
            output.flush();
            output.writeObject(obj);
            System.out.println("Закрыто ли соединение  " + connection.isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}