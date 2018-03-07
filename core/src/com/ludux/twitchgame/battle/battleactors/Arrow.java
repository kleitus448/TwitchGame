package com.ludux.twitchgame.battle.battleactors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.GifDecoder;
import com.ludux.twitchgame.Main;
import com.ludux.twitchgame.Sended;
import com.ludux.twitchgame.Server.Connection.Client;
import com.ludux.twitchgame.Server.Connection.PreparingBattle;
import com.ludux.twitchgame.items.Item;

public class Arrow extends Item {

    private byte flag;
    public static final String USUAL_STYLE = "usualStyle";
    public static final String FIRE_STYLE = "fireStyle";
    private static Animation<TextureRegion> ARROW = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP,
            Gdx.files.internal("core/assets/Sprites/objects/arrow.gif").read());
    private static Animation<TextureRegion> FIRE_ARROW = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP,
            Gdx.files.internal("core/assets/Sprites/objects/firearrow.gif").read());

    private ElfActor attackedElf;
    public Arrow(ElfActor attackedElf, String style){
        flag = (byte) (attackedElf.getName().equals(ElfActor.ELF1) ? -1 : 1);
        setName("arrow");
        setTouchable(Touchable.disabled);
        this.attackedElf = attackedElf;

        //10 - модуль скорости стрелы
        this.speed = 10 * flag;

        //Проверка на вид стрелы, procent - урон от неё
        if (style.equals(USUAL_STYLE)) {animation = ARROW;}
        else if (style.equals(FIRE_STYLE)) {animation = FIRE_ARROW;}

        //Размер стрелы
        sprite = new Sprite(animation.getKeyFrame(0));
        setSize(sprite.getWidth()/3, sprite.getHeight()/3);

        ElfActor elf = attackedElf.getParent().findActor(flag > 0 ? ElfActor.ELF1 : ElfActor.ELF2);
        int width = elf.getAnimation("attack").getKeyFrame(0).getRegionWidth();
        int height = elf.getAnimation("attack").getKeyFrame(0).getRegionHeight();
        setPosition(elf.getX() + ((flag + 1)/2) * width * (2f/3f), elf.getY() + height * (2f/5f));
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        sprite.setSize(getWidth()*flag, sprite.getHeight());
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        setX(getX() + speed);
        if (arrowCatch() && !(attackedElf.isFlagAnimation())) {
            Main app = (Main) Gdx.app.getApplicationListener();
            Client client = app.getCommandListener().getClient();
            PreparingBattle battle = app.getCommandListener().getBattle();
            client.sendData(new Sended(client.name, Const.IN_BATTLE, Const.HURT_BASE, battle)); //Я БЛЯТЬ 4 ЧАСА ИСКАЛ У СЕБЯ ОШИБКУ В ПЕРЕДАЧЕ КЛИЕНТОВ ПОКА СУКА НЕ ЗАМЕТИЛ ЭТУ СТРОЧКУ
        }
        super.act(delta);
    }

    //Проверка попадания стрелы
    private boolean arrowCatch() {
        Vector2 vector2 = attackedElf.stageToLocalCoordinates(new Vector2(getX(), getY()));
        return attackedElf.hit(vector2.x, vector2.y, attackedElf.isTouchable()) == attackedElf;
    }
}
