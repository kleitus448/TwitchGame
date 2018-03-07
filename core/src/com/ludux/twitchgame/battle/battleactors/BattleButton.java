package com.ludux.twitchgame.battle.battleactors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.Main;
import com.ludux.twitchgame.Sended;
import com.ludux.twitchgame.Server.Connection.Client;
import com.ludux.twitchgame.Server.Connection.PreparingBattle;
import com.ludux.twitchgame.label.Font;

public class BattleButton extends Button {

    public BattleButton(String name, Client client) {
        super(Const.menuButton_noactive);
        setName(name);
        int width = Const.menuButton_noactive.getRegion().getTexture().getWidth();
        int height = Const.menuButton_noactive.getRegion().getTexture().getHeight();
        setSize(width*1.8f, height*0.9f);
        setPosition(Const.LANDSCAPE_VIEWPORT_WIDTH - getWidth(), 0);
        Label.LabelStyle labelStyle = new Label.LabelStyle(Font.getFont(48, 0.8f), Color.CORAL);
        Label label = new Label(name, labelStyle);
        add(label);
        addListener(new ClickListener() {

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                getStyle().up = Const.menuButton_active;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                getStyle().up = Const.menuButton_noactive;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Main app = ((Main) Gdx.app.getApplicationListener());
                if (button == Input.Buttons.LEFT) {
                    switch (getName()) {
                        case "Закончить ход":
                            PreparingBattle battle = app.getCommandListener().getBattle();
                            client.sendData(new Sended(client.name, Const.IN_BATTLE, Const.NEXT_STEP, battle));
                            break;
                    }
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }


}
