package com.ludux.twitchgame.Server.Items.Magic;

import com.ludux.twitchgame.Server.Items.Item;
import com.ludux.twitchgame.Server.Monsters.Monster;

import java.util.ArrayList;

public abstract class MagicItem extends Item {
    protected String name;
    protected String act;
    protected int MaxCooldown;
    protected int Cooldown;
    protected int val;
    protected int manacost;
    protected boolean enable = false;


    public int getCooldown() {
        return Cooldown;
    }

    public int getVal() {
        return val;
    }

    public int getManacost() {
        return manacost;
    }

    public void setCooldown(int cooldown) {
        Cooldown = cooldown;
    }

    public int getMaxCooldown() {
        return MaxCooldown;
    }

    public void setMaxCooldown(int maxCooldown){MaxCooldown = maxCooldown;}

    public ArrayList<Monster> action(Monster current, Monster attacked){return new ArrayList<Monster>();}

    public String getName() {return name;}
}
