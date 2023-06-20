package com.example.jdmclicker.Scripts.GameScripts;

import android.content.Context;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.jdmclicker.Scripts.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

public class GameManager {
    private float _moneyCount;

    private Shop _shop;

    private int _trackProgress = 0;

    public static GameManager Instance;
     

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
        int progress = Math.round((_trackProgress * 100) / _shop.getCurrentTrack().getTrackLength());

        if(progress >= 100){
            _moneyCount += _shop.getCurrentTrack().getCurrentCompletionReward();
            _trackProgress = 0;
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
    }


    public Shop getShop(){
        return _shop;
    }

}
