package com.ludux.twitchgame.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ludux.twitchgame.Main;
import com.ludux.twitchgame.battle.battleactors.BattleActor;
import com.ludux.twitchgame.battle.battleactors.ElfActor;
import com.ludux.twitchgame.battle.battlelisteners.InputListeners;

public class ElfGroup extends BattleActor {
    public static final String NAME = "elfGroup";

    public ElfGroup(ShapeRenderer renderer) {
        super(0);
        setName(NAME);
        ElfActor elf1 = new ElfActor("elf1", 0, renderer);
        ElfActor elf2 = new ElfActor("elf2", 1, renderer);
        addActor(elf1); addActor(elf2);
        this.addListener(new InputListeners.ElfListener());
        ((Main) Gdx.app.getApplicationListener()).getCommandListener().setHeroes(elf1, elf2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.drawChildren(batch, parentAlpha);
    }
}
