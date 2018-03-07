package com.ludux.twitchgame.notinuse.objects;

import com.badlogic.gdx.graphics.Texture;

public class GameObject {
    private float x = 0;
    private float y = 0;
    private float speed;
    private int time;
    private float width;
    private float height;
    private Texture texture;

    protected float getX() {return x;}
    protected void setX(float x) {this.x = x;}
    protected float getY() {return y;}
    protected void setY(float y) {this.y = y;}

    protected float getSpeed() {return speed;}
    protected void setSpeed(float speed) {this.speed = speed;}

    protected Texture getTexture() {return texture;}
    protected void setTexture(Texture texture) {this.texture = texture;}

    protected int getTime() {return time;}
    protected void setTime(int time) {this.time = time;}

    protected float getWidth() {return width;}
    protected void setWidth(float width) {this.width = width;}
    protected float getHeight() {return height;}
    protected void setHeight(float height) {this.height = height;}

    protected GameObject(Texture texture, int time) {
        this.texture = texture; this.time = time;
    }

    public String toString() {
        return  "[" + this.getClass() + "]" + "\n" +
                "x: " + x + "; " + "y: " + y + "; " + "speed: " + speed + "; " + "time: " + time + "\n" +
               "width: " + width + "; " + "height: " + height;
    }
}
