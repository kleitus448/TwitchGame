package com.ludux.twitchgame.battle.battleactors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.GifDecoder;

public class Title extends BattleActor {
    private Animation<TextureAtlas.AtlasRegion> currentAnimation;
    private Animation<TextureRegion> opening_win = GifDecoder.loadGIFAnimation(Animation.PlayMode.NORMAL,
            Gdx.files.internal("core/assets/Titles/opening_win.gif").read());
    private Animation<TextureRegion> opening_lose = GifDecoder.loadGIFAnimation(Animation.PlayMode.NORMAL,
            Gdx.files.internal("core/assets/Titles/opening_lose.gif").read());

    public Title(String name) {
        super(0); setName(name);
        /*sprite.setPosition(Const.LANDSCAPE_VIEWPORT_WIDTH/3.2f,
                Const.LANDSCAPE_VIEWPORT_HEIGHT/2.6f);*/
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        boolean status = getName().equals(Const.WIN);
        if (status) sprite.set(new Sprite(opening_win.getKeyFrame(time)));
        else sprite.set(new Sprite(opening_lose.getKeyFrame(time)));
        time += delta;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(sprite.getTexture(), Const.LANDSCAPE_VIEWPORT_WIDTH/3.2f,
                Const.LANDSCAPE_VIEWPORT_HEIGHT/2.6f);
    }
}
