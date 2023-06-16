package com.example.jdmclicker.Scripts.GameScripts;

public class Track {
    private int _id;
    private String _name;
    private float _length;
    private float _currentCost;

    private float _currentCompletionReward;
    private float _completionRewardPerUpgrade;

    private int _currentLevel = 0;
    private boolean _isBought = false;

    public Track(int ID, String name, int length, float startingCost, float startingCompletionReward, float completionRewardPerUpgrade){
        _id = ID;
        _name = name;
        _length = length;
        _currentCost = startingCost;
        _currentCompletionReward = startingCompletionReward;
        _completionRewardPerUpgrade = completionRewardPerUpgrade;
    }

    public void Upgrade(float costMultiplicator){
        if(!_isBought)
            _isBought = true;

        _currentLevel++;

        _currentCost *= costMultiplicator;
        _currentCompletionReward += _completionRewardPerUpgrade;
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

    public float getTrackLength(){
        return _length;
    }

    public float getCurrentCompletionReward(){
        return _currentCompletionReward;
    }

    public boolean isBought(){
        return _isBought;
    }
}
