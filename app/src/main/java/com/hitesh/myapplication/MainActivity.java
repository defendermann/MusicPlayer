package com.hitesh.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button play;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.button);
        play.setText(">");
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.samplefile);

    }
    public void play(View v) {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            play.setText(">");
        } else {
            mediaPlayer.start();
            play.setText("||");
        }
    }

}

