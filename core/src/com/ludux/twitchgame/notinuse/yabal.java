package com.ludux.twitchgame.notinuse;

public class yabal {
    public static void main(String[] args) {
        try {
            Class c = Class.forName("com.ludux.twitchgame.Server.Items.Magic.HealingRing");
            Object obj = new Object();
            Object obj1 = c.cast(obj);
            System.out.println(obj1);
            System.out.println(c);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
