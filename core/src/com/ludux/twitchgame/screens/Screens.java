package com.ludux.twitchgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.ludux.twitchgame.Const;

public class Screens {
    public static class Blackscreen extends Actor {
        private float time;
        private ShapeRenderer renderer;

        public Blackscreen(ShapeRenderer renderer){
            this.renderer = renderer;
            setColor(Color.BLACK);
            setSize(Const.LANDSCAPE_VIEWPORT_WIDTH, Const.LANDSCAPE_VIEWPORT_HEIGHT);
            addAction(Actions.fadeOut(2f));
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            batch.end();
            setColor(getColor().r, getColor().g, getColor().b, getColor().a*parentAlpha);
            Gdx.gl.glEnable(GL30.GL_BLEND);
            Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
            renderer.begin(ShapeRenderer.ShapeType.Filled);
            renderer.setColor(getColor());
            renderer.rect(getX(), getY(), getWidth(), getHeight());
            renderer.end();
            Gdx.gl.glDisable(GL30.GL_BLEND);
            batch.begin();
            if (getColor().a == 0) remove();
        };
    };

    public static class CooldownScreen {
        private ShapeRenderer renderer = new ShapeRenderer();

        public CooldownScreen(ShapeRenderer renderer) {
            this.renderer = renderer;
            renderer.setColor(Color.BLUE);
        }

        public void draw(ShapeRenderer renderer, float parentAlpha) {

        }
    }
}
