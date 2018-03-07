package com.ludux.twitchgame.Server.Items;

import java.io.Serializable;

public abstract class Item implements Serializable {
    public boolean hasCooldown = false;
    private int level = 1;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
