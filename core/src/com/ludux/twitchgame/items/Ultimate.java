package com.ludux.twitchgame.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.GifDecoder;
import com.ludux.twitchgame.battle.battleactors.Arrow;
import com.ludux.twitchgame.battle.battleactors.ElfActor;

public class Ultimate extends Item {

    public Ultimate() {
        super();
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP,
                Gdx.files.internal("core/assets/Sprites/inventory/items/fire1.gif").read()
        );
        setName(Const.ULTIMATE);
    }

    @Override
    public boolean activate(ElfActor actor) {
        if (super.activate(actor)) {
            actor.getParent().addActor(new Arrow(actor, Arrow.FIRE_STYLE));
            actor.getOtherElf().setCurrentAnimation(actor.getAnimation("attack"));
        }
        return true;
    }
}
