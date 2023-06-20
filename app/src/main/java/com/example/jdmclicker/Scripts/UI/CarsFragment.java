package com.example.jdmclicker.Scripts.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jdmclicker.R;
import com.example.jdmclicker.Scripts.GameScripts.GameManager;

import java.text.DecimalFormat;

public class CarsFragment extends Fragment {

    private GameManager _gameManager;

    private TextView _moneyCountText;

    public CarsFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflatedView = inflater.inflate(R.layout.fragment_cars, container, false);

        _gameManager = GameManager.Instance;
        _gameManager.OnMoneyValueChange.AddCallback(this::MoneyChanged);

        _moneyCountText = inflatedView.findViewById(R.id.moneyCount_text);
        _moneyCountText.setText(new DecimalFormat("##.##").format(_gameManager.getMoneyCount()) + "$");

        RecyclerView rvCars = inflatedView.findViewById(R.id.rvCars);
        _gameManager.getShop().GenerateCarsRecyclerView(rvCars);

        return inflatedView;
    }

    public void MoneyChanged(Object source, Float moneyCount){
        Log.i("myData", "money visual updated");
        _moneyCountText.setText(new DecimalFormat("##.##").format(moneyCount) + "$");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _gameManager.OnMoneyValueChange.RemoveCallback(this::MoneyChanged);
    }

    @Override
    public void onPause() {
        super.onPause();
        _gameManager.OnMoneyValueChange.RemoveCallback(this::MoneyChanged);
    }

    @Override
    public void onStop() {
        super.onStop();
        _gameManager.OnMoneyValueChange.RemoveCallback(this::MoneyChanged);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        _gameManager.OnMoneyValueChange.RemoveCallback(this::MoneyChanged);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _gameManager.OnMoneyValueChange.RemoveCallback(this::MoneyChanged);
    }
}