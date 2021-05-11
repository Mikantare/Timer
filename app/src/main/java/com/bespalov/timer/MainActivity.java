package com.bespalov.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private boolean timerRun = false;
    private SeekBar seekBarTimer;
    private TextView textViewTimer;
    private Button buttonStart;
    private CountDownTimer myTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        seekBarTimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int mlSecs = seekBarTimer.getProgress();
                setTextViewTimer(mlSecs);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void init() {
        seekBarTimer = findViewById(R.id.seekBarTimer);
        textViewTimer = findViewById(R.id.textViewTimer);
        buttonStart = findViewById(R.id.buttonStart);
        seekBarTimer.setMax(600000);
        seekBarTimer.setProgress(30000);
        setTextViewTimer(seekBarTimer.getProgress());

    }

    public void setTextViewTimer (int seconds) {
        int minutes = seconds / 60000;
        int secs = (seconds % 60000) / 1000;
        textViewTimer.setText(String.format(Locale.getDefault(), "%02d:%02d", minutes,secs));
        }

//        final Handler handler = new Handler();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                Log.d("Runnable", "two second passsed");
//                handler.postDelayed(this,2000);
//            }
//        }; handler.post(runnable);
     public void startTimer (int mlSecs) {
         myTimer = new CountDownTimer(mlSecs , 1000) {
             @Override
             public void onTick(long millisUntilFinished) {
                 setTextViewTimer((int) millisUntilFinished);
             }

             @Override
             public void onFinish() {

             }
         };
         myTimer.start();
     }

    public void clickTimer(View view) {
        if (!timerRun) {
          startTimer(seekBarTimer.getProgress());
            timerRun = true;
            buttonStart.setText("Stop");
            seekBarTimer.setEnabled(false);

        } else if (timerRun) {
            buttonStart.setText("Start");
            seekBarTimer.setEnabled(true);
            myTimer.cancel();
            timerRun = false;
            init();
        }
    }
}