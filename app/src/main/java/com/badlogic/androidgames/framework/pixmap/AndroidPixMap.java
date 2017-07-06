package com.badlogic.androidgames.framework.pixmap;

import android.graphics.Bitmap;

import com.badlogic.androidgames.framework.pixmap.graphics.Graphics;

/**
 * Created by dell on 8/1/2016.
 * nguyen van phi tuoc
 */

public class AndroidPixMap implements PixMap {
    public Bitmap bitmap;
    public Graphics.PixMapFormat format;
    public AndroidPixMap(Bitmap bitmap, Graphics.PixMapFormat format) {
        this.bitmap = bitmap;
    }
    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }
    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }
    @Override
    public Graphics.PixMapFormat getFormat() {
        return format;
    }
    @Override
    public void dispose() {
        bitmap.recycle();
    }
}