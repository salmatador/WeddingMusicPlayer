package com.moonstub.weddingmusicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    static final String GIVE_UP = "i wont give up";
    static final String OPEN_DOOR = "love is an open door";
    static final String IMPERIAL_MARCH = "the imperial march";
    static final String SEE_YOU_AGAIN = "see you again";

    Button mFwdBtn;
    Button mStpBtn;
    Button mBwdBtn;

    MusicManager mMusicManager;
    int mSongTracker = 3;
    private String[] mSongStrings = {GIVE_UP, OPEN_DOOR, IMPERIAL_MARCH, SEE_YOU_AGAIN};
    private final int[] SONG_ID = new int[]{R.raw.see_you_again, R.raw.the_imperial_march, R.raw.love_is_an_open_door, R.raw.i_wont_give_up};
    private int NumberOfSongs = 4;
    MusicController mc;
    private boolean allStop = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mc = new MusicController(this, SONG_ID);
        //mMusicManager = new MusicManager(this);
        //mMusicManager.setTracks(loadMusicFiles());

        mFwdBtn = (Button) findViewById(R.id.fade_fwd_btn);
        mFwdBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mc.play(mSongTracker);
                mSongTracker = (mSongTracker + 1) % NumberOfSongs;
            }
        });
        mStpBtn = (Button) findViewById(R.id.fade_stop_btn);
        mStpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mc.stop((mSongTracker - 1 >= 0) ? mSongTracker : SONG_ID.length - 1);
            }
        });
//        mBwdBtn = (Button) findViewById(R.id.fade_bwd_btn);
//        mBwdBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mc.play(mSongTracker);
//                mSongTracker = (mSongTracker - 1 >= 0) ? (mSongTracker - 1) : NumberOfSongs - 1;
//            }
//        });
    }

    private HashMap<String, InputStream> loadMusicFiles() {
        HashMap<String, InputStream> temp = new HashMap<>();
        for (String fileName : mSongStrings) {
            try {
                temp.put(fileName, getAssets().open(fileName));
            } catch (IOException e) {
                Toast.makeText(this, "Error Loading..." + fileName, Toast.LENGTH_LONG).show();
            }
        }
        return temp;
    }
}
