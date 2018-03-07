package com.ludux.twitchgame.notinuse;


import com.ludux.twitchgame.Server.Server;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class ClientHandlerd implements Runnable {

    private Server server;
    private Socket clientSocket = null;
    private OutputSerial outMessage;
    private ObjectInputStream inMessage;
    private String name;
    public boolean inBattle = false;

    public String getName() {return name;}
    public Socket getClientSocket(){return clientSocket;}
    public Server getServer() {return server;}

    @Override
    public void run() {
        System.out.println("Слушаю клиента");
        while(true) {
            try {
                System.out.println("ddd");
                String clientMessage = null;
                HashMap<String, String> F = null;
                try {
                    F = (HashMap<String, String>) inMessage.readObject();
                    System.out.println(F);
                    for (String key : F.keySet()) {
                        clientMessage = key;
                        name = F.get(key);
                    }
                    System.out.println(name);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                switch (clientMessage) {
                    //case "##session##end##":
                        //break;
                    case "WannaBattle":
                        System.out.println("QKRQ");
                        //this.server.AddToQueue(this);
                        outMessage.writeObject("You are in queue");
                        break;

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public ClientHandlerd(Socket socket, Server server){
        try {
            this.server = server;
            this.clientSocket = socket;
            this.outMessage = new OutputSerial(socket.getOutputStream());
            this.inMessage = new ObjectInputStream(socket.getInputStream());
            this.inBattle = false;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
