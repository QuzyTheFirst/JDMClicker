package com.example.jdmclicker.Scripts.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.jdmclicker.R;
import com.example.jdmclicker.Scripts.GameScripts.GameManager;

public class TracksFragment extends Fragment {

    private GameManager _gameManager;

    public TracksFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_tracks, container, false);

        _gameManager = GameManager.Instance;

        TextView moneyCountText = inflatedView.findViewById(R.id.moneyCount_text);
        moneyCountText.setText(_gameManager.getMoneyCount() + "$");

        return inflatedView;
    }
}