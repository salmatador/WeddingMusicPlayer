package com.moonstub.weddingmusicplayer;

import android.media.MediaPlayer;

/**
 * Created by mkline on 8/4/2016.
 */
public class MusicManager {

    MainActivity main;
    MediaPlayer mMediaPlayer;

    public MusicManager(MainActivity mainActivity) {
        main = mainActivity;
        mMediaPlayer = MediaPlayer.create();
    }
}
