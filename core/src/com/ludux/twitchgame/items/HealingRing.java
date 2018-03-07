package com.ludux.twitchgame.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.GifDecoder;
import com.ludux.twitchgame.battle.battleactors.ElfActor;

public class HealingRing extends Item {

    public HealingRing() {
        super();
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP,
                Gdx.files.internal("core/assets/Sprites/inventory/items/ring.gif").read()
        );
        setName(Const.HEAL_RING);
    }
}
