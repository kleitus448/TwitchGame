package com.ludux.twitchgame.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.Main;
import com.ludux.twitchgame.Sended;
import com.ludux.twitchgame.Server.Connection.Client;
import com.ludux.twitchgame.Server.Connection.PreparingBattle;
import com.ludux.twitchgame.battle.BattleScreen;
import com.ludux.twitchgame.battle.battleactors.Arrow;
import com.ludux.twitchgame.battle.battleactors.ElfActor;
import com.ludux.twitchgame.battle.battleactors.Title;
import com.ludux.twitchgame.menu.MenuScreen;

public class CommandListener {
    private PreparingBattle battle;
    private Client client;
    private ElfActor elf1;
    private ElfActor elf2;
    private String type;
    private String command;
    private Main app;

    public CommandListener(Client client) {
        app = ((Main) Gdx.app.getApplicationListener());
        this.client = client;}

    public void setClient(Client client) {
        if (this.client!=null && !(this.client.getConnection().isClosed())) {
            this.client.sendData(new Sended(this.client.name,"break",""));
            this.client.Close();
        }
        this.client = client;
    }
    public Client getClient() {return client;}

    public PreparingBattle getBattle() {return battle;}
    public void setBattle(PreparingBattle battle) {this.battle = battle;}
    public void setCommand(String type, String command) {this.type = type; this.command = command;}
    public void setHeroes(ElfActor elf1, ElfActor elf2) {this.elf1 = elf1; this.elf2 = elf2;}

    private boolean isChanged() {return !(command.isEmpty()) && !(type.isEmpty());}
    public boolean isClientCheck() {return battle.isWhoIsYourDaddy() == battle.checkName(client.name);}

    public void check() {
        if (isChanged()) {
            switch (type) {
                case Const.CHANGE_SCREEN:
                    app.getStage().clear();
                    switch (command) {
                        case Const.TO_MENU_SCREEN: app.setScreen(new MenuScreen(client)); break;
                        case Const.TO_BATTLE_SCREEN: app.setScreen(new BattleScreen(battle, client)); break;
                    }
                    break;

                case Const.IN_BATTLE:
                    switch (command) {
                        case Const.HEAL_RING:
                            if (isClientCheck()) elf1.setCurrentAnimation(elf1.getAnimation(Const.HEALTH));
                            else elf2.setCurrentAnimation(elf2.getAnimation(Const.HEALTH));
                            break;
                            /*int i = app.getStage().getActors().indexOf()
                            app.getStage().getActors().get(*/

                        case Const.ATTACK:
                            System.out.println(battle.First.getError() + " First");
                            System.out.println(battle.Second.getError() + " Second");
                            if (battle.First.getError() == null && battle.Second.getError() == null) {
                                if (isClientCheck()) {
                                    elf1.setCurrentAnimation(elf1.getAnimation(Const.ATTACK));
                                    elf1.getParent().addActor(new Arrow(elf2, Arrow.USUAL_STYLE));
                                } else {
                                    elf2.setCurrentAnimation(elf2.getAnimation(Const.ATTACK));
                                    elf2.getParent().addActor(new Arrow(elf1, Arrow.USUAL_STYLE));
                                }
                            }
                            break;

                        case Const.HURT_BASE : case Const.HURT_ULTIMATE :
                            if (elf1.getParent().getChildren().peek().getName().equals("arrow"))
                                elf1.getParent().getChildren().pop();
                            if (isClientCheck()) elf2.setCurrentAnimation(elf2.getAnimation(Const.HURT));
                            else elf1.setCurrentAnimation(elf1.getAnimation(Const.HURT));
                            Sound sound = Gdx.audio.newSound(Gdx.files.internal("core/assets/Sound/Крик.mp3"));
                            sound.play(1.0f);
                            break;


                        case Const.NEXT_STEP:
                            System.out.println("changeTouch");
                            System.out.println(battle.isWhoIsYourDaddy());
                            changeTouch();
                            break;
                    }
                    break;

            }
            type = ""; command = "";
        }
    }

    public void changeTouch () {
        Main app = ((Main) Gdx.app.getApplicationListener());
        if (isClientCheck()) app.getStage().getRoot().setTouchable(Touchable.enabled);
        else app.getStage().getRoot().setTouchable(Touchable.disabled);

    }
}
