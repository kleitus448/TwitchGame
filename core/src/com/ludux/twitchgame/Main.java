package com.ludux.twitchgame;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ludux.twitchgame.Server.Connection.Client;
import com.ludux.twitchgame.command.CommandListener;
import com.ludux.twitchgame.registration.RegistrationScreen;

public class Main extends Game {
    private Stage stage;
    private ShapeRenderer renderer;
    private Music music;
    private CommandListener commandListener;

    public Music getMusic() {return music;}
    public void setMusic(Music music) {this.music = music;}

    public Stage getStage() {return stage;}
    public ShapeRenderer getRenderer() {return renderer;}

    public void setCommandListener(CommandListener commandListener) {this.commandListener = commandListener;}
    public CommandListener getCommandListener() {return commandListener;}
    public Client getClient() {return this.commandListener.getClient(); }
    public String getUser() {return commandListener.getClient().name;}

    @Override
    public void create() {
        stage = new Stage();
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        Gdx.input.setInputProcessor(stage);
        this.setScreen(new RegistrationScreen(stage, renderer));
        if (music != null) music.setVolume(0.2f);
    }

    @Override
    public void render() {
        if (commandListener != null) commandListener.check();
        try {
            if (!(music.isPlaying()) && music != null) music.play();
        } catch (Exception e) {}
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose() {stage.dispose(); renderer.dispose();}
}