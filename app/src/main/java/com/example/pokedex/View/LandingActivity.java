package com.example.pokedex.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokedex.Controller.API;
import com.example.pokedex.Model.Pokemon;
import com.example.pokedex.R;

import java.util.Collections;
import java.util.Comparator;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_landing);

        Button btnAcceder = findViewById(R.id.btnAcceder);

        btnAcceder.setOnClickListener(v -> {
            btnAcceder.setEnabled(false); // Evita múltiples clics
            btnAcceder.setText("Cargando Pokémones...");

            API.obtainAllPokemon(this, () -> {
                runOnUiThread(() -> {
                    Collections.sort(API.getMyPokedex(), Comparator.comparingInt(Pokemon::getNumber));
                    startActivity(new Intent(LandingActivity.this, GeneralPokedex.class));
                    finish();
                });
            });
        });


    }
}
