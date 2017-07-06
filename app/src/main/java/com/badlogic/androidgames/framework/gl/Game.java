package com.badlogic.androidgames.framework.gl;

import com.badlogic.androidgames.framework.audio.Audio;
import com.badlogic.androidgames.framework.fileio.FileIO;
import com.badlogic.androidgames.framework.pixmap.graphics.Graphics;
import com.badlogic.androidgames.framework.input.Input;

/**
 * Created by dell on 7/30/2016.
 * nguyen van phi tuoc
 */
public interface Game {
    Input getInput();

    FileIO getFileIO();

    Graphics getGraphics();

    Audio getAudio();

    void setScreen(Screen screen);

    Screen getCurrentScreen();

    Screen getStartScreen();
}
