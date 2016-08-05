package com.moonstub.weddingmusicplayer;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by mkline on 8/4/2016.
 */
public class MusicManager {
    //TODO TURN CROSS-FADE INTO AN ASYNC TASK
    //SONGS
    private static final int GIVE_UP = 0;
    private static final int OPEN_DOOR = 1;
    private static final int IMPERIAL_MARCH = 2;
    private static final int SEE_YOU_AGAIN = 3;

    MainActivity main;
    MediaPlayer mMediaPlayer;
    MediaPlayer mp;
    AudioManager mAudioManager;
    private HashMap<String, MediaPlayer> mTracks;
    private boolean turn = true;
    private long ms = 500;

    public MusicManager(MainActivity mainActivity) {
        main = mainActivity;
        mTracks = new HashMap<>();
//        mTracks.put(main.GIVE_UP,MediaPlayer.create(main,R.raw.i_wont_give_up, null, AudioManager.AUDIO_SESSION_ID_GENERATE));
//        mTracks.put(main.OPEN_DOOR, MediaPlayer.create(main,R.raw.love_is_an_open_door,null, AudioManager.AUDIO_SESSION_ID_GENERATE));
//        mTracks.put(main.IMPERIAL_MARCH, MediaPlayer.create(main,R.raw.the_imperial_march,null, AudioManager.AUDIO_SESSION_ID_GENERATE));
//        mTracks.put(main.SEE_YOU_AGAIN, MediaPlayer.create(main,R.raw.see_you_again,null, AudioManager.AUDIO_SESSION_ID_GENERATE));
    }

    public void playNextTrack(String s, int raw) {
        //MediaPlayer temp = mMediaPlayer.selectTrack(mMediaPlayer.getAudioSessionId());
//        if (turn) {
//            //mp = MediaPlayer.create(main, raw, null, AudioManager.AUDIO_SESSION_ID_GENERATE);
//            //mp.start();
//        } else {
        if (mMediaPlayer != null) {
            mp = mMediaPlayer;
        }
        mMediaPlayer = MediaPlayer.create(main, raw, null, AudioManager.AUDIO_SESSION_ID_GENERATE);
        mMediaPlayer.start();
        //}


        turn = !turn;

    }

    public void playStopTrack() {
        mp = mMediaPlayer;
        ms = 1000;
        stopCurrentTrack("NULL");
    }

    public void playPreviousTrack(int songTracker) {

    }

    public void setTracks(HashMap<String, InputStream> tracks) {
        //mTracks = tracks;
    }

    final float[] mpSoundIndex = {1.0f, 1.0f};

    public void stopCurrentTrack(String v) {

        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            final Handler handler2 = new Handler();

            Runnable tt = new Runnable() {
                @Override
                public void run() {
                    while (mpSoundIndex[1] > 0) {
                        try {
                            Thread.yield();
                            Thread.sleep(ms);
                            //handler2.post(this);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.e("Volume level", mpSoundIndex[1] + "");
                        mpSoundIndex[1] -= .1;
                        mp.setVolume(mpSoundIndex[1], mpSoundIndex[1]);
                        if (mpSoundIndex[1] <= 0) {

                            mp.reset();
                        }
                    }
                    mpSoundIndex[1] = 1.0f;

                }
            };
            handler2.post(tt);


        }
    }
}
