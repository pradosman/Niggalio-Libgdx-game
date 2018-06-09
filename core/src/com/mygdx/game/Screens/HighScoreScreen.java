package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Niggalio;
import com.mygdx.game.Tools.Setting;

/**
 * Created by pradosmac on 12/3/18.
 */

public class HighScoreScreen extends ScreenAdapter {
    Niggalio game;
    OrthographicCamera guiCam;
    Rectangle backBounds;
    Vector3 touchPoint;
    String[] highScores;
    float xOffset = 0;
    GlyphLayout glyphLayout = new GlyphLayout();
    Texture items = loadTexture("data/items.png");
    BitmapFont font = new BitmapFont(Gdx.files.internal("data/font.fnt"), Gdx.files.internal("data/font.png"), false);
    Texture background = loadTexture("data/background.jpg");
    TextureRegion backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);
    TextureRegion highScoresRegion = new TextureRegion(items, 0, 257, 300, 110 / 3);

    public HighScoreScreen (Niggalio game) {
        this.game = game;

        guiCam = new OrthographicCamera(320, 480);
        guiCam.position.set(320 / 2, 480 / 2, 0);
        backBounds = new Rectangle(0, 0, 64, 64);
        touchPoint = new Vector3();
        highScores = new String[5];
        for (int i = 0; i < 5; i++) {
            highScores[i] = i + 1 + ". " + Setting.highscores[i];
            glyphLayout.setText(font, highScores[i]);
            xOffset = Math.max(glyphLayout.width, xOffset);
        }
        xOffset = 160 - xOffset / 2 + font.getSpaceWidth() / 2;
    }

    public void update () {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (backBounds.contains(touchPoint.x, touchPoint.y)) {
                game.setScreen(new TitleScreen(game));
                return;
            }
        }
    }

    public void draw () {
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();

        game.batch.setProjectionMatrix(guiCam.combined);
        game.batch.disableBlending();
        game.batch.begin();
        game.batch.draw(backgroundRegion, 0, 0, 320, 480);
        game.batch.end();

        game.batch.enableBlending();
        game.batch.begin();
        game.batch.draw(highScoresRegion, 10, 360 - 16, 300, 33);

        float y = 230;
        for (int i = 4; i >= 0; i--) {
            font.draw(game.batch, highScores[i], xOffset, y);
            y += font.getLineHeight();
        }

        game.batch.end();
    }

    @Override
    public void render (float delta) {
        update();
        draw();
    }

    public static Texture loadTexture (String file) {
        return new Texture(Gdx.files.internal(file));
    }
}
