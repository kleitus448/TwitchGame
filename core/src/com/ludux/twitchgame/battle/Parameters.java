package com.ludux.twitchgame.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.ludux.twitchgame.battle.battleactors.BattleActor;

public class Parameters extends BattleActor {
    private BitmapFont font = new BitmapFont();
    private boolean flagFPS = false;

    public Parameters () {
        super(0);
        font.getData().setScale(5);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.setTouchable(Touchable.enabled);
        this.addListener(new InputListener(){
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                System.out.println("ImHere");
                switch (keycode) {
                    case Input.Keys.F3: flagFPS = !flagFPS; break;
                }
                return super.keyUp(event, keycode);
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        if (flagFPS) font.draw(batch, String.valueOf(Gdx.graphics.getFramesPerSecond()),100,100);
    }
}

