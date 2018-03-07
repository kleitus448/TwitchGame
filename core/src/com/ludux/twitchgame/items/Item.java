package com.ludux.twitchgame.items;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.ludux.twitchgame.battle.battleactors.BattleActor;
import com.ludux.twitchgame.battle.battleactors.ElfActor;

public class Item extends BattleActor {

    protected Animation<TextureRegion> animation;
    private int coolDownTime;
    private boolean coolDown = false;

    public void setCoolDownTime(int coolDownTime) {this.coolDownTime = coolDownTime;}
    public int getCoolDownTime() {return coolDownTime;}
    public boolean isCoolDown() {return coolDown;}
    public void setCoolDown(boolean coolDown) {this.coolDown = coolDown;}

    protected Item() {
        super(0);
        this.coolDownTime = 10;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        if (coolDownTime == 0) {
            coolDown = false;
            setTouchable(Touchable.enabled);
            coolDownTime = 10;}
        super.act(delta);
        time += delta;
        sprite = new Sprite(animation.getKeyFrame(time));
        sprite.setBounds(getX(),getY(),getWidth(),getHeight());
    }

    public boolean activate(ElfActor actor) {
        return true;
        //return (actor.getOtherElf().getBarMana().decrease(procent));
    }
}
