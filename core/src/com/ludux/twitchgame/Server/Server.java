package com.ludux.twitchgame.Server;

import com.ludux.twitchgame.OutputSerial;
import com.ludux.twitchgame.Sended;
import com.ludux.twitchgame.Server.Connection.ClientHandler;
import com.ludux.twitchgame.Server.Connection.PreparingBattle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Server implements Runnable  {
    static private ServerSocket server;
    static private final Semaphore sem = new Semaphore(1);
    static public Socket connection;
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ArrayList<ClientHandler> BattleQueue = new ArrayList<>();
    private ArrayList<Integer> NumbersOfBattle = new ArrayList<>();
    private   ThreadLocal<Boolean> flag = new ThreadLocal<>();

    public static ArrayList<OutputSerial> getBattleQueueOutputs() {
        return BattleQueueOutputs;
    }
    public static ArrayList<ArrayList<OutputSerial>> BattleQueueOutputsArr = new ArrayList<>();
    private static ArrayList<OutputSerial> BattleQueueOutputs = new ArrayList<>();
    public ArrayList<Integer> getNumbersOfBattle() {
        return NumbersOfBattle;
    }
    @Override
    public void run() {
        try {
            main();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void main() throws IOException {
        try {

            server = new ServerSocket(8000,1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            connection = server.accept();
            System.out.println("NEW CONNECTION " + connection.toString());
            ClientHandler client = new ClientHandler(connection,this);
            if (!clients.contains(client)) {
                clients.add(client);
                new Thread(client).start();
            }
        }
    }

    public void  AddToQueue (ClientHandler current, OutputSerial output) throws Exception {
        flag.set(false);
        System.out.println(output);
        System.out.println("сервак добавил в очередь");
        this.BattleQueue.add(current);
        this.BattleQueueOutputs.add(output);
        System.out.println(this.BattleQueueOutputs);
        if (this.BattleQueue.size() == 2) {
            this.sem.acquire();
            ClientHandler first = this.BattleQueue.get(0);
            ClientHandler second = this.BattleQueue.get(1);
            Random rnd = new Random();
            int randomNumber;
            do{
                randomNumber = rnd.nextInt();
                if (!this.NumbersOfBattle.contains(randomNumber)){
                    flag.set(true);
                    System.out.println(randomNumber);
                    this.NumbersOfBattle.add(randomNumber);
                }
            }while (!flag.get());

            PreparingBattle battle = new PreparingBattle(randomNumber);
            battle.setBattleQueueOutputs((ArrayList<OutputSerial>) getBattleQueueOutputs().clone());
            battle.BattleProcess(first, second, this.sem);

            //System.out.println("Потоки вывода: \n " + battle.getBattleQueueOutputs() + "\n --------- \n");
            ArrayList<OutputSerial> tmpOutAr = new ArrayList<>(getBattleQueueOutputs().size());
            for(OutputSerial item: getBattleQueueOutputs()){
                tmpOutAr.add(item.CloneThis());
            }
            BattleQueueOutputsArr.add(tmpOutAr);
            System.out.println(BattleQueueOutputsArr);

            for (OutputSerial out : BattleQueueOutputs) {
                out.writeObject(new Sended("","You are in battle","", battle));
                out.flush();
            }

            this.BattleQueue.clear();
            this.BattleQueueOutputs.clear();
        }
    }

    public static void main(String[] args) throws ParseException {
        new Thread(new Server()).start();
    }

    public void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }
}