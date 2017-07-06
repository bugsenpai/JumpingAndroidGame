package com.badlogic.androidgames.framework.audio;

import com.badlogic.androidgames.framework.audio.music.Music;
import com.badlogic.androidgames.framework.audio.sound.Sound;

/**
 * Created by dell on 7/30/2016.
 * nguyen van phi tuoc
 */
public interface Audio {
    Music newMusic(String filename);

    Sound newSound(String filename);
}

