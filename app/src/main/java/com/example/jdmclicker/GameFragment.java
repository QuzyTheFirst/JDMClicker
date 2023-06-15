package com.example.jdmclicker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GameFragment extends Fragment {

    private int _moneyCount = 0;

    private int _trackDistance = 5;
    private int _trackReward = 10;
    private int _trackProgress = 0;

    public GameFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_game, container, false);

        ProgressBar driveProgressBar = inflatedView.findViewById(R.id.driveProgressBar);
        driveProgressBar.setMax(100);
        driveProgressBar.setProgress(0);

        TextView moneyCountText = inflatedView.findViewById(R.id.moneyCount_text);
        moneyCountText.setText(_moneyCount + "$");

        Button driveButton = inflatedView.findViewById(R.id.driveButton);
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

        return inflatedView;
    }
}