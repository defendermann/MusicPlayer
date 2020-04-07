package com.hitesh.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    Button play;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;

    double startTime = 0;
    double finalTime;
    int currentPosition;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.button);
        play.setText(">");
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.samplefile);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();
        seekBar.setProgress((int)startTime);
    }
    public void play(View v) {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            play.setText(">");

        } else {
            mediaPlayer.start();
            play.setText("||");
            finalTime = mediaPlayer.getDuration();
            seekBar.setMax((int)finalTime);
            currentPosition = mediaPlayer.getCurrentPosition();
            seekBar.setProgress((int)startTime);

            //run handler
            handler.postDelayed(updateSeekBarValue, 50);
        }
    }

    //define runnable param for input
    private Runnable updateSeekBarValue = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            seekBar.setProgress((int)startTime);
            handler.postDelayed(this, 50);
        }
    };

}

