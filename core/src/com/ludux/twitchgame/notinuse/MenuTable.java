package com.ludux.twitchgame.notinuse;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.ludux.twitchgame.Const;

public class MenuTable extends Table {
    private TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("core/assets/Sprites/Menu/menu.atlas"));

    public MenuTable() {
        int buttonwidth = Const.LANDSCAPE_VIEWPORT_WIDTH*2/3;
        int buttonheight = Const.LANDSCAPE_VIEWPORT_HEIGHT/3;
        Button playOnlineButton = new Button(new TextureRegionDrawable(textureAtlas.findRegions("playonline").pop()));
        Button inventoryButton = new Button(new TextureRegionDrawable(textureAtlas.findRegions("inventory").first()));
        Button shopButton = new Button(new TextureRegionDrawable(textureAtlas.findRegions("shop").first()));
        Button exitButton = new Button(new TextureRegionDrawable(textureAtlas.findRegions("exit").first()));
        playOnlineButton.setName("playonline");
        inventoryButton.setName("inventory");
        shopButton.setName("shop");
        exitButton.setName("exit");
        add(playOnlineButton).fill().padBottom(20); row();
        add(shopButton).fill().padBottom(20); row();
        add(inventoryButton).fill().padBottom(20).row();
        center();
        //setWidth(Const.LANDSCAPE_VIEWPORT_WIDTH/1.5f);
        //setHeight(Const.LANDSCAPE_VIEWPORT_HEIGHT/1.5f);
    }

}
