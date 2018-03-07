package com.ludux.twitchgame.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ludux.twitchgame.*;
import com.ludux.twitchgame.Server.Connection.Client;
import com.ludux.twitchgame.label.Font;

import java.text.ParseException;
import java.util.Random;

public class MenuButton extends Button {
    private Animation<TextureRegion> animation;
    private float time;
    private String user;

    public String getUser() {
        return user;
    }

    public MenuButton(String name, String user) {
        super(Const.menuButton_noactive);
        this.user = user; setName(name);

        //Создание надписи на кнопке
        Label.LabelStyle labelStyle = new Label.LabelStyle(Font.getFont(48, 0.8f), Color.CORAL);
        Label label = new Label(name, labelStyle); add(label);

        addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                getStyle().up = Const.menuButton_active;
                switch (getName()) {
                    case "Игра по сети": animation = Const.play_menu; break;
                    case "Профиль": animation = Const.profile_menu; break;
                    case "Магазин": animation = Const.shop_menu; break;
                    case "Инвентарь": animation = Const.inventory_menu; break;
                    case "Настройки": animation = Const.settings_menu; break;
                    case "Выход": animation = Const.exit_menu; break;
                }
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                getStyle().up = Const.menuButton_noactive;
                animation = null;
                time = 0;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Main app = ((Main)Gdx.app.getApplicationListener());
                switch (getName()) {
                    case "Игра по сети":
                        try {
                            new Thread(new Client(new Sended(user,"WantToFight",user), "EX" + new Random().nextInt())).start();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "Магазин":
                        //app.changeScreen(Const.SHOP);
                        break;


                    case "Назад" :
                        app.getCommandListener().setCommand(Const.CHANGE_SCREEN, Const.TO_MENU_SCREEN);
                        break;


                    case "Выход":
                        Gdx.app.exit();
                        break;

                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        time += Gdx.graphics.getDeltaTime();
        if (animation != null) {
            TextureRegion texture = animation.getKeyFrame(time);
            batch.draw(texture, 1340, 0, 550, 500);
        }
    }
}
