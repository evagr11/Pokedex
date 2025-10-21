package com.example.pokedex.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.pokedex.Model.TipoPokemonEnum;
import com.example.pokedex.R;

public class InfoPokemon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pokemon);

        // Recupera los datos enviados desde GeneralPokedex a través del Intent
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String peso = intent.getStringExtra("peso");
        String tamaño = intent.getStringExtra("tamaño");
        String historia = intent.getStringExtra("historia");
        String imagenUrl = intent.getStringExtra("imagen");
        String[] tipos = intent.getStringArrayExtra("tipos");

        // Conecta las vistas del XML con el código
        TextView nombreView = findViewById(R.id.Nombre);
        TextView pesoView = findViewById(R.id.peso);
        TextView tamañoView = findViewById(R.id.tamaño);
        TextView historiaView = findViewById(R.id.historia);
        ImageView imagenView = findViewById(R.id.Pokemon);
        ImageView tipo1 = findViewById(R.id.tipo1);
        ImageView tipo2 = findViewById(R.id.tipo2);
        ConstraintLayout fondo = findViewById(R.id.infoPokemon);

        // Asignar valores a las vistas
        nombreView.setText(nombre);
        pesoView.setText("Peso: " + peso + " kg");
        tamañoView.setText("Tamaño: " + tamaño + " m");
        historiaView.setText(historia);
        Glide.with(this).load(imagenUrl).into(imagenView);

        // Aplica los tipos visualmente si existen
        if (tipos != null && tipos.length > 0) {
            TipoPokemonEnum tipoPrincipal = TipoPokemonEnum.fromName(tipos[0]);
            if (tipoPrincipal != null) {
                tipo1.setImageResource(tipoPrincipal.getImagenResId());
                fondo.setBackgroundColor(getColor(tipoPrincipal.getColorResId()));
            }

            if (tipos.length > 1) {
                TipoPokemonEnum tipoSecundario = TipoPokemonEnum.fromName(tipos[1]);
                if (tipoSecundario != null) {
                    tipo2.setImageResource(tipoSecundario.getImagenResId());
                } else {
                    tipo2.setVisibility(View.GONE);
                }
            } else {
                tipo2.setVisibility(View.GONE);
            }
        }

        // Botón para volver a la vista general
        ImageButton backButton = findViewById(R.id.home);
        backButton.setOnClickListener(v -> {
            Intent volverIntent = new Intent(InfoPokemon.this, GeneralPokedex.class);
            startActivity(volverIntent);
            finish();
        });
    }
}