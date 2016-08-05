package com.moonstub.weddingmusicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mFwdBtn;
    Button mStpBtn;
    Button mBwdBtn;

    MusicManager mMusicManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMusicManagerTrack1 = new MusicManager(this);
        mMusicManagerTrack2 = new MusicManager(this);

        mFwdBtn = (Button)findViewById(R.id.fade_fwd_btn);
        mFwdBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mMusicManager.playNextTrack();
            }
        });
        mStpBtn = (Button)findViewById(R.id.fade_stop_btn);
        mStpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMusicManager.stopTrack();
            }
        });
        mBwdBtn = (Button)findViewById(R.id.fade_bwd_btn);
        mBwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMusicManager.playPreviousTrack();
            }
        });
    }

    private void loadMusicFiles(){

    }
}
