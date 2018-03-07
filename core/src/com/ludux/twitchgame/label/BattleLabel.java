package com.ludux.twitchgame.label;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.Server.Connection.PreparingBattle;

public class BattleLabel extends Label {

    private static float width = Const.LANDSCAPE_VIEWPORT_WIDTH;
    private static float height = Const.LANDSCAPE_VIEWPORT_HEIGHT;
    private static LabelStyle turnLabelStyle = new LabelStyle(Font.getFont(60,2), Color.YELLOW);
    private static LabelStyle coolDownLabelStyle = new LabelStyle(Font.getFont(30,1), Color.WHITE);
    private static LabelStyle healthStyle = new LabelStyle(Font.getFont(30, 1), Color.WHITE);
    private static LabelStyle manaStyle = new LabelStyle(Font.getFont(20, 0.5f), Color.WHITE);

    public BattleLabel(String string, Label.LabelStyle labelStyle) {
        super(string, labelStyle);
    }

    public static Group getBattleLabels(PreparingBattle battle, String clientName) {
        Group group = new Group();
        String name = battle.isWhoIsYourDaddy() ? battle.getPlayer1().name : battle.getPlayer2().name;
        String health1Text = String.valueOf(battle.First.getHealth()) + "/" + String.valueOf(battle.First.getHealthMax());
        String health2Text = String.valueOf(battle.Second.getHealth()) + "/" + String.valueOf(battle.Second.getHealthMax());
        String mana1Text = String.valueOf(battle.First.getMana()) + "/" + String.valueOf(battle.First.getManaMax());
        String mana2Text = String.valueOf(battle.Second.getMana()) + "/" + String.valueOf(battle.Second.getManaMax());
        Vector2 health1Pos = new Vector2(width/64f + Const.BAR_WIDTH*(2f/5f), height*(11f /12f));
        Vector2 health2Pos = new Vector2(width*(63f/64f) - Const.BAR_WIDTH*(3f/5f),height*(11f /12f));
        Vector2 mana1Pos = new Vector2(width/64f + Const.BAR_WIDTH*(2f/5f) + 40, height*(11f /12f) - 0.35f * Const.BAR_HEIGHT);
        Vector2 mana2Pos = new Vector2(width*(63f/64f) - Const.BAR_WIDTH*(3f/5f) + 40, height*(11f /12f) - 0.35f * Const.BAR_HEIGHT);
        BattleLabel turn = new BattleLabel("Очередь игрока " + name, turnLabelStyle); turn.setPosition(width/3.2f,height/1.3f);
        BattleLabel health1 = new BattleLabel(health1Text, healthStyle);
        BattleLabel health2 = new BattleLabel(health2Text, healthStyle);
        BattleLabel mana1 = new BattleLabel(mana1Text, manaStyle);
        BattleLabel mana2 = new BattleLabel(mana2Text, manaStyle);

        if (battle.checkName(clientName)) {
            health1.setPosition(health1Pos.x, health1Pos.y);
            health2.setPosition(health2Pos.x, health2Pos.y);
            mana1.setPosition(mana1Pos.x, mana1Pos.y);
            mana2.setPosition(mana2Pos.x, mana2Pos.y);
        }
        else {
            health1.setPosition(health2Pos.x, health2Pos.y);
            health2.setPosition(health1Pos.x, health1Pos.y);
            mana1.setPosition(mana2Pos.x, mana2Pos.y);
            mana2.setPosition(mana1Pos.x, mana1Pos.y);
        }
        group.addActor(turn);
        group.addActor(health1);
        group.addActor(health2);
        group.addActor(mana1);
        group.addActor(mana2);
        return group;
    }

    public static BattleLabel getCooldownLabel(String coolDown) {return new BattleLabel(coolDown, coolDownLabelStyle);}
}
