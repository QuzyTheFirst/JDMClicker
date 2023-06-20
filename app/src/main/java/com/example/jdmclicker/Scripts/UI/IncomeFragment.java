package com.example.jdmclicker.Scripts.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jdmclicker.R;
import com.example.jdmclicker.Scripts.GameScripts.GameManager;

public class IncomeFragment extends Fragment {

    private GameManager _gameManager;

    public IncomeFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_income, container, false);

        _gameManager = GameManager.Instance;

        TextView moneyCountText = inflatedView.findViewById(R.id.moneyCount_text);
        moneyCountText.setText(_gameManager.getMoneyCount() + "$");

        RecyclerView rvIncomes = inflatedView.findViewById(R.id.rvIncomes);
        _gameManager.getShop().GenerateIncomesRecyclerView(rvIncomes);

        return inflatedView;
    }
}