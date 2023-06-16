package com.example.jdmclicker.Scripts.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jdmclicker.R;
import com.example.jdmclicker.Scripts.GameScripts.GameManager;

public class GameFragment extends Fragment {

    private GameManager _gameManager;

    public GameFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_game, container, false);

        ProgressBar driveProgressBar = inflatedView.findViewById(R.id.driveProgressBar);
        driveProgressBar.setMax(100);
        driveProgressBar.setProgress(0);

        _gameManager = GameManager.Instance;

        TextView moneyCountText = inflatedView.findViewById(R.id.moneyCount_text);
        moneyCountText.setText(_gameManager.getMoneyCount() + "$");

        Button driveButton = inflatedView.findViewById(R.id.driveButton);
        driveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _gameManager.OnDriveButtonClick();

                driveProgressBar.setProgress(_gameManager.getTrackProgress());
                moneyCountText.setText(_gameManager.getMoneyCount() + "$");
            }
        });

        return inflatedView;
    }
}