package com.hitesh.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Button play;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    TextView startTimeField;
    TextView endTimeField;

    double startTime = 0;
    double finalTime;
    int currentPosition;
    Handler handler = new Handler();
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startTimeField = findViewById(R.id.startTimeField);
        endTimeField = findViewById(R.id.endTimeField);

        play = (Button) findViewById(R.id.button);
        play.setText(">");
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.samplefile);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();
        seekBar.setProgress((int)startTime);
        startTimeField.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                TimeUnit.MILLISECONDS.toSeconds((long) startTime)));
        endTimeField.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime)));

    }
    @SuppressLint("DefaultLocale")
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
        @SuppressLint("DefaultLocale")
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            seekBar.setProgress((int)startTime);
            startTimeField.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime)));
            endTimeField.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) finalTime)));
            handler.postDelayed(this, 50);
        }
    };

}

