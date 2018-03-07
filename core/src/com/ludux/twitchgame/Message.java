package com.ludux.twitchgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.Main;
import com.ludux.twitchgame.label.Font;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;


public class Message extends Dialog {

    public static Message WRONG_LOGIN() {return new Message("Неправильный логин/пароль");}
    public static Message WIN() {return new Message("Вы выиграли!");}
    public static Message LOSE() {return new Message("Вы проиграли!");}
    public static Message NO_MANA() {return new Message("Не хватает маны");}

    public Message(String string) {
        super("Сообщение", new WindowStyle(
                Const.messageStyle.font, Const.messageStyle.fontColor, Const.message_bg));
        setName("message");
        padTop(47);
        getStyle().stageBackground = Const.wrong_bg;
        TextButton textButton = new TextButton("ОК", Const.messageStyle);
        textButton.getLabel().setAlignment(Align.top);
        getButtonTable().defaults().maxSize(Const.messagebutton_bg.getRegion().getTexture().getWidth() / 4f,
                Const.messagebutton_bg.getRegion().getTexture().getHeight() / 1.7f).padBottom(20);
        button(textButton);
        setWidth(Const.message_bg.getRegion().getTexture().getWidth() / 1.3f);
        setHeight(Const.message_bg.getRegion().getTexture().getHeight() / 1.3f);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2f, Gdx.graphics.getHeight() / 3f);
        addAction(sequence(Actions.alpha(0), Actions.fadeIn(0.4f, Interpolation.fade)));
        text(new Label(string,
                new Label.LabelStyle(Const.messageStyle.font, Const.messageStyle.fontColor)));
        getContentTable().center();
        getTitleTable().padLeft(getWidth() / 3f);
    }
}