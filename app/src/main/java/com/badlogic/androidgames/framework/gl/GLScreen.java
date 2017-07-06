package com.badlogic.androidgames.framework.gl;

/**
 * Created by dell on 8/5/2016.
 * nguyen van phi tuoc
 */
public abstract class GLScreen extends Screen {
    protected final GLGraphics glGraphics;
    protected final GLGame glGame;

    public GLScreen(Game game) {
        super(game);
        glGame = (GLGame) game;
        glGraphics = ((GLGame) game).getGLGraphics();
    }
}
