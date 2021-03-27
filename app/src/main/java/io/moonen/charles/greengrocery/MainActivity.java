package io.moonen.charles.greengrocery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.moonen.charles.greengrocery.ui.gardenessentials.PlantRow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<PlantRow> plants =  new ArrayList<>(Arrays.asList(
                new PlantRow("Evergreen Tree", 100, "Plant", R.id.evergreenView, R.drawable.evergreen1, R.drawable.evergreen2, R.drawable.evergreen3),
                new PlantRow("Maple Tree", 50,"Plant", R.id.mapleView, R.drawable.maple1, R.drawable.maple2, R.drawable.maple3),
                new PlantRow("Berry Bush", 20, "Plant", R.id.bushView, R.drawable.berrybush1, R.drawable.berrybush2, R.drawable.berrybush3),
                new PlantRow("Tulip", 10, "Plant", R.id.tulipView, R.drawable.tulip1, R.drawable.tulip2, R.drawable.tulip3)
        ));

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("points", 1000);

        Gson gson = new Gson();
        String jsonText = gson.toJson(plants);
        editor.putString("plants", jsonText);

        editor.apply();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}