package com.example.jdmclicker.Scripts.GameScripts;

public class Car {
    private int _id;
    private String _name;
    private float _currentCost;
    private float _currentSpeed;
    private float _speedPerUpgrade;

    private int _currentLevel = 0;
    private boolean _isBought = false;

    public Car(int ID, String name, float startingCost, float startingSpeed, float speedPerUpgrade){
        _id = ID;
        _name = name;
        _currentCost = startingCost;
        _currentSpeed = startingSpeed;
        _speedPerUpgrade = speedPerUpgrade;
    }

    public void Upgrade(float costMultiplicator){
        if(!_isBought)
            _isBought = true;

        _currentLevel++;

        _currentCost *= costMultiplicator;
        _currentSpeed += _speedPerUpgrade;
    }

    public int getID(){
        return _id;
    }

    public String getName(){
        return _name;
    }

    public float getCurrentCost(){
        return _currentCost;
    }

    public float getCurrentSpeed(){
        return _currentSpeed;
    }

    public boolean isBought(){
        return _isBought;
    }
}
