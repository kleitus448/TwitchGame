package com.ludux.twitchgame.battle.battleactors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.battle.bars.HPBar;
import com.ludux.twitchgame.battle.bars.LostBar;
import com.ludux.twitchgame.battle.bars.ManaBar;

public class ElfActor extends BattleActor {

    public static final String ELF1 = "elf1";
    public static final String ELF2 = "elf2";

    //Анимации и изображения эльфов
    private TextureAtlas textureAtlas = new TextureAtlas(
            Gdx.files.internal("core/assets/Sprites/character/elf/Elf.atlas")
    );
    private Animation<TextureAtlas.AtlasRegion> currentAnimation;
    private Animation<TextureAtlas.AtlasRegion> standAnimation = new Animation<>
            (0.15f, textureAtlas.findRegions("stand"), Animation.PlayMode.LOOP);
    private Animation<TextureAtlas.AtlasRegion> attackAnimation = new Animation<>
            (1f, textureAtlas.findRegions("attack"), Animation.PlayMode.NORMAL);
    private Animation<TextureAtlas.AtlasRegion> healAnimation = new Animation<>
            (0.1f, textureAtlas.findRegions("jump"), Animation.PlayMode.LOOP_PINGPONG);
    private Animation<TextureAtlas.AtlasRegion> hurtAnimation = new Animation<>
            (0.12f, textureAtlas.findRegions("hurt"), Animation.PlayMode.NORMAL);
    private Animation<TextureAtlas.AtlasRegion> deadAnimation = new Animation<>
            (0.2f, textureAtlas.findRegions("die"), Animation.PlayMode.REVERSED);

    //Флаг для смены анимации
    private boolean flagAnimation;
    private HPBar barHP;
    private ManaBar barMana;
    private LostBar barLost;

    public HPBar getBarHP() {return barHP;}
    public ManaBar getBarMana() {return barMana;}
    public boolean isFlagAnimation() {return flagAnimation;}

    public ElfActor(String name, float time, ShapeRenderer renderer) {
        super(time); setName(name);
        currentAnimation = standAnimation;
        sprite = new Sprite(currentAnimation.getKeyFrame(time));
        if (name.equals("elf1")) {
            setPosition(Const.LANDSCAPE_VIEWPORT_WIDTH / 6f, Const.LANDSCAPE_VIEWPORT_HEIGHT / 10f);
            sprite.setPosition(getX(), getY());
        } else {
            setPosition(Const.LANDSCAPE_VIEWPORT_WIDTH * (5 / 6f) - sprite.getWidth(), Const.LANDSCAPE_VIEWPORT_HEIGHT / 10f);
            sprite.setX(getX() + sprite.getWidth());
        }
        barLost = new LostBar(renderer, this);
        barHP = new HPBar(renderer, this);
        barMana = new ManaBar(renderer, this);
    }

    public void setCurrentAnimation(Animation<TextureAtlas.AtlasRegion> currentAnimation) {
        this.currentAnimation = currentAnimation;
        flagAnimation = true;
        time = 0;
    }

    public Animation<TextureAtlas.AtlasRegion> getAnimation(String s) {
        switch (s) {
            case Const.STAND : return standAnimation;
            case Const.ATTACK : return attackAnimation;
            case Const.HURT : return hurtAnimation;
            case Const.HEALTH : return healAnimation;
            case Const.DIE : return deadAnimation;
        }
        return null;
    }

    private Sprite changeAnimationAction() {
        Sprite sprite = new Sprite(currentAnimation.getKeyFrame(time));
        setSize(sprite.getWidth(), sprite.getHeight());
        sprite.setBounds(this.sprite.getX(), getY(),
                getName().equals("elf1") ? sprite.getWidth() : -sprite.getWidth(), sprite.getHeight());
        time += currentAnimation.getFrameDuration() / 40;
        if ((flagAnimation) && (time >= currentAnimation.getAnimationDuration())) {
            currentAnimation = standAnimation;
            flagAnimation = false;
        } else time += Gdx.graphics.getDeltaTime();
        return sprite;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite = changeAnimationAction();
        sprite.draw(batch);
        batch.end();
        barLost.draw();
        barHP.draw();
        barMana.draw();
        batch.begin();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        barHP.act();
        barMana.act();
    }


    public ElfActor getOtherElf() {
        return getName().equals("elf1") ? getParent().findActor("elf2") : getParent().findActor("elf1");
    }
}



