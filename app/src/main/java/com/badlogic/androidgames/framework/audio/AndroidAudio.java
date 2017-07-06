package com.badlogic.androidgames.framework.audio;

/**
 * Created by dell on 8/1/2016.
 * nguyen van phi tuoc
 */

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import com.badlogic.androidgames.framework.audio.music.AndroidMusic;
import com.badlogic.androidgames.framework.audio.sound.AndroidSound;
import com.badlogic.androidgames.framework.audio.music.Music;
import com.badlogic.androidgames.framework.audio.sound.Sound;

import java.io.IOException;

public class AndroidAudio implements Audio {
    private AssetManager assets;
    private SoundPool soundPool;

    public AndroidAudio(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.soundPool = new SoundPool.Builder()
                    .setMaxStreams(20)
                    .setAudioAttributes(new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_GAME)
                            .build())
                    .build();
        } else {
            this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
        }
    }

    @Override
    public Music newMusic(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            return new AndroidMusic(assetDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music '" + filename + "'");
        }
    }

    @Override
    public Sound newSound(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            int soundId = soundPool.load(assetDescriptor, 0);
            return new AndroidSound(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load sound '" + filename + "'");
        }
    }

}