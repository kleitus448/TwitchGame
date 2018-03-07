package com.ludux.twitchgame.Server.Connection;

import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.OutputSerial;
import com.ludux.twitchgame.Sended;
import com.ludux.twitchgame.Server.JobWithDATABASE.Registration;
import com.ludux.twitchgame.Server.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    private Server server;
    private Socket clientSocket = null;
    private OutputSerial outMessage;
    private ObjectInputStream inMessage;
    public ObjectInputStream getInMessage() {
        return inMessage;
    }
    public OutputSerial getOutMessage() {
        return outMessage;
    }
    private String name;
    private ThreadLocal<Integer> indexOfBattle = new ThreadLocal<>();

    public ThreadLocal<String> flag = new ThreadLocal<>();
    public ArrayList<BattleClient> users = new ArrayList<>();

    public String getName() {return name;}
    public Socket getClientSocket(){return clientSocket;}
    public Server getServer() {return server;}

    @Override
    public void run() {
        flag.set("true");
            try {
                while(flag.get() == "true") {

                    Sended sended = (Sended) inMessage.readObject();
                    System.out.println(this.inMessage);
                    String clientMessage = sended.action;
                    String clientInformation = sended.information;
                    System.out.println(clientMessage);
                    name = sended.sender;
                    PreparingBattle battle = sended.battle;
                    //System.out.println("Получил сообщение " + clientMessage);
                    switch (clientMessage) {
                        case "Registration":
                            String[] Reg = sended.information.split(" ");
                            String username = Reg[1];
                            String newname = Reg[0];
                            String pass = Reg[2];
                            System.out.println((username + " " + newname + " " + pass));
                            if (!(new Registration()).Check(username)) {
                                (new Registration()).add(username, newname, "elf", pass);
                            }
                            breaking();
                            break;

                        case "login":
                            String[] login = sended.information.split(" ");
                            String LoginUserame = login[0];
                            String LoginPassword = login[1];
                            if (new Registration().CheckLogin(LoginUserame, LoginPassword) && (new Registration().checkStatus(LoginUserame))){
                                new Registration().ChangeStatus(LoginUserame,"in");
                                outMessage.writeObject(new Sended("","true",""));
                                outMessage.flush();
                            } else {
                                outMessage.writeObject(new Sended("","false",""));
                                outMessage.flush();
                                this.breaking();
                            }

                            break;

                        case "change":
                            String act;
                            System.out.println("имя для изменения " + name);
                            System.out.println(new Registration().checkStatus(name));
                            if (new Registration().checkStatus(name)) {
                                act = "in";
                            } else {
                                act = "out";
                            }

                            new Registration().ChangeStatus(sended.information, act);
                            flag.set("false");
                            break;

                        case "WantToFight":
                            System.out.println("Получил WANT TO BATTLE");
                            this.server.AddToQueue(this, outMessage);
                            System.out.println("добавил в очередь");
                            break;

                        case Const.IN_BATTLE:
                            battle.logic(clientInformation);
                            if (battle.First.getHealth()==0 || battle.Second.getHealth()==0){
                                battle.setEnd(true);
                            }
                            System.out.println(battle.First.getError() + " CHF");
                            System.out.println(battle.Second.getError() + " CHS");
                            indexOfBattle.set(this.server.getNumbersOfBattle().indexOf(battle.number_of_battle));
                            System.out.println(battle.number_of_battle);
                            System.out.println(indexOfBattle.get());
                            System.out.println(this.server.BattleQueueOutputsArr.get(indexOfBattle.get()));
                            this.server.BattleQueueOutputsArr.get(indexOfBattle.get()).get(0).writeObject(new Sended("", clientMessage, clientInformation, battle));
                            this.server.BattleQueueOutputsArr.get(indexOfBattle.get()).get(1).writeObject(new Sended("", clientMessage, clientInformation, battle));
                            this.server.BattleQueueOutputsArr.get(indexOfBattle.get()).get(0).flush();
                            this.server.BattleQueueOutputsArr.get(indexOfBattle.get()).get(1).flush();
                            break;

                        case "break":
                            System.out.println("EBATNYA");
                            outMessage.writeObject(new Sended("","break",""));
                            outMessage.flush();
                            this.breaking();
                            break;
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }

    }


    public void breaking() throws IOException {
        System.out.println("ЗАКРЫЛ СОЕДИНЕНИЕ");
        this.clientSocket.close();
        this.outMessage.close();
        this.inMessage.close();
        this.flag.set("false");
    }

    public ClientHandler(Socket socket, Server server){
        try {
            this.users.add(new BattleClient(socket.getPort()));
            flag.set("false");
            this.server = server;
            this.clientSocket = socket;
            this.outMessage = new OutputSerial(socket.getOutputStream());
            this.inMessage = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}




