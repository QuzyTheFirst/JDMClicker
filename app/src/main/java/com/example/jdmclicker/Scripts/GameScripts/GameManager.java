package com.example.jdmclicker.Scripts.GameScripts;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.jdmclicker.Scripts.Events.EventHandler;
import com.example.jdmclicker.Scripts.Utils;


public class GameManager implements Runnable {
    private Shop _shop;
    private Wallet _wallet;

    private int _trackProgress = 0;

    public static GameManager Instance;

    public Activity _activity;
    public Context _context;

    public GameManager(Activity activity, Context context){
        String carsJsonFileString = Utils.getJsonFromAssets(context, "cars.json");
        String tracksJsonFileString = Utils.getJsonFromAssets(context, "tracks.json");
        String incomesJsonFileString = Utils.getJsonFromAssets(context, "incomes.json");

        _shop = new Shop(context, this, carsJsonFileString, tracksJsonFileString, incomesJsonFileString);
        _wallet = new Wallet();


        new Thread(this).start();

        _activity = activity;
        _context = context;

        if(Instance == null)
            Instance = this;
    }

    private void Update(float deltaTime){
        float moneyToAdd = _shop.getPassiveIncomesMoneyCount();

        Log.i("myData", Float.toString(moneyToAdd));

        if(moneyToAdd == 0)
            return;

        _activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                _wallet.TryAddMoney(moneyToAdd);
            }
        });
    }

    public void OnDriveButtonClick(){
        _trackProgress += _shop.getCurrentCar().getCurrentSpeed();

        while(getTrackProgress() >= 100){
            _trackProgress -= _shop.getCurrentTrack().getTrackLength();
            _wallet.TryAddMoney(_shop.getCurrentTrack().getCurrentCompletionReward());
        }
    }

    @Override
    public void run(){
        long lastTime = System.nanoTime();
        float amountOfTicks = 1;
        float timeBetweenFrames = 1 / amountOfTicks;
        float ns = 1000000000;
        float delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= timeBetweenFrames){
                Update(delta);
                delta -= timeBetweenFrames;
            }
        }

    }

    public int getTrackProgress(){
        int progress = Math.round((_trackProgress * 100) / _shop.getCurrentTrack().getTrackLength());
        return progress;
    }

    public enum MoneyTransactionDirection{
        Up,
        Down
    }

    public Shop getShop(){
        return _shop;
    }
    public Wallet getWallet(){return _wallet;}

}
