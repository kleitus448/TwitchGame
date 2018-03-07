package com.ludux.twitchgame.battle.battleactors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.ludux.twitchgame.Const;
import java.util.Random;

public class Cloud extends BattleActor {

    private static Array<Cloud> cloudArray = new Array<Cloud>();
    private Texture cloudTexture = new Texture(
            Gdx.files.internal("core/assets/Sprites/fight_arena/objects/cloud.png")
    );

    private Cloud() {
        super(0);
        speed = GenerateSpeed();
        sprite = new Sprite(cloudTexture);
        float random = new Random().nextFloat();
        setX(Const.LANDSCAPE_VIEWPORT_WIDTH / 2f);
        setY(new Random().nextInt(
                Math.round(Const.LANDSCAPE_VIEWPORT_HEIGHT / 2.7f)) +
                Math.round(Const.LANDSCAPE_VIEWPORT_HEIGHT / 1.96f)
        );
        setSize(sprite.getWidth() * (random > 0.5f ? random : random + 0.5f) / 2,
                sprite.getHeight() * (random > 0.5f ? random : random + 0.5f) / 2);
    }

    private float GenerateSpeed() {
        Boolean randomBoolean = new Random().nextBoolean();
        return ((new Random().nextInt(5) + 5) *
                (new Random().nextFloat()) + 0.1f) *
                (randomBoolean ? -1 : 1);
    }

    static Array<Sprite> getArrayClouds() {
        Array<Sprite> spriteArray = new Array<Sprite>();
        for (int i = 0; i < Const.QUANTITY_CLOUDS; i++) {
            Cloud cloud = new Cloud();
            cloud.getSprite().setBounds(cloud.getX(), cloud.getY(),
                    cloud.getWidth(), cloud.getHeight());
            cloudArray.add(cloud);
            spriteArray.add(cloud.getSprite());
        }
        return spriteArray;
    }

    private void moving() {
        float x = getX() + time * speed;
        if (x > Const.LANDSCAPE_VIEWPORT_WIDTH && speed > 0) {
            time = 0;
            setX(-getWidth());
        } else if (x < -getWidth() && speed < 0) {
            time = 0;
            setX(Const.LANDSCAPE_VIEWPORT_WIDTH + getWidth());
        } else time += 1;
        sprite.setX(x);
    }

    static void act() {for (Cloud cloud : cloudArray) cloud.moving();}
}


