package com.ludux.twitchgame.battle.battleactors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.Main;
import com.ludux.twitchgame.Server.Connection.Client;
import com.ludux.twitchgame.Server.Connection.PreparingBattle;
import com.ludux.twitchgame.Server.Items.Item;
import com.ludux.twitchgame.Server.Items.Magic.MagicItem;
import com.ludux.twitchgame.battle.battlelisteners.InputListeners;
import com.ludux.twitchgame.items.HealingRing;
import com.ludux.twitchgame.label.BattleLabel;

import java.util.ArrayList;

public class Inventory extends Table {

    private TextureRegion texture = new TextureRegion(new Texture("core/assets/Sprites/inventory/inventory.png"));
    private Texture cooldownTexture = new Texture("core/assets/Sprites/inventory/cooldown.png");
    private BattleLabel cooldownLabel;
    private int slots = 9;
    private int activeSlots = 6;

    public Inventory() {
        setName("inventory"); //debug();
        left(); pad(texture.getTexture().getWidth()/19f);
        setBackground(new TextureRegionDrawable(texture));
        setSize(texture.getTexture().getWidth()*2,texture.getTexture().getHeight()*2);
        defaults().size(getWidth()/14.5f, getHeight()/1.7f).space(getWidth()/40f);
        Main app = (Main) Gdx.app.getApplicationListener();
        PreparingBattle battle = app.getCommandListener().getBattle();
        Client client = app.getCommandListener().getClient();
        ArrayList<MagicItem> arrayList = battle.checkName(client.name) ? battle.First.getUsableItems() :
                battle.Second.getUsableItems();
        for (MagicItem magicItem : arrayList) {
            switch (magicItem.getName()) {
                case Const.HEAL_RING: add(new HealingRing());
            }
        }
        /*for (int i = 0; i < activeSlots; i++) add(new HealingRing());
        add(new Ultimate()).space(getWidth()/2.95f).size(getWidth()/14f, getHeight()/1.6f);*/
        addCaptureListener(new InputListeners.ItemListener());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Main app = (Main) Gdx.app.getApplicationListener();
        PreparingBattle battle = app.getCommandListener().getBattle();
        Client client = app.getCommandListener().getClient();
        ArrayList<MagicItem> arrayList = battle.checkName(client.name) ? battle.First.getUsableItems() :
                battle.Second.getUsableItems();
        for (Cell cell: getCells()) cell.getActor().setTouchable(Touchable.enabled);
        for (MagicItem magicItem : arrayList) {
            if (magicItem.getCooldown() != 0) {
                Actor actor = null;
                for (Cell cell: getCells())
                    if (cell.getActor().getName().equals(magicItem.getName())) actor = cell.getActor();
                batch.draw(cooldownTexture, actor.getX(), actor.getY(),
                        actor.getWidth(), actor.getHeight());
                cooldownLabel = BattleLabel.getCooldownLabel(Integer.toString(magicItem.getCooldown()));
                cooldownLabel.setPosition(actor.getX() , actor.getY());
                cooldownLabel.draw(batch, parentAlpha);
                actor.setTouchable(Touchable.disabled);
            }
        }
    }
}

