package com.example.jdmclicker.Scripts.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jdmclicker.R;
import com.example.jdmclicker.Scripts.Events.IEvent;
import com.example.jdmclicker.Scripts.GameScripts.GameManager;

import java.text.DecimalFormat;

public class GameFragment extends Fragment {

    private GameManager _gameManager;

    private TextView _moneyCountText;

    private IEvent<Float> _myMoneyChangeMethod;

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

        _moneyCountText = inflatedView.findViewById(R.id.moneyCount_text);
        _moneyCountText.setText(new DecimalFormat("##.##").format(_gameManager.getMoneyCount()) + "$");

        _myMoneyChangeMethod = this::MoneyChanged;
        _gameManager.OnMoneyValueChange.AddCallback(_myMoneyChangeMethod);

        Button driveButton = inflatedView.findViewById(R.id.driveButton);
        driveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _gameManager.OnDriveButtonClick();

                driveProgressBar.setProgress(_gameManager.getTrackProgress());
            }
        });

        return inflatedView;
    }

    public void MoneyChanged(Object source, Float moneyCount){
        Log.i("myData", "money visual updated");
        _moneyCountText.setText(new DecimalFormat("##.##").format(moneyCount) + "$");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("myData", "onDestroy");
        _gameManager.OnMoneyValueChange.RemoveCallback(_myMoneyChangeMethod);
    }



}