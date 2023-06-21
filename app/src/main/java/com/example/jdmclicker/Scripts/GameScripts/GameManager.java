package com.example.jdmclicker.Scripts.GameScripts;

import android.content.Context;

import com.example.jdmclicker.Scripts.Events.EventHandler;
import com.example.jdmclicker.Scripts.Utils;


public class GameManager {
    private float _moneyCount;

    private Shop _shop;

    private int _trackProgress = 0;

    public static GameManager Instance;

    public EventHandler<Float> OnMoneyValueChange = new EventHandler<>();

    public GameManager(Context context){
        String carsJsonFileString = Utils.getJsonFromAssets(context, "cars.json");
        String tracksJsonFileString = Utils.getJsonFromAssets(context, "tracks.json");
        String incomesJsonFileString = Utils.getJsonFromAssets(context, "incomes.json");

        _shop = new Shop(context, this, carsJsonFileString, tracksJsonFileString, incomesJsonFileString);

        if(Instance == null)
            Instance = this;
    }

    public void OnDriveButtonClick(){
        _trackProgress += _shop.getCurrentCar().getCurrentSpeed();

        while(getTrackProgress() >= 100){
            _trackProgress -= _shop.getCurrentTrack().getTrackLength();
            ChangeMoneyValue(_shop.getCurrentTrack().getCurrentCompletionReward(), MoneyTransactionDirection.Up);
        }
    }

    public int getTrackProgress(){
        int progress = Math.round((_trackProgress * 100) / _shop.getCurrentTrack().getTrackLength());
        return progress;
    }
    public float getMoneyCount(){
        return _moneyCount;
    }

    public enum MoneyTransactionDirection{
        Up,
        Down
    }

    public void ChangeMoneyValue(float amount, MoneyTransactionDirection direction){
        if(direction == MoneyTransactionDirection.Up){
            _moneyCount += amount;
        }
        if(direction == MoneyTransactionDirection.Down){
            _moneyCount -= amount;
        }

        OnMoneyValueChange.invoke(this, _moneyCount);
    }


    public Shop getShop(){
        return _shop;
    }

}
