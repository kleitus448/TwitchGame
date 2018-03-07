package com.ludux.twitchgame;

import com.ludux.twitchgame.Server.Connection.PreparingBattle;

public class Sended implements java.io.Serializable {
    public String sender;
    public String action;
    public String information;
    public PreparingBattle battle;

    public Sended(String sender, String action, String information) {
        this.action = action;
        this.sender = sender;
        this.information = information;
    }

    public Sended(String sender, String action, String information, PreparingBattle battle) {
        this.action = action;
        this.sender = sender;
        this.information = information;
        this.battle = battle;
    }
}

