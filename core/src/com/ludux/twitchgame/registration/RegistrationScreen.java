package com.ludux.twitchgame.registration;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.GifDecoder;
import com.ludux.twitchgame.Main;
import com.ludux.twitchgame.screens.Screens;

public class RegistrationScreen extends ScreenAdapter {
    private float time = 0;
    private Animation<TextureRegion> animation;
    private Stage stage;

    public RegistrationScreen(Stage stage, ShapeRenderer renderer) {
        this.stage = stage;
        this.stage.setViewport(new FitViewport(Const.REGISTRATION_WIDTH, Const.REGISTRATION_HEIGHT));
        this.stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP,
                Gdx.files.internal("core/assets/Sprites/registration/register_bg.gif").read());
        Table table = new Table();
        table.setSize(this.stage.getWidth(), this.stage.getHeight());
        table.defaults().fill().spaceBottom(15);
        table.add(new RegistrationButton("Регистрация"));
        table.row();
        table.add(new RegistrationButton("Войти в игру"));
        this.stage.addActor(table);
        this.stage.addActor(new Screens.Blackscreen(renderer));
        /*((Main)Gdx.app.getApplicationListener()).setMusic
                (Gdx.audio.newMusic(Gdx.files.internal("core/assets/Music/Rusty Ruins Zone Act 1.mp3")));*/
    }

    @Override
    public void render (float delta) {
        time += delta;
        ((Table) stage.getActors().first()).setBackground(
                new TextureRegionDrawable(animation.getKeyFrame(time))
        );
        stage.act();
        stage.draw();
    }
}
