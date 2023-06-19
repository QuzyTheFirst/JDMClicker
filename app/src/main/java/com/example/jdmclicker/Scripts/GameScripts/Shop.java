package com.example.jdmclicker.Scripts.GameScripts;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jdmclicker.Scripts.CarsAdapter;
import com.example.jdmclicker.Scripts.TemporaryData.CarData;
import com.example.jdmclicker.Scripts.TemporaryData.IncomeData;
import com.example.jdmclicker.Scripts.TemporaryData.TrackData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
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

    public Shop(Context context, String carsJSON, String tracksJSON, String incomesJSON){
        _context = context;

        _cars = InitializeCars(carsJSON);
        _tracks = InitializeTracks(tracksJSON);
        _passiveIncomes = InitializePassiveIncomes(incomesJSON);

        _currentCar = _cars.get(0);
        _currentTrack = _tracks.get(0);

        _currentCar.Upgrade(_carsCostMultiplicator);
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

    public Car getCurrentCar(){
        return _currentCar;
    }
    public Track getCurrentTrack(){
        return _currentTrack;
    }

    public void GenerateCarsRecyclerView(RecyclerView recyclerView){
        CarsAdapter adapter = new CarsAdapter(_cars);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(_context));
    }
}