package com.ludux.twitchgame.battle.battleactors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class BALocation extends BattleActor {
    private Texture background = new Texture("core/assets/Sprites/fight_arena/grounds/background.png");
    private Texture middleground = new Texture("core/assets/Sprites/fight_arena/grounds/mountains.png");
    private Array<Sprite> spriteArray = new Array<>();


    public BALocation() {
        super(0);
        setName("BALocation");
        spriteArray.add(new Sprite(background));
        spriteArray.addAll(Cloud.getArrayClouds());
        spriteArray.add(new Sprite(middleground));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (Sprite sprite : spriteArray) {sprite.draw(batch);}
    }

    @Override
    public void act(float delta) {Cloud.act();}
}
