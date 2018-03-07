package com.ludux.twitchgame.battle.bars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.Main;
import com.ludux.twitchgame.Server.Connection.Client;
import com.ludux.twitchgame.Server.Connection.PreparingBattle;
import com.ludux.twitchgame.battle.battleactors.BattleActor;
import com.ludux.twitchgame.battle.battleactors.ElfActor;

public class Bar extends BattleActor {
    protected ShapeRenderer renderer;
    protected Rectangle rectangle;
    protected Color color;
    protected Main app;
    private boolean flag;

    protected Bar(ShapeRenderer renderer, ElfActor elf) {
        super(0);
        this.renderer = renderer;
        app = ((Main) Gdx.app.getApplicationListener());
        flag = elf.getName().equals("elf1");
    }

    public void draw() {
        renderer.begin();
        renderer.setColor(color);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        renderer.end();
    }

    protected void act(String resource) {
        Client client = app.getCommandListener().getClient();
        PreparingBattle battle = app.getCommandListener().getBattle();
        Boolean clientCheck = app.getCommandListener().getBattle().checkName(client.name);
        float value = 0;
        switch (resource) {
            case Const.HEALTH:
                value = (clientCheck == flag ? (float) battle.First.getHealth() / (float) battle.First.getHealthMax() :
                        (float) battle.Second.getHealth() / (float) battle.Second.getHealthMax()) ;
                break;
            case Const.MANA:
                value = (clientCheck == flag ? (float) battle.First.getMana() / (float) battle.First.getManaMax() :
                        (float) battle.Second.getMana() / (float) battle.Second.getManaMax());
                break;
        }
        rectangle.setWidth(getWidth() * value);
    }
}

