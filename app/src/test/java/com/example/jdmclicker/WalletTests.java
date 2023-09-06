package com.example.jdmclicker;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.app.GameManager;

import com.example.jdmclicker.Scripts.GameScripts.Wallet;

public class WalletTests {
    @Test
    public void Wallet_AddPositiveAmount_ReturnsTrue(){
        Wallet wallet = new Wallet();
        assertTrue(wallet.TryAddMoney(10));
    }

    @Test
    public void Wallet_AddNegativeAmount_ReturnsFalse(){
        Wallet wallet = new Wallet();
        assertFalse(wallet.TryAddMoney(-10));
    }

    @Test
    public void Wallet_SpendingLessThanYouHave_ReturnsTrue(){
        Wallet wallet = new Wallet();
        wallet.TryAddMoney(100);
        assertTrue(wallet.TrySpendMoney(99));
    }

    @Test
    public void Wallet_SpendingEqualThatYouHave_ReturnsTrue(){
        Wallet wallet = new Wallet();
        wallet.TryAddMoney(100);
        assertTrue(wallet.TrySpendMoney(100));
    }

    @Test
    public void Wallet_SpendingMoreThanYouHave_ReturnsFalse(){
        Wallet wallet = new Wallet();
        wallet.TryAddMoney(100);
        assertFalse(wallet.TrySpendMoney(101));
    }
}
