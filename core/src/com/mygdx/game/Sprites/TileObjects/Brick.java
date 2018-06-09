package com.mygdx.game.Sprites.TileObjects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Niggalio;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Sprites.Nigga;

/**
 * Created by pradosmac on 12/3/18.
 */
public class Brick extends InteractiveTileObject {
    public Brick(PlayScreen screen, MapObject object){
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(Niggalio.BRICK_BIT);
    }

    @Override
    public void onHeadHit(Nigga mario) {

        Niggalio.manager.get("audio/sounds/bump.wav", Sound.class).play();
    }

}
