package com.example.jdmclicker.Scripts.GameScripts;

import static java.util.stream.Collectors.toList;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jdmclicker.R;
import com.example.jdmclicker.Scripts.Adapters.CarsAdapter;
import com.example.jdmclicker.Scripts.Adapters.IncomesAdapter;
import com.example.jdmclicker.Scripts.TemporaryData.CarData;
import com.example.jdmclicker.Scripts.TemporaryData.IncomeData;
import com.example.jdmclicker.Scripts.TemporaryData.TrackData;
import com.example.jdmclicker.Scripts.Adapters.TracksAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Shop {
    private List<Car> _cars;
    private List<Track> _tracks;
    private List<PassiveIncome> _passiveIncomes;

    private Car _currentCar;
    private Track _currentTrack;

    private float _carsCostMultiplicator = 1.15f;
    private float _tracksCostMultiplicator = 1.15f;
    private float _passiveIncomesCostMultiplicator = 1.15f;

    private Context _context;
    private GameManager _gameManager;

    private int[] _carsImages ={
            R.drawable.trueno,
            R.drawable.civic,
            R.drawable.rx7,
            R.drawable.subaru,
            R.drawable.skyline
    };

    private int[] _tracksImages = {
            R.drawable.fieldroad,
            R.drawable.forestroad,
            R.drawable.winterroad,
            R.drawable.cityroad,
            R.drawable.beachroad
    };

    private int[] _incomesImages = {
            R.drawable.grandma,
            R.drawable.youtube,
            R.drawable.editor,
            R.drawable.ads,
            R.drawable.racingsponsor,
    };

    public int getCarImage(int id){
        if(id < _carsImages.length)
            return _carsImages[id];

        return 0;
    }

    public int getTrackImage(int id){
        if(id < _tracksImages.length)
            return _tracksImages[id];

        return 0;
    }

    public int getIncomeImage(int id){
        if(id < _incomesImages.length)
            return _incomesImages[id];

        return 0;
    }

    public Shop(Context context, GameManager gameManager, String carsJSON, String tracksJSON, String incomesJSON){
        _context = context;

        _cars = InitializeCars(carsJSON);
        _tracks = InitializeTracks(tracksJSON);
        _passiveIncomes = InitializePassiveIncomes(incomesJSON);

        _currentCar = _cars.get(0);
        _currentTrack = _tracks.get(0);

        _currentCar.Buy();
        _currentTrack.Buy();

        _gameManager = gameManager;
    }

    public boolean BuyUpgradeSelectCar(Car car){
        if(!car.isBought() && _gameManager.getWallet().TrySpendMoney(car.getCurrentCost())){
            car.Buy();
            return true;
        }

        if(car.isBought() && _currentCar != car){
            _currentCar = car;
            return true;
        }

        if(_gameManager.getWallet().TrySpendMoney(car.getCurrentCost())){
            car.Upgrade(_carsCostMultiplicator);
            _currentCar = car;
            return true;
        }

        return false;
    }

    public boolean BuyUpgradeSelectTrack(Track track){
        if(!track.isBought() && _gameManager.getWallet().TrySpendMoney(track.getCurrentCost())){
            track.Buy();
            return true;
        }

        if(track.isBought() && _currentTrack != track){
            _currentTrack = track;
            return true;
        }

        if(_gameManager.getWallet().TrySpendMoney(track.getCurrentCost())){
            track.Upgrade(_tracksCostMultiplicator);
            _currentTrack = track;
            return true;
        }

        return false;
    }

    public boolean BuyUpgradeIncome(PassiveIncome income){

        if(!income.isBought() && _gameManager.getWallet().TrySpendMoney(income.getCurrentCost())){
            income.Buy();
            return true;
        }

        if(_gameManager.getWallet().TrySpendMoney(income.getCurrentCost())){
            income.Upgrade(_passiveIncomesCostMultiplicator);
            return true;
        }

        return false;
    }

    private List<Car> InitializeCars(String jsonFileString){
        Gson gson = new Gson();
        Type listCarType = new TypeToken<List<CarData>>(){}.getType();

        List<CarData> carsData = gson.fromJson(jsonFileString, listCarType);
        List<Car> cars = new ArrayList<Car>();
        for (int i = 0; i < carsData.size(); i++) {
            Car car =
                    new Car(
                            carsData.get(i).ID,
                            carsData.get(i).Name,
                            carsData.get(i).StartingCost,
                            carsData.get(i).StartingSpeed,
                            carsData.get(i).SpeedPerUpgrade
                    );
            cars.add(car);
            Log.i("mydata", cars.get(i).getName());
        }
        return cars;
    }

    private List<Track> InitializeTracks(String jsonFileString){
        Gson gson = new Gson();
        Type listTrackType = new TypeToken<List<TrackData>>(){}.getType();

        List<TrackData> tracksData = gson.fromJson(jsonFileString, listTrackType);
        List<Track> tracks = new ArrayList<Track>();
        for (int i = 0; i < tracksData.size(); i++) {
            Track track =
                    new Track(
                            tracksData.get(i).ID,
                            tracksData.get(i).Name,
                            tracksData.get(i).Length,
                            tracksData.get(i).StartingCost,
                            tracksData.get(i).StartingCompletionReward,
                            tracksData.get(i).CompletionRewardPerUpgrade
                            );
            tracks.add(track);
            Log.i("mydata", tracks.get(i).getName());
        }
        return tracks;
    }

    private List<PassiveIncome> InitializePassiveIncomes(String jsonFileString){
        Gson gson = new Gson();
        Type listIncomeType = new TypeToken<List<IncomeData>>(){}.getType();

        List<IncomeData> incomesData = gson.fromJson(jsonFileString, listIncomeType);
        List<PassiveIncome> incomes = new ArrayList<PassiveIncome>();
        for (int i = 0; i < incomesData.size(); i++) {
            PassiveIncome income =
                    new PassiveIncome(
                            incomesData.get(i).ID,
                            incomesData.get(i).Name,
                            incomesData.get(i).StartingCost,
                            incomesData.get(i).StartingMoneyPerSecond,
                            incomesData.get(i).MoneyPerSecondPerUpgrade
                    );
            incomes.add(income);
            Log.i("mydata", incomes.get(i).getName());
        }
        return incomes;
    }

    public float getPassiveIncomesMoneyCount(){
        List<PassiveIncome> boughtIncomes = _passiveIncomes.parallelStream().filter(income -> income.isBought()).collect(toList());

        if(boughtIncomes.size() == 0)
            return 0;

        float moneyValue = 0;
        for(int i =0; i < boughtIncomes.size();i++){
            moneyValue += boughtIncomes.get(i).getCurrentMoneyPerSecond();
        }
        return moneyValue;
    }

    public Car getCurrentCar(){
        return _currentCar;
    }



    public Track getCurrentTrack(){
        return _currentTrack;
    }

    public void GenerateCarsRecyclerView(RecyclerView recyclerView){
        CarsAdapter adapter = new CarsAdapter(_cars, _carsImages);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(_context));
    }

    public void GenerateTracksRecyclerView(RecyclerView recyclerView){
        TracksAdapter adapter = new TracksAdapter(_tracks, _tracksImages);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(_context));
    }

    public void GenerateIncomesRecyclerView(RecyclerView recyclerView){
        IncomesAdapter adapter = new IncomesAdapter(_passiveIncomes, _incomesImages);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(_context));
    }
}
