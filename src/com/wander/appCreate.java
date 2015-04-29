package com.wander;

import android.app.Application;
import android.app.Notification;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.browse.MediaBrowser;
import android.preference.PreferenceManager;
import android.widget.MediaController;
import com.wander.wdTrip.R;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by wander on IDEA.
 * Date:15-4-28
 * Email:18955260352@163.com
 */
public class appCreate extends Application {
    public boolean bg_music;
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
//        init();

    }

    void init() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        bg_music = sharedPreferences.getBoolean("bg_music", true);
        if (bg_music) {
            startMusic();
        }
    }

    void startMusic() {
        mediaPlayer = MediaPlayer.create(this, R.raw.zoidtrip);
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
        }
//            mediaPlayer.prepare();
        mediaPlayer.start();
    }


    public void stopMusic() {
        if (mediaPlayer!=null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
        }
    }
}
