package com.badlogic.androidgames.framework.pixmap.graphics;

import com.badlogic.androidgames.framework.pixmap.PixMap;

/**
 * Created by dell on 7/30/2016.
 * nguyen van phi tuoc
 */
public interface Graphics {
    enum PixMapFormat {
        ARGB8888, ARGB4444, RGB565
    }
    PixMap newPixMap(String fileName, PixMapFormat format);
    void clear(int color);
    void drawPixel(int x, int y, int color);
    void drawLine(int x, int y, int x2, int y2, int color);
    void drawRect(int x, int y, int width, int height, int color);
    void drawPixMap(PixMap pixMap, int x, int y, int srcX, int srcY,
                    int srcWidth, int srcHeight);
    void drawPixMap(PixMap pixMap, int x, int y);
    int getWidth();
    int getHeight();
}