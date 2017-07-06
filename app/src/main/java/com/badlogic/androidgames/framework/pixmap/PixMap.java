package com.badlogic.androidgames.framework.pixmap;
import com.badlogic.androidgames.framework.pixmap.graphics.Graphics.PixMapFormat;

/**
 * Created by dell on 7/30/2016.
 */
public interface PixMap {
    public int getWidth();
    public int getHeight();
    public PixMapFormat getFormat();
    public void dispose();
}
