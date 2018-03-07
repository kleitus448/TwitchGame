//package com.ludux.twitchgame.Server;
//
//import java.io.*;
//import java.net.Socket;
//import java.util.HashMap;
//
//
//public class BattleClientHandler implements Runnable {
//
//    private Server server;
//    private Socket clientSocket = null;
//    private OutputSerial outMessage;
//    private ObjectInputStream inMessage;
//    private String name;
//    private Fight fight;
//    public boolean inBattle = false;
//
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public void run() {
//        System.out.println("Слушаю клиента");
//        while(true) {
//            try {
//                String clientMessage = null;
//                HashMap<String, String> F = null;
//                try {
//                    F = (HashMap<String, String>) inMessage.readObject();
//                    System.out.println(F);
//                    for (String key : F.keySet()) {
//                        clientMessage = key;
//                        name = F.get(key);
//                    }
//                    System.out.println(name);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//                switch (clientMessage) {
//
//                    case "Attack":
//                        if(this.inBattle==true){
//                            if (this.name == "First")
//                                new Battle().logic(this.fight.getPlayer1(), this.fight.getPlayer2(),"Attack");
//                            else
//                                new Battle().logic(this.fight.getPlayer2(), this.fight.getPlayer1(),"Attack");
//
//                        }
//                        break;
//
//                    case "Bye":{
//                        break;
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
//
//    public BattleClientHandler(Socket socket, Server server, Fight fight, String name){
//        try {
//            this.name = name;
//            this.server = server;
//            this.clientSocket = socket;
//            this.outMessage = new OutputSerial(socket.getOutputStream());
//            this.inMessage = new ObjectInputStream(socket.getInputStream());
//            this.inBattle = true;
//            this.fight = fight;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//}