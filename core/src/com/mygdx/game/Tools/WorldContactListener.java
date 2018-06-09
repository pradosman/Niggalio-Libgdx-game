package com.mygdx.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.Niggalio;
import com.mygdx.game.Sprites.Enemies.Enemy;
import com.mygdx.game.Sprites.Items.Item;
import com.mygdx.game.Sprites.Nigga;
import com.mygdx.game.Sprites.Other.FireBall;
import com.mygdx.game.Sprites.TileObjects.InteractiveTileObject;

/**
 * Created by pradosmac on 12/3/18.
 */
public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef){
            case Niggalio.MARIO_HEAD_BIT | Niggalio.BRICK_BIT:
            case Niggalio.MARIO_HEAD_BIT | Niggalio.COIN_BIT:
                if(fixA.getFilterData().categoryBits == Niggalio.MARIO_HEAD_BIT)
                    ((InteractiveTileObject) fixB.getUserData()).onHeadHit((Nigga) fixA.getUserData());
                else
                    ((InteractiveTileObject) fixA.getUserData()).onHeadHit((Nigga) fixB.getUserData());
                break;
            case Niggalio.ENEMY_HEAD_BIT | Niggalio.MARIO_BIT:
                if(fixA.getFilterData().categoryBits == Niggalio.ENEMY_HEAD_BIT)
                    ((Enemy)fixA.getUserData()).hitOnHead((Nigga) fixB.getUserData());
                else
                    ((Enemy)fixB.getUserData()).hitOnHead((Nigga) fixA.getUserData());
                break;
            case Niggalio.ENEMY_BIT | Niggalio.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == Niggalio.ENEMY_BIT)
                    ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case Niggalio.MARIO_BIT | Niggalio.ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == Niggalio.MARIO_BIT)
                    ((Nigga) fixA.getUserData()).hit((Enemy)fixB.getUserData());
                else
                    ((Nigga) fixB.getUserData()).hit((Enemy)fixA.getUserData());
                break;
            case Niggalio.ENEMY_BIT | Niggalio.ENEMY_BIT:
                ((Enemy)fixA.getUserData()).hitByEnemy((Enemy)fixB.getUserData());
                ((Enemy)fixB.getUserData()).hitByEnemy((Enemy)fixA.getUserData());
                break;
            case Niggalio.ITEM_BIT | Niggalio.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == Niggalio.ITEM_BIT)
                    ((Item)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Item)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case Niggalio.ITEM_BIT | Niggalio.MARIO_BIT:
                if(fixA.getFilterData().categoryBits == Niggalio.ITEM_BIT)
                    ((Item)fixA.getUserData()).use((Nigga) fixB.getUserData());
                else
                    ((Item)fixB.getUserData()).use((Nigga) fixA.getUserData());
                break;
            case Niggalio.FIREBALL_BIT | Niggalio.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == Niggalio.FIREBALL_BIT)
                    ((FireBall)fixA.getUserData()).setToDestroy();
                else
                    ((FireBall)fixB.getUserData()).setToDestroy();
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
