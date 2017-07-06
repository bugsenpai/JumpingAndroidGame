package com.badlogic.androidgames.framework.input.handle.touch;

/**
 * Created by dell on 8/1/2016.
 */

import android.view.View.OnTouchListener;

import com.badlogic.androidgames.framework.input.Input.TouchEvent;

import java.util.List;

public interface TouchHandler extends OnTouchListener {
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public List<TouchEvent> getTouchEvents();
}
