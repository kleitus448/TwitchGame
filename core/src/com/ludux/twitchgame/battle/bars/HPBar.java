package com.ludux.twitchgame.battle.bars;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.battle.battleactors.ElfActor;

public class HPBar extends Bar {
    public HPBar(ShapeRenderer renderer, ElfActor elf) {
        super(renderer, elf);
        color = Color.RED;
        setSize(Const.BAR_WIDTH * Const.getWidthKRes(), Const.HPBAR_HEIGHT * Const.getHeightKRes());
        rectangle = new Rectangle((elf.getName().equals("elf1") ? Const.LANDSCAPE_VIEWPORT_WIDTH / 64f :
                Const.LANDSCAPE_VIEWPORT_WIDTH * (63f / 64f) - Const.BAR_WIDTH) * Const.getWidthKRes(),
                (Const.LANDSCAPE_VIEWPORT_HEIGHT * (35f / 36f) - Const.HPBAR_HEIGHT) * Const.getHeightKRes(),
                getWidth(), getHeight()
        );

    }

    public void act() {super.act(Const.HEALTH);}
}

