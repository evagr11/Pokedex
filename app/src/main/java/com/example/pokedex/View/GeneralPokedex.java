package com.example.pokedex.View;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.gridlayout.widget.GridLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.pokedex.R;
import com.example.pokedex.Controller.API;
import com.example.pokedex.Model.Pokemon;

import java.util.ArrayList;

public class GeneralPokedex extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_general_pokedex);

        GridLayout myGrid = findViewById(R.id.myGrid);

        ArrayList<Pokemon> pokedex = API.getMyPokedex();
        int batchSize = 50;
        int total = pokedex.size();

        Handler handler = new Handler();

        for (int start = 0; start < total; start += batchSize) {
            int end = Math.min(start + batchSize, total);
            int finalStart = start;
            int finalEnd = end;

            handler.postDelayed(() -> {
                for (int i = finalStart; i < finalEnd; i++) {
                    Pokemon p = pokedex.get(i);

                    // Tarjeta contenedora
                    LinearLayout card = new LinearLayout(this);
                    card.setOrientation(LinearLayout.VERTICAL);
                    card.setGravity(Gravity.CENTER);
                    card.setPadding(24, 24, 24, 24);

                    GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                    params.width = 330; // pelín más pequeño
                    params.setMargins(12, 12, 12, 12);
                    card.setLayoutParams(params);

                    GradientDrawable cardBackground = new GradientDrawable();
                    cardBackground.setColor(Color.parseColor("#7FCFAC"));
                    cardBackground.setCornerRadius(32);
                    cardBackground.setStroke(3, Color.parseColor("#62B090"));
                    card.setBackground(cardBackground);

                    // Contenedor blanco para la imagen con bordes redondeados
                    LinearLayout imageContainer = new LinearLayout(this);
                    imageContainer.setPadding(12, 12, 12, 12);
                    imageContainer.setGravity(Gravity.CENTER);

                    GradientDrawable imageBackground = new GradientDrawable();
                    imageBackground.setColor(Color.WHITE);
                    imageBackground.setCornerRadius(24);
                    imageContainer.setBackground(imageBackground);

                    // Imagen del Pokémon
                    ImageButton imageButton = new ImageButton(this);
                    Glide.with(this)
                            .load(p.getImage())
                            .sizeMultiplier(0.072f) // pelín más pequeño
                            .into(imageButton);
                    imageButton.setBackground(null);
                    imageButton.setAdjustViewBounds(true);
                    imageButton.setScaleType(ImageButton.ScaleType.FIT_CENTER);

                    imageContainer.addView(imageButton);

                    // Número del Pokémon
                    TextView numberText = new TextView(this);
                    numberText.setText("No." + String.format("%03d", p.getNumber()));
                    numberText.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                    numberText.setTextSize(14);
                    numberText.setTypeface(Typeface.DEFAULT_BOLD);

                    // Nombre del Pokémon
                    TextView nameText = new TextView(this);
                    nameText.setText(p.getName());
                    nameText.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                    nameText.setTextSize(14);

                    // Añadir elementos a la tarjeta
                    card.addView(imageContainer);
                    card.addView(numberText);
                    card.addView(nameText);

                    // Añadir tarjeta al GridLayout
                    myGrid.addView(card);
                }
            }, (start / batchSize) * 500);
        }
    }
}
