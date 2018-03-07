package com.ludux.twitchgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;

public class Cursors {
    private static final Pixmap attackPixmap = new Pixmap(Gdx.files.internal("core/assets/Sprites/cursors/attackCursor.png"));
    private static final Cursor attackCursor = Gdx.graphics.newCursor(attackPixmap, attackPixmap.getWidth() - 1, attackPixmap.getHeight() - 1);

    public static Cursor getCursor(String cursor) {
        switch (cursor) {
            case Const.ATTACK:
                return attackCursor;
        }
        return null;
    }
}
