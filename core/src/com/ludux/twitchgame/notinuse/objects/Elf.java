package com.ludux.twitchgame.notinuse.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.ludux.twitchgame.Const;

public class Elf extends GameObject {

    private Animation runningAnimation;
    private Rectangle rectangle;

    public Rectangle getRectangle() {return rectangle; }
    public void setRectangle(Rectangle rectangle) {this.rectangle = rectangle;}

    public Elf(byte choice) {
        super(new Texture("core/assets/Sprites/character/elf/elf.png"), 0);
        switch(choice){
            case 1:
                this.setX(Const.LANDSCAPE_VIEWPORT_WIDTH/6f);
                this.setY(Const.LANDSCAPE_VIEWPORT_HEIGHT/10f);
                this.setWidth(this.getTexture().getWidth()/3);
                this.setHeight(this.getTexture().getHeight()/3);
                this.rectangle = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
                break;
            case 2:
                this.setX(Const.LANDSCAPE_VIEWPORT_WIDTH * (5/6f));
                this.setY(Const.LANDSCAPE_VIEWPORT_HEIGHT/10f);
                this.setWidth(-this.getTexture().getWidth()/3);
                this.setHeight(this.getTexture().getHeight()/3);
                this.rectangle = new Rectangle(this.getX() + this.getWidth(), this.getY(), -this.getWidth(), this.getHeight());
                break;
        }
        TextureAtlas charset = new TextureAtlas(Gdx.files.internal("core/assets/Sprites/ElfStand.atlas"));
        Array<TextureAtlas.AtlasRegion> runningFrames = charset.findRegions("1_IDLE");
        runningAnimation = new Animation(2f, runningFrames, Animation.PlayMode.LOOP);
        System.out.println(this.toString());
    }

    public void draw(Batch batch) {
        batch.draw(this.getTexture(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    /*public static boolean CheckRectangle(Rectangle rectangle) {
        int pointer_x = Gdx.input.getX();
        int pointer_y = Const.LANDSCAPE_VIEWPORT_HEIGHT - Gdx.input.getY();
        return  (rectangle.contains(pointer_x, pointer_y));
    }*/


}
