package com.hoanganhtuan95ptit.twolineseekbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hoanganhtuan95ptit.library.TwoLineSeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TwoLineSeekBar twoLineSeekBar = findViewById(R.id.twoLine);
        twoLineSeekBar.setOnSeekChangeListener(new TwoLineSeekBar.OnSeekChangeListener() {
            @Override
            public void onSeekChanged(float value, float step) {

            }

            @Override
            public void onSeekStopped(float value, float step) {

            }
        });

        twoLineSeekBar.setOnSeekDownListener(new TwoLineSeekBar.OnSeekDownListener() {
            @Override
            public void onSeekDown() {

            }
        });

        twoLineSeekBar.setOn
    }
}
