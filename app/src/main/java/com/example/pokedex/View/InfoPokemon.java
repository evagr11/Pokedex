package com.example.pokedex.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.pokedex.R;

public class InfoPokemon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pokemon);

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String peso = intent.getStringExtra("peso") != null ? intent.getStringExtra("peso") : "Desconocido";
        String tamaño = intent.getStringExtra("tamaño") != null ? intent.getStringExtra("tamaño") : "Desconocido";
        String historia = intent.getStringExtra("historia") != null ? intent.getStringExtra("historia") : "Sin historia";
        String imagenUrl = intent.getStringExtra("imagen");

        TextView nombreView = findViewById(R.id.Nombre);
        TextView pesoView = findViewById(R.id.peso);
        TextView tamañoView = findViewById(R.id.tamaño);
        TextView historiaView = findViewById(R.id.textView3);
        ImageView imagenView = findViewById(R.id.Pokemon);

        nombreView.setText(nombre);
        pesoView.setText("Peso: " + peso);
        tamañoView.setText("Tamaño: " + tamaño);
        historiaView.setText(historia);

        Glide.with(this).load(imagenUrl).into(imagenView);
    }
}
