package com.ludux.twitchgame.Server.Monsters;

import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.Server.Connection.Rebuilder;
import com.ludux.twitchgame.Server.Items.Item;
import com.ludux.twitchgame.Server.Items.Magic.Abilities.BaseAttack;
import com.ludux.twitchgame.Server.Items.Magic.Abilities.ElfUltimate;
import com.ludux.twitchgame.Server.Items.Magic.MagicItem;
import com.ludux.twitchgame.Server.JobWithDATABASE.Search;
import com.ludux.twitchgame.Server.JobWithDATABASE.Someone;


import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;


public class Monster implements Serializable {

    private String name;
    private int HealthMax;
    private int Health;
    private int ManaMax = 100;
    private int Mana = 100;
    private int level;
    private int BaseForceAttack;
    private int BaseForceDefend;
    private int Resistance;
    private int Accuracy;
    private int Agressive = 0;
    private int CriticalChance;
    private int Evasion;
    private ElfUltimate Ultimate;
    public BaseAttack BaseAttack = new BaseAttack();
    private String Error;
    private ArrayList<Item> LiesInTheInventory = new ArrayList<>();
    private ArrayList<MagicItem> UsableItems = new ArrayList<>();


    public ArrayList<MagicItem> getUsableItems() {
        return UsableItems;
    }

    public void setUsableItems(ArrayList<MagicItem> usableItems) {
        UsableItems = usableItems;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public int getHealthMax() {
        return HealthMax;
    }

    public void setHealthMax(int healthMax) {
        HealthMax = healthMax;
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public int getManaMax() {
        return ManaMax;
    }

    public void setManaMax(int manaMax) {
        ManaMax = manaMax;
    }

    public int getMana() {
        return Mana;
    }

    public void setMana(int mana) {
        Mana = mana;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBaseForceAttack() {
        return BaseForceAttack;
    }

    public void setBaseForceAttack(int baseForceAttack) {
        BaseForceAttack = baseForceAttack;
    }

    public int getBaseForceDefend() {
        return BaseForceDefend;
    }

    public void setBaseForceDefend(int baseForceDefend) {
        BaseForceDefend = baseForceDefend;
    }

    public int getResistance() {
        return Resistance;
    }

    public void setResistance(int resistance) {
        Resistance = resistance;
    }

    public int getAccuracy() {
        return Accuracy;
    }

    public void setAccuracy(int accuracy) {
        Accuracy = accuracy;
    }

    public int getAgressive() {
        return Agressive;
    }

    public void setAgressive(int agressive) {
        Agressive = agressive;
    }

    public int getCriticalChance() {
        return CriticalChance;
    }

    public void setCriticalChance(int criticalChance) {
        CriticalChance = criticalChance;
    }

    public int getEvasion() {
        return Evasion;
    }

    public void setEvasion(int evasion) {
        Evasion = evasion;
    }

    public ElfUltimate getUltimate() {
        return Ultimate;
    }

    public void setUltimate(ElfUltimate ultimate) {
        Ultimate = ultimate;
    }

    public ArrayList<Item> getLiesInTheInventory() {
        return LiesInTheInventory;
    }

    public void setLiesInTheInventory(ArrayList<Item> liesInTheInventory) {
        LiesInTheInventory = liesInTheInventory;
    }

    public void TakenDamage(int dmg){
        int damage = dmg;
        if (damage <= this.getHealth() && damage>0) {
            this.setHealth(this.getHealth() - damage);
            System.out.println("ЗДОРОВЬЕ ПЕДИКА " + this.getHealth());
        } else if(damage > this.getHealth() && damage>0){
            this.setHealth(0);
        } else if(damage < 0 && this.getHealth() - damage >= this.getHealthMax()){
            this.setHealth(this.getHealthMax());
        } else if(damage < 0){
            this.setHealth(this.getHealth()-damage);
        }
    }

    public ArrayList<String> Create(String name){
        ArrayList<String> result;
        Someone someone =  Search.search(name);
        result = new Rebuilder().StringToArrayList(new String());
        return result;
    }


    public void ManaCostUsing(int manacost){this.Mana -= manacost;}

    public void regen() {
        if (this.Mana < this.ManaMax ){
            if (this.Mana + Const.ElfManaRegen <= this.ManaMax) {
                this.setMana(this.Mana + Const.ElfManaRegen);
            } else {
                this.setMana(this.ManaMax);
            }
        }
        if (this.Health < this.HealthMax){
            if (this.Health + Const.ElfHealthRegen <= this.HealthMax) {
                this.setHealth(this.Health + Const.ElfHealthRegen);
            } else {
                this.setHealth(this.HealthMax);
            }
        }
    }

    public void addItem(Item item) throws SQLException {
        this.LiesInTheInventory.add(item);
        new Search().addItem(this, this.LiesInTheInventory);
    }
}
