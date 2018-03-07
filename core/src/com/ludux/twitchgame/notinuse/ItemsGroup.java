/*package com.ludux.twitchgame.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.ludux.twitchgame.battle.battlelisteners.InputListeners;

public class ItemsGroup extends Group {
    public ItemsGroup() {
        this.addCaptureListener(new InputListeners.ItemListener());
    }

    public void draw(Batch batch) {
        drawChildren(batch, 0);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        Vector2 vector2 = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        for (Actor item : this.getChildren()) {
            if (((Item) item).hitDetection(item.stageToLocalCoordinates(vector2), item.isTouchable())) {
                System.out.println("Suchka");
                return item;
            }
        }
        return super.hit(x, y, touchable);
    }
}
*/