package com.example.jdmclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String BUTTONS_MESSAGE = "BUTTONS";

    private int _moneyCount = 0;

    private int _trackDistance = 5;
    private int _trackReward = 10;
    private int _trackProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar driveProgressBar = findViewById(R.id.driveProgressBar);
        driveProgressBar.setMax(100);
        driveProgressBar.setProgress(0);

        TextView moneyCountText = findViewById(R.id.moneyCount_text);

        Button driveButton = findViewById(R.id.driveButton);
        driveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _trackProgress++;
                int progress = (_trackProgress * 100) / _trackDistance;

                if(progress < 100){
                    driveProgressBar.setProgress(progress);
                }
                else{
                    _trackProgress = 0;
                    driveProgressBar.setProgress(0);

                    _moneyCount += _trackReward;
                    moneyCountText.setText(_moneyCount + "$");
                }
            }
        });
    }
}