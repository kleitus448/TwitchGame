package com.ludux.twitchgame.notinuse;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.battle.battleactors.BattleActor;


public class BAHud extends BattleActor {

    private ShapeRenderer renderer = new ShapeRenderer();

    public BAHud() { super(0);
        renderer.setAutoShapeType(true);
    }

    public void dispose() {renderer.dispose();}

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        renderer.begin();
        renderer.set(ShapeRenderer.ShapeType.Filled);
        //Draw Bars
        renderer.setColor(Color.GRAY);
        renderer.rect(Const.LANDSCAPE_VIEWPORT_WIDTH / 64f,
                Const.LANDSCAPE_VIEWPORT_HEIGHT * (35f/36f) - Const.BAR_HEIGHT,
                Const.BAR_WIDTH, Const.BAR_HEIGHT);
        renderer.rect(Const.LANDSCAPE_VIEWPORT_WIDTH - 30,
                Const.LANDSCAPE_VIEWPORT_HEIGHT - Const.BAR_HEIGHT - 30,
                -Const.BAR_WIDTH, Const.BAR_HEIGHT);

        //Draw HPBars
        renderer.setColor(Color.RED);
        renderer.rect(30,
                Const.LANDSCAPE_VIEWPORT_HEIGHT - Const.HPBAR_HEIGHT - 30,
                Const.BAR_WIDTH, Const.HPBAR_HEIGHT);
        renderer.rect(Const.LANDSCAPE_VIEWPORT_WIDTH - 30,
                Const.LANDSCAPE_VIEWPORT_HEIGHT - Const.HPBAR_HEIGHT - 30,
                -Const.BAR_WIDTH, Const.HPBAR_HEIGHT);

        //Draw ManaBars
        renderer.setColor(Const.MANA_COLOR);
        renderer.rect(30,
                Const.LANDSCAPE_VIEWPORT_HEIGHT - Const.BAR_HEIGHT - 30,
                Const.BAR_WIDTH, Const.MANABAR_HEIGHT);
        renderer.rect(Const.LANDSCAPE_VIEWPORT_WIDTH - 30,
                Const.LANDSCAPE_VIEWPORT_HEIGHT - Const.BAR_HEIGHT - 30,
                -Const.BAR_WIDTH, Const.MANABAR_HEIGHT);
        renderer.end();
        batch.begin();
    }
}

