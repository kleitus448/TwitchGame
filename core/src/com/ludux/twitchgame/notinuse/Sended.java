package com.ludux.twitchgame.notinuse;

public class Sended extends Object {
    private String sender;
    private String action;
    private String information;

    public String getAction() {
        return action;
    }

    public String getInformation() {
        return information;
    }

    public String getSender() {
        return sender;
    }

    public Sended (String sender, String action, String information){
        this.sender = sender;
        this.action = action;
        this.information = information;
    }
}
