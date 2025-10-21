package com.example.pokedex.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokedex.Controller.API;
import com.example.pokedex.Model.Pokemon;
import com.example.pokedex.R;

import java.util.Collections;
import java.util.Comparator;

public class LandingActivity extends AppCompatActivity {

    private boolean datosCargados = false;
    private ProgressBar progressBar;
    private Button btnAcceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_landing);

        btnAcceder = findViewById(R.id.btnAcceder);
        progressBar = findViewById(R.id.progressBar);

        // Ocultar el botón y mostrar la barra de carga al iniciar
        btnAcceder.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        // Iniciar descarga de datos
        API.obtainAllPokemon(this, () -> runOnUiThread(() -> {
            datosCargados = true;
            Collections.sort(API.getMyPokedex(), Comparator.comparingInt(Pokemon::getNumber));

            // Ocultar la barra y mostrar el botón
            progressBar.setVisibility(View.GONE);
            btnAcceder.setVisibility(View.VISIBLE);
        }));

        btnAcceder.setOnClickListener(v -> {
            btnAcceder.setEnabled(false);
            btnAcceder.setText("Accediendo...");
            esperarCargaYEntrar();
        });
    }

    private void esperarCargaYEntrar() {
        if (datosCargados) {
            startActivity(new Intent(this, GeneralPokedex.class));
            finish(); // Cierra esta pantalla para evitar volver atrás
        } else {
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
