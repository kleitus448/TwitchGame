package com.ludux.twitchgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ludux.twitchgame.menu.MenuButton;

public class ShopScreen extends ScreenAdapter {
    private String name;
    public ShopScreen(Stage stage, ShapeRenderer renderer, String name) {
        this.name = name;
        stage.setViewport(new FitViewport(Const.LANDSCAPE_VIEWPORT_WIDTH, Const.LANDSCAPE_VIEWPORT_HEIGHT));
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        Table table = new Table().left().top().padTop(200).padLeft(50); table.debug();
        table.setBackground(Const.menu_background);
        table.setSize(stage.getWidth(), stage.getHeight());
        int width = Const.cell.getWidth()*2; int height = Const.cell.getHeight()*2;
        table.defaults().minSize(width, height);
        //table.add(new MenuButton("Купить предмет", name));
        //table.add(new MenuButton("Продать предмет", name));
        //table.add(new MenuButton("Назад", name));
        //table.add().row();

        //Добавление ячеек магазина и инвентаря
        for (int i = 1; i <= 40; i++) {
            table.add(new Image(Const.cell));
            if (i % 5 == 0) table.row();
        }
        stage.addActor(table);

        //Добавление картинки
        Image image = new Image(Const.shop_texture);
        image.setScale(2.4f, 2.5f);
        image.setPosition(Const.LANDSCAPE_VIEWPORT_WIDTH*(3.95f/6f),
                Const.LANDSCAPE_VIEWPORT_HEIGHT/100f);
        stage.addActor(image);
    }

    @Override
    public void render(float delta) {Gdx.app.getApplicationListener().render();}

}
