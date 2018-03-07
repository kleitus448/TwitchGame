package com.ludux.twitchgame.battle.bars;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.battle.battleactors.ElfActor;

public class LostBar extends Bar {
    public LostBar(ShapeRenderer renderer, ElfActor elf) {
        super(renderer, elf);
        this.renderer = renderer; color = Color.GRAY;
        rectangle = new Rectangle(
                (elf.getName().equals("elf1") ? Const.LANDSCAPE_VIEWPORT_WIDTH / 64f :
                Const.LANDSCAPE_VIEWPORT_WIDTH * (63f / 64f) - Const.BAR_WIDTH) * Const.getWidthKRes(),
                (Const.LANDSCAPE_VIEWPORT_HEIGHT * (35f / 36f) - Const.BAR_HEIGHT) * Const.getHeightKRes(),
                Const.BAR_WIDTH * Const.getWidthKRes(),
                Const.BAR_HEIGHT * Const.getHeightKRes()
        );
    }
}
