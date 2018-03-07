package com.ludux.twitchgame.notinuse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Tmp {
    public static void main(String[] args) {
        byte k = (byte) 15185465;
        System.out.println(k);
        Thread d = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ss");
            }
        });

        try {
            d.wait();
            d.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        d.notify();
     }
}
