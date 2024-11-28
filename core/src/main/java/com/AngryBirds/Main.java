package com.AngryBirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private boolean musicopen,soundopen;
    private ArrayList<Integer> scores=new ArrayList<>();
    private ArrayList<Integer> stars;
    private Music bird;
    private Music pig;
    private Container gameContainer;

    public Container getGameContainer() {
        return gameContainer;
    }

    public void setGameContainer(Container gameContainer) {
        this.gameContainer = gameContainer;
    }

    public Music getSlingshot() {
        return slingshot;
    }

    public Music getBird() {
        return bird;
    }

    public Music getPig() {
        return pig;
    }

    private Music slingshot;

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public ArrayList<Integer> getStars() {
        return stars;
    }

    @Override
    public void create() {
        for (int i=0;i<=2;i++) {
            scores.add(0);
        }
        ContainerHandler.load(this);
        StarsHandler.load(this);
        MusicHandler.load(this);
        SoundHandler.load(this);
        bird = Gdx.audio.newMusic(Gdx.files.internal("bird.mp3"));
        pig = Gdx.audio.newMusic(Gdx.files.internal("pig.mp3"));
        slingshot = Gdx.audio.newMusic(Gdx.files.internal("slingshot.mp3"));
        bird.setLooping(false);
        pig.setLooping(false);
        slingshot.setLooping(false);
        bird.setVolume(0.5f);
        pig.setVolume(0.5f);
        slingshot.setVolume(0.5f);
        setScreen(new LoadingScreen(this));
//        setScreen(new Levelone(this));
//        setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }

    public boolean isMusicopen() {
        return musicopen;
    }

    public void setMusicopen(boolean musicopen) {
        this.musicopen = musicopen;
    }

    public boolean isSoundopen() {
        return soundopen;
    }

    public void setSoundopen(boolean soundopen) {
        this.soundopen = soundopen;
    }

    public void setScores(ArrayList<Integer> scores) {
        this.scores = scores;
    }

    public void setStars(ArrayList<Integer> stars) {
        this.stars = stars;
    }
}
