package com.example.phituocpc.jumping.jumper.model;

import com.badlogic.androidgames.framework.gamedev2d.GameObject;

/**
 * Created by dell on 8/5/2016.
 * nguyen van phi tuoc
 */
public class Castle extends GameObject {
    public static float CASTLE_WIDTH = 1.7f;
    public static float CASTLE_HEIGHT = 1.7f;

    public Castle(float x, float y) {
        super(x, y, CASTLE_WIDTH, CASTLE_HEIGHT);
    }
}
