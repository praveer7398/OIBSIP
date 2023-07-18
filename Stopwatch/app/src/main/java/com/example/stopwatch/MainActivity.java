package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView stopwatchdisplay;
    private Button startButton;
    private Button stopButton;
    private Button resetButton;
    private Handler handler;
    private boolean isRunning;
    private int seconds;
    private int milliseconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stopwatchdisplay=findViewById(R.id.stopwatch_display);
        startButton=findViewById(R.id.start_button);
        stopButton=findViewById(R.id.stop_button);
        resetButton=findViewById(R.id.reset_button);
        handler=new Handler();
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStopwatch();
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopStopwatch();
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetStopwatch();
            }
        });
    }
    private void resetStopwatch(){
        isRunning=false;
        seconds=0;
        milliseconds=0;
        stopwatchdisplay.setText("00:00:00:000");
    }
    private void stopStopwatch(){
        isRunning=false;
    }
    private void startStopwatch(){
        isRunning=true;
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int minutes=(seconds%3600)/60;
                int sec=seconds%60;
                String time=String.format("%d:%02d:%02d.%03d",hours,minutes,sec,milliseconds);
                stopwatchdisplay.setText(time);
                if(isRunning){
                    milliseconds++;
                    if(milliseconds==999){
                        seconds++;
                        milliseconds=0;
                    }
                }
                handler.postDelayed(this, 1);
            }
        });
    }
}