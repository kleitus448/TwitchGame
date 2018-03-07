package com.ludux.twitchgame.registration;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.ludux.twitchgame.*;
import com.ludux.twitchgame.Server.Connection.Client;

import java.util.Random;

public class RegistrationButton extends Button {
    private Label label;

    public RegistrationButton(String name) throws NullPointerException {
        super(Const.rgbutton_default);
        setName(name);
        label = new Label(name, Const.registerStyle);
        add(label);
        addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                getStyle().up = Const.rgbutton_selected;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                getStyle().up = Const.rgbutton_default;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Table table = ((Table) getParent());
                switch (getName()) {
                    case "Регистрация" : {
                        table.clearChildren();
                        table.add(Const.registrationLabel).colspan(2).fill(false).center();
                        table.row();
                        table.add(Const.nameLabel);
                        table.add(addTextField("Name", "Вася")).height(47);
                        table.row();
                        table.add(Const.loginLabel).left();
                        table.add(addTextField("Login", "Вася")).height(47);
                        table.row();
                        table.add(Const.passwordLabel).left();
                        table.add(addTextField("Password", "******")).height(47);
                        ((TextField) table.getCells().peek().getActor()).setPasswordMode(true);
                        ((TextField) table.getCells().peek().getActor()).setPasswordCharacter('*');
                        table.row();
                        table.add(new RegistrationButton("Зарегистрироваться")).colspan(2);
                        table.row().spaceTop(300); table.add();
                        table.add(new RegistrationButton("Назад")).right().size(
                                Const.rgbutton_default.getRegion().getTexture().getWidth()/3f,
                                Const.rgbutton_default.getRegion().getTexture().getHeight()/2f);
                        break;
                    }

                    case "Войти в игру" : {
                        table.clearChildren();
                        table.add(Const.loginLabel).left();
                        table.add(addTextField("Login", "Вася")).height(47);
                        table.row();
                        table.add(Const.passwordLabel).left();
                        table.add(addTextField("Password", "******")).height(47);
                        ((TextField) table.getCells().peek().getActor()).setPasswordMode(true);
                        ((TextField) table.getCells().peek().getActor()).setPasswordCharacter('*');
                        table.row();
                        table.add(new RegistrationButton("Играть")).colspan(2);
                        table.row().spaceTop(300); table.add();
                        table.add(new RegistrationButton("Назад")).right().size(
                                Const.rgbutton_default.getRegion().getTexture().getWidth()/3f,
                                Const.rgbutton_default.getRegion().getTexture().getHeight()/2f);
                        break;
                    }

                    case "Зарегистрироваться": {
                        try {
                            String information = "";
                            String log = "";
                            for (Cell<TextField> cell : table.getCells()) {
                                information += (cell.getActor() instanceof TextField) ? cell.getActor().getText() + " ": "";
                                if (cell.getActor() instanceof TextField && cell.getActor().getName().equals("Login")) log = cell.getActor().getText();
                            }
                            System.out.println(information);
                            Sended sended = new Sended(log,"Registration", information);
                            new Client(sended, "1");
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    case "Назад" : {
                        table.clearChildren();
                        table.add(new RegistrationButton("Регистрация"));
                        table.row();
                        table.add(new RegistrationButton("Войти в игру"));
                        break;
                    }

                    case "Играть": {
                        try {
                            String login = "";
                            for (Cell<TextField> cell : table.getCells()) {
                                login += (cell.getActor() instanceof TextField) ? cell.getActor().getText() + " " : "";
                            }
                            login.trim();
                            Sended sended = new Sended(login.split(" ")[0], "login", login);
                            new Client(sended, "EX"+ new Random().nextInt()).run();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private TextField addTextField(String name, String message) {
        TextField textField = new TextField("", Const.getTextFieldStyle());
        textField.setName(name); textField.setMessageText(message);
        textField.setFocusTraversal(true);
        textField.setAlignment(Align.center);
        return textField;
    }
}
