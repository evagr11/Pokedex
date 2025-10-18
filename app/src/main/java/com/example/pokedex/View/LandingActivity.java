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

    private boolean datosCargados = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_landing);

        Button btnAcceder = findViewById(R.id.btnAcceder);

        // 🔄 Inicia la carga silenciosa al abrir la app
        API.obtainAllPokemon(this, () -> {
            runOnUiThread(() -> {
                datosCargados = true;
                Collections.sort(API.getMyPokedex(), Comparator.comparingInt(Pokemon::getNumber));
            });
        });

        btnAcceder.setOnClickListener(v -> {
            btnAcceder.setEnabled(false);
            btnAcceder.setText("Cargando Pokémones...");

            // ✅ Si ya se cargaron, navega directamente
            if (datosCargados) {
                startActivity(new Intent(LandingActivity.this, GeneralPokedex.class));
                finish();
            } else {
                // 🔄 Si aún no han terminado, espera a que terminen
                Handler handler = new Handler();
                Runnable checkReady = new Runnable() {
                    @Override
                    public void run() {
                        if (datosCargados) {
                            startActivity(new Intent(LandingActivity.this, GeneralPokedex.class));
                            finish();
                        } else {
                            handler.postDelayed(this, 500); // vuelve a revisar en 0.5s
                        }
                    }
                };
                handler.post(checkReady);
            }
        });
    }

}
