package com.ludux.twitchgame.Server.JobWithDATABASE;

import com.ludux.twitchgame.Server.Items.Item;

import java.io.Serializable;
import java.util.ArrayList;

public class Someone implements Serializable {
    public String name;
    public String rasa;
    public int level;
    public ArrayList<String> Heroes;
    public ArrayList<Item> Inventory;

}
