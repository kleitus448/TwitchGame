package com.ludux.twitchgame.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.Main;
import com.ludux.twitchgame.Server.Connection.Client;

public class MenuScreen extends ScreenAdapter {
    private float time = 0;
    private Stage stage;
    private ShapeRenderer renderer;

    public MenuScreen(Client client) {
        String name = client.name;
        Main app = ((Main)Gdx.app.getApplicationListener());
        Gdx.graphics.setWindowedMode(1366,768);
        Gdx.graphics.setResizable(true);
        this.stage = app.getStage(); this.renderer = app.getRenderer();
        this.stage.setViewport(new FitViewport(Const.LANDSCAPE_VIEWPORT_WIDTH, Const.LANDSCAPE_VIEWPORT_HEIGHT));
        this.stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        Table table = new Table(); //table.debug();
        table.setBackground(Const.menu_background);
        table.setSize(this.stage.getWidth(), this.stage.getHeight());
        int width = Const.menuButton_noactive.getRegion().getTexture().getWidth();
        int height = Const.menuButton_noactive.getRegion().getTexture().getHeight();
        table.padRight(10).padTop(250);
        table.defaults().spaceBottom(60).spaceRight(100).minSize(width*1.8f, height*1.8f);
        table.add(new MenuButton("Игра по сети", name));
        table.add(new MenuButton("Инвентарь", name));
        table.add(new MenuButton("Магазин", name)); table.row();
        table.add(new MenuButton("Настройки", name));
        table.add(new MenuButton("Профиль", name)); table.row();
        table.add(new MenuButton("Авторы", name));
        table.add(new MenuButton("Выход", name)); table.row();
        Image image = new Image(Const.name_texture);
        image.setScaleX(1.05f);
        image.setPosition(51, 800);
        this.stage.addActor(table);
        this.stage.addActor(image);
        Gdx.graphics.setTitle(" " + name);
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }
}
