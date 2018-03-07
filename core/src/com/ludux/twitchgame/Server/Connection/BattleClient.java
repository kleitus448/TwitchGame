package com.ludux.twitchgame.Server.Connection;

public class BattleClient {
    private Integer  name;
    private boolean connectedWithOpponent;
    public PreparingBattle battle;

    public boolean isConnectedWithOpponent() {
        return connectedWithOpponent;
    }

    public void setConnectedWithOpponent(boolean connectedWithOpponent) {
        this.connectedWithOpponent = connectedWithOpponent;
    }

    public BattleClient(Integer name){
        this.name = name;
        this.connectedWithOpponent = false;
    }
}
