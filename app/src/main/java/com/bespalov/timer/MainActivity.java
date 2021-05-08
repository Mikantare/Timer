package com.bespalov.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

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
        seekBarTimer = findViewById(R.id.seekBarTimer);
        textViewTimer = findViewById(R.id.textViewTimer);
        buttonStart = findViewById(R.id.buttonStart);
        seekBarTimer.setProgress(30);
        myTimer = new CountDownTimer(seekBarTimer.getProgress() * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textViewTimer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {

            }
        };
    }
//        final Handler handler = new Handler();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                Log.d("Runnable", "two second passsed");
//                handler.postDelayed(this,2000);
//            }
//        }; handler.post(runnable);


    public void clickTimer(View view) {
        if (!timerRun) {
          myTimer.start();
            timerRun = true;
            buttonStart.setText("Stop");
            seekBarTimer.setEnabled(false);

        } else if (timerRun) {
            buttonStart.setText("Start");
            seekBarTimer.setEnabled(true);
            myTimer.cancel();
            timerRun = false;
        }
    }
}