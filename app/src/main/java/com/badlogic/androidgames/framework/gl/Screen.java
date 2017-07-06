package com.badlogic.androidgames.framework.gl;

/**
 * Created by dell on 7/30/2016.
 * nguyen van phi tuoc
 */
public abstract class Screen {
    protected final Game game;

    public Screen(Game game) {
        this.game = game;
    }

    public abstract void update(float deltaTime);

    public abstract void present(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
}
