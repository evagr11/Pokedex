package com.example.pokedex.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokedex.Controller.API;
import com.example.pokedex.Model.Pokemon;
import com.example.pokedex.R;

import java.util.Collections;
import java.util.Comparator;

public class LandingActivity extends AppCompatActivity {

    private boolean datosCargados = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_landing);

        Button btnAcceder = findViewById(R.id.btnAcceder);

        API.obtainAllPokemon(this, () -> runOnUiThread(() -> {
            datosCargados = true;
            // ordeno la lista de Pokémon por número (de menor a mayor)
            Collections.sort(API.getMyPokedex(), Comparator.comparingInt(Pokemon::getNumber));
        }));

        btnAcceder.setOnClickListener(v -> {
            btnAcceder.setEnabled(false);
            btnAcceder.setText("Accediendo...");
            // Llamo al metodo que espera a que los datos estén listos antes de avanzar
            esperarCargaYEntrar();
        });
    }

    private void esperarCargaYEntrar() {
        if (datosCargados) {
            startActivity(new Intent(this, GeneralPokedex.class));
            finish(); //cierra la pantalla para que no se pueda volver atras
        } else {
            // crea un Handler para esperar
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (datosCargados) {
                        startActivity(new Intent(LandingActivity.this, GeneralPokedex.class));
                        finish();
                    } else {
                        handler.postDelayed(this, 500);
                    }
                }
            }, 500);
        }
    }
}
