package com.ludux.twitchgame.Server.Connection;

import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.OutputSerial;
import com.ludux.twitchgame.Server.Items.Item;
import com.ludux.twitchgame.Server.Items.Kleidung.Clothes;
import com.ludux.twitchgame.Server.Items.Magic.Abilities.BaseAttack;
import com.ludux.twitchgame.Server.Items.Magic.HealingRing;
import com.ludux.twitchgame.Server.Items.Magic.MagicItem;
import com.ludux.twitchgame.Server.JobWithDATABASE.Search;
import com.ludux.twitchgame.Server.JobWithDATABASE.Someone;
import com.ludux.twitchgame.Server.Monsters.Elf;
import com.ludux.twitchgame.Server.Monsters.Monster;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class PreparingBattle implements java.io.Serializable  {
    private boolean WhoIsYourDaddy = Const.P1;
    public boolean BattleInProcess;
    public int number_of_battle;
    public Monster First = null;
    public Monster Second = null;
    private Someone Player1;
    private Someone Player2;
    private Monster winner;
    private ArrayList<OutputSerial> BattleQueueOutputs;
    private String Error;
    private boolean changed = false;
    private boolean end = false;

    public Monster getWinner() {
        return winner;
    }
    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public void changeWhoIsYourDaddy(){
        this.changed = true;
        if (this.WhoIsYourDaddy == Const.P1){
            this.setWhoIsYourDaddy(Const.P2);
        }
        else{
            this.setWhoIsYourDaddy(Const.P1);
        }
    }

    public Someone getPlayer1() {
        return Player1;
    }

    public Someone getPlayer2() {
        return Player2;
    }

    public void setWhoIsYourDaddy(boolean WhoIsYourDaddy) {
        this.WhoIsYourDaddy = WhoIsYourDaddy;
    }

    public boolean checkName(String name) {
        return name.equals(Player1.name);
    }

    public boolean isWhoIsYourDaddy() {
        return this.WhoIsYourDaddy;
    }

    public void setBattleQueueOutputs(ArrayList<OutputSerial> battleQueueOutputs) {
        BattleQueueOutputs = battleQueueOutputs;
    }

    public ArrayList<OutputSerial> getBattleQueueOutputs() {
        return BattleQueueOutputs;
    }

    public PreparingBattle(int number){
        this.number_of_battle = number;
    }

    public synchronized void BattleProcess(ClientHandler Name1, ClientHandler  Name2, Semaphore sem) {
        Player1 = Search.search(Name1.getName());
        Player2 = Search.search(Name2.getName());
        System.out.println("Битва началась");
        switch (Player1.rasa) {
            case "elf":
                First = new Elf(Player1.name + "elf");
        }

        switch (Player2.rasa) {
            case "elf":
                Second = new Elf(Player2.name + "elf");
        }

        sem.release();
        try {
            if (First.getAgressive()< Second.getAgressive()) {
                this.WhoIsYourDaddy = Const.P2;
            } else if (First.getAgressive() == Second.getAgressive()) {
                Random rnd = new Random();
                if (rnd.nextBoolean()) {
                    this.WhoIsYourDaddy = Const.P2;
                    System.out.println("Поменял");
                }
            }
        }
        catch (NullPointerException e ){
            e.printStackTrace();
        }
        this.BattleInProcess = true;

        First = this.ItemCorrection(First);
        Second = this.ItemCorrection(Second);

        System.out.println("Мана1 = " + First.getMana());
        System.out.println("МАНА2 = " + Second.getMana());


        System.out.println("в инвентаре у первого" + First.getLiesInTheInventory());
        for (Item item: First.getLiesInTheInventory()){
            System.out.println(item.getLevel());
        }
        System.out.println("в инвентаре у второго" + Second.getLiesInTheInventory());
    }

    public void logic(String item) {
        System.out.println("in");
        Monster current = isWhoIsYourDaddy() ? First : Second;
        Monster attacked = isWhoIsYourDaddy() ? Second : First;
        current.setError(null);
        attacked.setError(null);
        ArrayList<Monster> after = new ArrayList<>();
        switch (item) {
            case Const.ATTACK:
            case Const.HURT_BASE:
                after = current.BaseAttack.action(current, attacked);
                current = after.get(0);
                attacked = after.get(1);
                break;

            case Const.HEAL_RING:
                System.out.println(current.getLiesInTheInventory());
                for (Item item1 : current.getLiesInTheInventory()) {
                    if (item1 instanceof HealingRing) {
                        after = ((HealingRing) item1).action(current, attacked);
                        current = after.get(0);
                    }
                }
                break;

            case Const.FULLHP: //ФУЛЛХП СТРИЛИЦ ПРОСТО ПЕРЕДАВАЙ ЭТО для того чтобы БЛЯТЬ РАБОТАТЬ НАХУЙ НАД ПРОЕКТОМ!!!
                attacked.setHealth(0);
                this.end = true;
                break;

            case Const.HURT_ULTIMATE:
                after = current.getUltimate().action(current, attacked);
                current = after.get(0);
                attacked = after.get(1);
                break;

            case Const.NEXT_STEP:
                System.out.println("In next Step");
                System.out.println(isWhoIsYourDaddy());
                current.regen();
                current.setLiesInTheInventory(nextCooldown(current.getLiesInTheInventory()));
                //attacked.setLiesInTheInventory(nextCooldown(attacked.getLiesInTheInventory()));
                current.BaseAttack = nextCooldownAA(current.BaseAttack);
                //attacked.BaseAttack = nextCooldownAA(attacked.BaseAttack);
                changeWhoIsYourDaddy();
                System.out.println(isWhoIsYourDaddy());
                break;
        }
        if (!changed){
            if(isWhoIsYourDaddy()){
                First = current;
                Second = attacked;
            } else {
                Second = current;
                First = attacked;
            }
            System.out.println("ощибка на фирст "+First.getError());
            System.out.println("ощибка на секонд " + Second.getError());
        }else{
            changed = false;
        }

    }

    private BaseAttack nextCooldownAA(BaseAttack in){
        in.setCooldown(0);
        return  in;
    }

    private ArrayList<Item> nextCooldown(ArrayList<Item> in){
        for (Item predmet: in ) {
            System.out.println("in next cooldown");
            System.out.println(predmet);
            System.out.println(predmet.hasCooldown);

            try {
                System.out.println(((MagicItem) predmet).getCooldown());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (predmet.hasCooldown && ((MagicItem)predmet).getCooldown() != 0){
                ((MagicItem)predmet).setCooldown(((MagicItem)predmet).getCooldown()-1);
                System.out.println(((MagicItem) predmet).getCooldown());
            }
        }
        return in;
    }


    public Monster ItemCorrection(Monster monster){
        for (Item item : monster.getLiesInTheInventory()) {
            if(item instanceof Clothes){
                monster = ((Clothes) item).action(monster);
            }
        }
        return monster;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public boolean getEnd() {
        return this.end;
    }
}
            
