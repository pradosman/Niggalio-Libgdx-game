package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Niggalio;

/**
 * Created by pradosmac on 12/3/18.
 */

public class HelpScreen implements Screen{
    private Viewport viewport;
    private Stage stage;

    private Game game;

    public HelpScreen(Game game){
        this.game = game;
        viewport = new FitViewport(Niggalio.V_WIDTH, Niggalio.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Niggalio) game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("SALTAR: FLECHA ARRIBA", font);
        Label gameOverLabel2 = new Label("CORRER: FLECHA DERECHA / FLECHA IZQUIERDA", font);
        Label gameOverLabel3 = new Label("Pulsa Para Volver", font);

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(gameOverLabel2).expandX().padTop(10f);
        table.row();
        table.add(gameOverLabel3).expandX().padTop(10f);

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            game.setScreen(new TitleScreen((Niggalio) game));
            dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
