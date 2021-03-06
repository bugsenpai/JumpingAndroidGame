package com.badlogic.androidgames.framework.impl;

import com.badlogic.androidgames.framework.gl.GLGraphics;
import com.badlogic.androidgames.framework.gamedev2d.math.Vector2;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by dell on 8/5/2016.
 * nguyen van phi tuoc
 */
public class Camera2D {
    public final Vector2 position;
    private float zoom;
    private final float frustumWidth;
    private final float frustumHeight;
    private final GLGraphics glGraphics;
    public Camera2D(GLGraphics glGraphics, float frustumWidth, float frustumHeight) {
        this.glGraphics = glGraphics;
        this.frustumWidth = frustumWidth;
        this.frustumHeight = frustumHeight;
        this.position = new Vector2(frustumWidth / 2, frustumHeight / 2);
        this.zoom = 1.0f;
    }

    public void setViewportAndMatrices() {
        GL10 gl = glGraphics.getGL();
        gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof((float)(position.x - frustumWidth * zoom / 2),
                (float)(position.x + frustumWidth * zoom/ 2),
                (float)( position.y - frustumHeight * zoom / 2),
                (float)position.y + frustumHeight * zoom/ 2,
                1, -1);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    public void touchToWorld(Vector2 touch) {
        touch.x = (touch.x / (float) glGraphics.getWidth()) * frustumWidth * zoom;
        touch.y = (1 - touch.y / (float) glGraphics.getHeight()) * frustumHeight * zoom;
        touch.add(position).sub(frustumWidth * zoom / 2, frustumHeight * zoom / 2);
    }
}
