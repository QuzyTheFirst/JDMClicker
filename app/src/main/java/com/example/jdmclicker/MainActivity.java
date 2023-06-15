package com.example.jdmclicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private final String BUTTONS_MESSAGE = "BUTTONS";

    //Navigation
    BottomNavigationView bottomNavigationView;

    //Fragments
    GameFragment gameFragment = new GameFragment();
    CarsFragment carsFragment = new CarsFragment();
    TracksFragment tracksFragment = new TracksFragment();
    IncomeFragment incomesFragment = new IncomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView
                = findViewById(R.id.bottomNavigationView);

        bottomNavigationView
                .setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.game);
    }

    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.game:
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.anim.fade_in,  // enter
                                R.anim.fade_out,  // exit
                                R.anim.slide_in,   // popEnter
                                R.anim.slide_out  // popExit
                        )
                        .replace(R.id.flFragment, gameFragment)
                        .commit();
                return true;

            case R.id.car:
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.anim.fade_in,  // enter
                                R.anim.fade_out,  // exit
                                R.anim.slide_in,   // popEnter
                                R.anim.slide_out  // popExit
                        )
                        .replace(R.id.flFragment, carsFragment)
                        .commit();
                return true;

            case R.id.track:
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.anim.fade_in,  // enter
                                R.anim.fade_out,  // exit
                                R.anim.slide_in,   // popEnter
                                R.anim.slide_out  // popExit
                        )
                        .replace(R.id.flFragment, tracksFragment)
                        .commit();
                return true;

            case R.id.income:
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.anim.fade_in,  // enter
                                R.anim.fade_out,  // exit
                                R.anim.slide_in,   // popEnter
                                R.anim.slide_out  // popExit
                        )
                        .replace(R.id.flFragment, incomesFragment)
                        .commit();
                return true;
        }
        return false;
    }
}