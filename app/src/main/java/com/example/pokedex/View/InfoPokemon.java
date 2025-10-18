package com.example.pokedex.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.pokedex.Controller.TipoManager;
import com.example.pokedex.Model.TipoPokemon;
import com.example.pokedex.R;

public class InfoPokemon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pokemon);

        // Obtener datos del Intent
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String peso = intent.getStringExtra("peso") != null ? intent.getStringExtra("peso") : "Desconocido";
        String tamaño = intent.getStringExtra("tamaño") != null ? intent.getStringExtra("tamaño") : "Desconocido";
        String historia = intent.getStringExtra("historia") != null ? intent.getStringExtra("historia") : "Sin historia";
        String imagenUrl = intent.getStringExtra("imagen");

        // Referencias a vistas del layout
        TextView nombreView = findViewById(R.id.Nombre);
        TextView pesoView = findViewById(R.id.peso);
        TextView tamañoView = findViewById(R.id.tamaño);
        TextView historiaView = findViewById(R.id.historia);
        ImageView imagenView = findViewById(R.id.Pokemon);

        // Asignar valores a las vistas
        nombreView.setText(nombre);
        pesoView.setText("Peso: " + peso);
        tamañoView.setText("Tamaño: " + tamaño);
        historiaView.setText(historia);

        Glide.with(this).load(imagenUrl).into(imagenView);

        TipoManager.init(this); // Inicializa el mapa

        String[] tipos = intent.getStringArrayExtra("tipos");
        ImageView tipo1 = findViewById(R.id.tipo1);
        ImageView tipo2 = findViewById(R.id.tipo2);
        ConstraintLayout fondo = findViewById(R.id.infoPokemon);

        if (tipos != null && tipos.length > 0) {
            TipoPokemon tipoPrincipal = TipoManager.getTipo(tipos[0]);
            if (tipoPrincipal != null) {
                tipo1.setImageResource(tipoPrincipal.getImagenResId());
                fondo.setBackgroundColor(tipoPrincipal.getColorResId());
            }

            if (tipos.length > 1) {
                TipoPokemon tipoSecundario = TipoManager.getTipo(tipos[1]);
                if (tipoSecundario != null) {
                    tipo2.setImageResource(tipoSecundario.getImagenResId());
                }
            } else {
                tipo2.setVisibility(View.GONE);
            }
        }


    }
}
