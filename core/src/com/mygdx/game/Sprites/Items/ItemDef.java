package com.mygdx.game.Sprites.Items;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by pradosmac on 12/3/18.
 */
public class ItemDef {
    public Vector2 position;
    public Class<?> type;

    public ItemDef(Vector2 position, Class<?> type){
        this.position = position;
        this.type = type;
    }
}
