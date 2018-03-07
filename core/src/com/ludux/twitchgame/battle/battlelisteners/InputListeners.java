package com.ludux.twitchgame.battle.battlelisteners;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.scenes.scene2d.*;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.Cursors;
import com.ludux.twitchgame.Main;
import com.ludux.twitchgame.Server.Connection.Client;
import com.ludux.twitchgame.Sended;
import com.ludux.twitchgame.Server.Connection.PreparingBattle;
import com.ludux.twitchgame.battle.ElfGroup;
import com.ludux.twitchgame.battle.battleactors.ElfActor;
import com.ludux.twitchgame.battle.battleactors.Title;
import com.ludux.twitchgame.items.Item;

public class InputListeners {
    public static class ElfListener extends InputListener {
        private Main app;

        public ElfListener() {
            app = ((Main) Gdx.app.getApplicationListener());
            System.out.println("ElfListener");
        }

        @Override
        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            if (event.getTarget().getName().equals("elf2"))
                Gdx.graphics.setCursor(Cursors.getCursor(Const.ATTACK));
            super.enter(event, x, y, pointer, fromActor);
        }

        @Override
        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            super.exit(event, x, y, pointer, toActor);
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            PreparingBattle battle = app.getCommandListener().getBattle();
           // Client client = app.getCommandListener().getClient();
            if (button == Input.Buttons.RIGHT) {
                if (event.getTarget().getName().equals("elf2")) {
                    System.out.println("elf2Touch");
                    try {
                        System.out.println("отправил сообщение об атаке");
                        Client client = app.getCommandListener().getClient();
                        client.sendData(new Sended(client.name, Const.IN_BATTLE, Const.FULLHP, battle));
                        app.getStage().addActor(new Title(Const.WIN));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return super.touchDown(event, x, y, pointer, button);
        }
    }

    public static class ItemListener extends InputListener {
        private Main app;

        public ItemListener() {
            app = ((Main) Gdx.app.getApplicationListener());
            System.out.println("ItemListener");
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            event.getTarget().setOrigin(event.getTarget().getX(), event.getTarget().getY());
            return (button == Input.Buttons.LEFT);
        }

        @Override
        public void touchDragged(InputEvent event, float x, float y, int pointer) {
            event.getTarget().setPosition(x - event.getTarget().getWidth() / 2, y - event.getTarget().getHeight() / 2);
            super.touchDragged(event, x, y, pointer);
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            PreparingBattle battle = app.getCommandListener().getBattle();
            //Client client = app.getCommandListener().getClient();
            for (Actor actor : event.getTarget().getStage().getActors())
                if (actor.getName().equals(ElfGroup.NAME)) {
                    Actor actorElf = actor.hit(x, y, true);
                    if (actorElf != null) {
                        Client client = app.getCommandListener().getClient();
                        client.sendData(new Sended(client.name, Const.IN_BATTLE, event.getTarget().getName(), battle));
                    }
                        /*((MagicItem) event.getTarget()).setCoolDown(true);
                        event.getTarget().setTouchable(Touchable.disabled);*/
                }
            event.getTarget().setPosition(event.getTarget().getOriginX(), event.getTarget().getOriginY());
            super.touchUp(event, x, y, pointer, button);
        }
    }
}
