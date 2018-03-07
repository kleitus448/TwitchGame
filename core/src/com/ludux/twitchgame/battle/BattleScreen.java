package com.ludux.twitchgame.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ludux.twitchgame.Server.Connection.Client;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.Main;
import com.ludux.twitchgame.Server.Connection.PreparingBattle;
import com.ludux.twitchgame.battle.battleactors.*;
import com.ludux.twitchgame.label.BattleLabel;

public class BattleScreen extends ScreenAdapter {

    private Stage stage;
    private Main app;

    public BattleScreen(PreparingBattle battle, Client client) {
        Gdx.graphics.setResizable(true);
        app = (Main) Gdx.app.getApplicationListener();

        //Изменение матрицы проекции для полосок здоровья и маны
        ShapeRenderer renderer = app.getRenderer();
        Matrix4 matrix4 = renderer.getProjectionMatrix().setToOrtho2D
                (0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        renderer.setProjectionMatrix(matrix4);

        this.stage = app.getStage();
        this.stage.setViewport(new FitViewport(Const.LANDSCAPE_VIEWPORT_WIDTH, Const.LANDSCAPE_VIEWPORT_HEIGHT));
        this.stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        this.stage.addActor(new BALocation());
        this.stage.addActor(new ElfGroup(renderer));
        this.stage.addActor(new Inventory());
        this.stage.addActor(new BattleButton("Закончить ход", app.getCommandListener().getClient()));
        //app.getMusic().stop();
        //app.setMusic(Gdx.audio.newMusic(Gdx.files.internal("core/assets/Music/Guiles_Theme.mp3")));
        app.getCommandListener().changeTouch();
    }

    @Override
    public void render(float delta) {
        String name = app.getCommandListener().getClient().name;
        stage.addActor(BattleLabel.getBattleLabels(app.getCommandListener().getBattle(), name));
        stage.act();
        stage.draw();
        stage.getActors().pop();
    }

    @Override
    public void resize(int width, int height) {stage.getViewport().update(width,height);}
}


