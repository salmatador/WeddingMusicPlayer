package com.moonstub.weddingmusicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mFwdBtn;
    Button mStpBtn;

    int mSongTracker = 0;
    private final int[] SONG_ID = new int[]{R.raw.see_you_again, R.raw.the_imperial_march, R.raw.love_is_an_open_door, R.raw.i_wont_give_up};
    private int NumberOfSongs = 4;
    MusicController mMusicController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMusicController = new MusicController(this, SONG_ID);

        mFwdBtn = (Button) findViewById(R.id.fade_fwd_btn);
        mFwdBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mMusicController.play(mSongTracker);
                mSongTracker = (mSongTracker + 1) % NumberOfSongs;
                mStpBtn.setEnabled(true);
            }
        });
        mStpBtn = (Button) findViewById(R.id.fade_stop_btn);
        mStpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMusicController.stop((mSongTracker - 1 >= 0) ? mSongTracker : SONG_ID.length - 1);
                mStpBtn.setEnabled(false);
            }
        });
    }



}
