package com.example.jdmclicker;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.app.GameManager;

import com.example.jdmclicker.Scripts.GameScripts.Wallet;

class UnitTests {

    @Test
    public void Wallet_CorrectAddMoney_ReturnsTrue(){
        Wallet wallet = new Wallet();
        assertTrue(wallet.TryAddMoney(10));
    }

    @Test
    public void Wallet_IncorrectAddMoney_ReturnsFalse(){
        Wallet wallet = new Wallet();
        assertTrue(wallet.TryAddMoney(-10));
    }

}
