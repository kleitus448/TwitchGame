package com.ludux.twitchgame.notinuse;


import com.ludux.twitchgame.OutputSerial;
import com.ludux.twitchgame.Sended;
import com.ludux.twitchgame.Server.Connection.ClientHandler;
import com.ludux.twitchgame.Server.Connection.PreparingBattle;
import com.ludux.twitchgame.Server.JobWithDATABASE.Registration;
import com.ludux.twitchgame.Server.Server;

import java.io.ObjectInputStream;
import java.net.Socket;

public class BattleHandler implements Runnable {
    private Server server;
    private Socket Client1 = null;
    private Socket Client2 = null;
    private OutputSerial outMessagePlayer1;
    private ObjectInputStream inMessagePlayer1;
    private OutputSerial outMessagePlayer2;
    private ObjectInputStream inMessagePlayer2;
    private ClientHandler Player1;
    private ClientHandler Player2;
    private ThreadLocal<String> flag = new ThreadLocal<>();

    public BattleHandler(ClientHandler Player1, ClientHandler Player2, Server server){
        try {
            this.Player1 = Player1;
            this.Player2 = Player2;
            this.server = server;
            this.Client1 = Player1.getClientSocket();
            this.Client2 = Player2.getClientSocket();
            this.outMessagePlayer1 =  Player1.getOutMessage() ;
            this.outMessagePlayer2 =  Player2.getOutMessage();
            this.inMessagePlayer1  =  Player1.getInMessage();
            this.inMessagePlayer2  =  Player2.getInMessage();
            System.out.println(this.inMessagePlayer1);
            System.out.println(this.inMessagePlayer2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        try {
            this.flag.set("true");
            System.out.println("В баттлхендлере");
            System.out.println(this.flag.get().equals("true"));
            while (this.flag.get().equals("true")) {
                System.out.println("ggggddddd");
                String clientMessage1 = "";
                String clientMessage2 = "";
                Sended sended1 = null;
                if (!(Client1.isClosed())) {
                    System.out.println("ЭЭЭЭ брат я в первом условии");
                    sended1 = (Sended) inMessagePlayer1.readObject();
                }
                System.out.println("проверил первый клиент");
                Sended sended2 = null;
                if (!(Client2.isClosed())) {
                    sended2 = (Sended) inMessagePlayer2.readObject();
                }
                System.out.println("проверил второй клиент");
                System.out.println(this.inMessagePlayer1);
                System.out.println(this.inMessagePlayer2);

                PreparingBattle battle = null;
                String name1 = null;
                String name2 = null;
                try {
                    battle = sended1.battle;
                    clientMessage1 = sended1.action;
                    name1 = sended1.sender;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    clientMessage2 = sended2.action;
                    name2 = sended2.sender;
                } catch (Exception e) {
                }

                System.out.println(clientMessage1);
                System.out.println(clientMessage2);

                switch (clientMessage1) {
                    case "attack":
                        outMessagePlayer1.writeObject(new Sended("", "huia", "", battle));
                        outMessagePlayer2.writeObject(new Sended("", "huia", "", battle));
                        outMessagePlayer1.flush();
                        outMessagePlayer2.flush();
                        break;

                    case "break":
                        this.Client1.close();
                        this.outMessagePlayer1.close();
                        this.inMessagePlayer1.close();
                        this.Player1.breaking();
                        break;

                    case "change":
                        String act;
                        System.out.println("имя для изменения " + name1);
                        System.out.println(new Registration().checkStatus(name1));
                        if (new Registration().checkStatus(name1)) {
                            act = "in";
                        } else {
                            act = "out";
                        }
                        new Registration().ChangeStatus(sended1.information, act);
                        flag.set("false");
                        break;
                }

                switch (clientMessage2) {
                    case "attack":
                        outMessagePlayer1.writeObject(new Sended("", "huia", "", battle));
                        outMessagePlayer2.writeObject(new Sended("", "huia", "", battle));
                        outMessagePlayer1.flush();
                        outMessagePlayer2.flush();
                        break;

                    case "break":
                        this.Client2.close();
                        this.outMessagePlayer2.close();
                        this.inMessagePlayer2.close();
                        this.Player2.breaking();
                        break;

                    case "change":
                        String act;
                        System.out.println("имя для изменения " + name2);
                        System.out.println(new Registration().checkStatus(name2));
                        if (new Registration().checkStatus(name2)) {
                            act = "in";
                        } else {
                            act = "out";
                        }
                        new Registration().ChangeStatus(sended2.information, act);
                        flag.set("false");
                        break;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

