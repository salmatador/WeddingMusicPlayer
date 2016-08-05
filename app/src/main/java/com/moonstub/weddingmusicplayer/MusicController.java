package com.moonstub.weddingmusicplayer;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Handler;

/**
 * Created by mkline on 8/5/2016.
 */
public class MusicController {

    MainActivity mMain;
    MediaPlayer[] mMediaPlayers = new MediaPlayer[4];
    int[] mSongs;

    public MusicController(MainActivity main, int songs[]) {
        mMain = main;
        mSongs = songs;
    }

    public void stop(int song) {
        if (mMediaPlayers[song] != null && mMediaPlayers[song].isPlaying()) {
            fadeOut(mMediaPlayers[song]);
        }
    }

    public void play(int playSong) {
        int index = (playSong - 1 >= 0) ? playSong - 1 : mMediaPlayers.length - 1;
//        Log.e(index + " Index", playSong + " Song Number");

        mMediaPlayers[playSong] = MediaPlayer.create(mMain, mSongs[playSong], new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setFlags(AudioAttributes.FLAG_AUDIBILITY_ENFORCED)
                .setUsage(AudioAttributes.USAGE_GAME).build(), playSong);

        if (mMediaPlayers[playSong] != null && !mMediaPlayers[playSong].isPlaying()) {
            fadeIn(mMediaPlayers[playSong]);

        }
        try {
            if (mMediaPlayers[index] != null && mMediaPlayers[index].isPlaying()) {
                fadeOut(mMediaPlayers[index]);
            }
        } catch (Exception e) {
            //Dont Care;
        }
    }

    public static void fadeOut(final MediaPlayer mediaPlayer) {
        final int duration = 5000;
        final float deviceVolume = 1f;
        final Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            private float time = duration;
            private float volume = 0.0f;

            @Override
            public void run() {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                time -= 100;
                volume = (deviceVolume * time) / duration;
                mediaPlayer.setVolume(volume, volume);
                if (time > 0) {
                    handle.postDelayed(this, 100);
                } else {
                    // Log.e("Stopped Player", mediaPlayer.toString());
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.release();

                }
                //Log.e("SONG", "RUN OUT " + volume);
            }

        }, 100);
    }

    public static void fadeIn(final MediaPlayer mediaPlayer) {
        final int duration = 5000;
        final float deviceVolume = 1f;
        final Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            private float time = 0.0f;
            private float volume = 0.0f;

            @Override
            public void run() {
                //Log.e("SONG", "  " + volume);
                if (!mediaPlayer.isPlaying()) {
                    //   Log.e("Started Player", mediaPlayer.toString());
                    mediaPlayer.start();
                }
                time += 100;
                volume = (deviceVolume * time) / duration;
                mediaPlayer.setVolume(volume, volume);
                if (time < duration) {
                    handle.postDelayed(this, 100);
                }

            }

        }, 100);
    }
}
