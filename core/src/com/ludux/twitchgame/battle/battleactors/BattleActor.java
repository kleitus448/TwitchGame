package com.ludux.twitchgame.battle.battleactors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

public class BattleActor extends Group {
    protected float speed = 0;
    protected float time;
    protected Sprite sprite = new Sprite();

    public Sprite getSprite() {
        return sprite;
    }

    public BattleActor(float time){
        this.time = time; //debug();
    }
}


