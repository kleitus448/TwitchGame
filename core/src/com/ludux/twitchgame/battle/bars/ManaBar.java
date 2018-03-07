package com.ludux.twitchgame.battle.bars;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.battle.battleactors.ElfActor;

public class ManaBar extends Bar {
    public ManaBar(ShapeRenderer renderer, ElfActor elf) {
        super(renderer, elf);
        color = Const.MANA_COLOR;
        setSize(Const.BAR_WIDTH * Const.getWidthKRes(),Const.MANABAR_HEIGHT * Const.getHeightKRes());
        rectangle = new Rectangle((elf.getName().equals("elf1") ? Const.LANDSCAPE_VIEWPORT_WIDTH / 64f :
                Const.LANDSCAPE_VIEWPORT_WIDTH * (63f / 64f) - Const.BAR_WIDTH) * Const.getWidthKRes(),
                (Const.LANDSCAPE_VIEWPORT_HEIGHT * (35f / 36f) - Const.BAR_HEIGHT) * Const.getHeightKRes(),
                getWidth(), getHeight()
        );
    }

    public void act() {super.act(Const.MANA);}

        /*if (width <= 0.1f) {
            ((Main) Gdx.app.getApplicationListener()).getStage().addActor(Message.NO_MANA());
            return false;
        }
        else {
            rectangle.setWidth(rectangle.getWidth() - Const.BAR_WIDTH * procent * k);
            return true;
        }*/
}
