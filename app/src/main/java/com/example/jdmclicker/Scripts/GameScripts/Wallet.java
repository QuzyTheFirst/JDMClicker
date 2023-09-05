package com.example.jdmclicker.Scripts.GameScripts;

import com.example.jdmclicker.Scripts.Events.EventHandler;

public class Wallet {
    private float _moneyCount;

    public EventHandler<Float> OnMoneyValueChange = new EventHandler<>();

    public float getMoneyCount() {return _moneyCount;}

    public boolean TrySpendMoney(float value){
        if(value < 0)
            return false;

        if(value > _moneyCount)
            return false;

        _moneyCount -= value;
        return true;
    }

    public boolean TryAddMoney(float value){
        if(value < 0)
            return false;

        _moneyCount += value;
        return true;
    }
}
