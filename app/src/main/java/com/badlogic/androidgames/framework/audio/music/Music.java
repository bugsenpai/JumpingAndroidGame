package com.badlogic.androidgames.framework.audio.music;

/**
 * Created by dell on 7/30/2016.
 */
public interface Music {
    void play();

    void stop();

    void pause();

    void setLooping(boolean looping);

    void setVolume(float volume);

    boolean isPlaying();

    boolean isStopped();

    boolean isLooping();

    void dispose();
}
