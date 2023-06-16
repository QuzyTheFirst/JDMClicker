package com.example.jdmclicker.Scripts.GameScripts;

public class PassiveIncome {
    private int _id;
    private String _name;
    private float _currentCost;

    private float _currentMoneyPerSecond;
    private float _moneyPerSecondPerUpgrade;

    private int _currentLevel = 0;
    private boolean _isBought = false;

    public PassiveIncome(int ID, String name, float startingCost, float startingMoneyPerSecond, float moneyPerSecondPerUpgrade){
        _id = ID;
        _name = name;
        _currentCost = startingCost;
        _currentMoneyPerSecond = startingMoneyPerSecond;
        _moneyPerSecondPerUpgrade = moneyPerSecondPerUpgrade;
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

    public float getCurrentMoneyPerSecond(){
        return _currentMoneyPerSecond;
    }

    public boolean isBought(){
        return _isBought;
    }
}
