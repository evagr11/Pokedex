package com.example.pokedex.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnAcceder = findViewById(R.id.btnAcceder);

        btnAcceder.setOnClickListener(v -> {
            ViewGroup main = (ViewGroup) btnAcceder.getParent();
            main.removeView(btnAcceder);

            // Llamamos a obtainAllPokemon con un callback
            API.obtainAllPokemon(this, () -> {
                // Una vez obtenidos los URLs, llamamos a gatherAllPokemonInfo
                API.gatherAllPokemonInfo(this);

                // Esperamos un poco para que se llenen los datos (puedes ajustar el tiempo)
                new Handler().postDelayed(() -> {
                    // Ordenamos por número
                    Collections.sort(API.getMyPokedex(), Comparator.comparingInt(Pokemon::getNumber));

                    // Lanzamos la siguiente actividad
                    startActivity(new Intent(LandingActivity.this, GeneralPokedex.class));
                    finish();
                }, 6000); // Ajusta este tiempo si ves que no es suficiente
            });
        });
    }
}
