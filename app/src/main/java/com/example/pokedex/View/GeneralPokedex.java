package com.example.pokedex.View;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.gridlayout.widget.GridLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.pokedex.R;
import com.example.pokedex.Controller.API;
import com.example.pokedex.Model.Pokemon;

import java.util.ArrayList;

public class GeneralPokedex extends AppCompatActivity {

    private int currentBatch = 0;
    private final int batchSize = 50;
    private boolean isLoading = false;

    private GridLayout myGrid;
    private ScrollView scrollView;
    private SearchView searchBar;

    // lista completa de Pokémon obtenida desde la API
    private ArrayList<Pokemon> pokedex;

    // lista filtrada según la búsqueda del usuario
    private ArrayList<Pokemon> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_general_pokedex);

        scrollView = findViewById(R.id.scrollView);
        myGrid = findViewById(R.id.myGrid);
        searchBar = findViewById(R.id.SearchBar);


        pokedex = API.getMyPokedex();

        filteredList = new ArrayList<>(pokedex);

        // carga el primer lote de Pokémon en pantalla
        loadBatch();

        // detecta cuando el usuario hace scroll hasta el final
        scrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            int scrollY = scrollView.getScrollY();
            int height = scrollView.getHeight();
            int contentHeight = scrollView.getChildAt(0).getHeight();

            // si no está cargando y el usuario llegó al final, carga má
            if (!isLoading && (scrollY + height >= contentHeight - 50)) {
                isLoading = true;
                Toast.makeText(this, "Cargando más Pokémon…", Toast.LENGTH_SHORT).show();
                loadBatch();
            }
        });

        // configura el buscador para filtrar por nombre o número
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterPokemon(newText);
                return true;
            }
        });
    }

    private void filterPokemon(String query) {
        filteredList.clear();
        currentBatch = 0;
        myGrid.removeAllViews();

        String lowerQuery = query.toLowerCase();

        for (int i = 0; i < pokedex.size(); i++) {
            Pokemon p = pokedex.get(i);
            String numberStr = String.valueOf(p.getNumber());
            if (p.getName().toLowerCase().contains(lowerQuery) || numberStr.contains(lowerQuery)) {
                filteredList.add(p);
            }
        }

        loadBatch();
    }

    private void loadBatch() {
        if (currentBatch * batchSize >= filteredList.size()) return;

        int start = currentBatch * batchSize;
        int end = Math.min(start + batchSize, filteredList.size());

        for (int i = start; i < end; i++) {
            Pokemon p = filteredList.get(i);

            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.VERTICAL);
            card.setGravity(Gravity.CENTER);
            card.setPadding(24, 24, 24, 24);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 330;
            params.setMargins(12, 12, 12, 12);
            card.setLayoutParams(params);

            GradientDrawable cardBackground = new GradientDrawable();
            cardBackground.setColor(Color.parseColor("#7FCFAC"));
            cardBackground.setCornerRadius(32);
            cardBackground.setStroke(3, Color.parseColor("#62B090"));
            card.setBackground(cardBackground);

            LinearLayout imageContainer = new LinearLayout(this);
            imageContainer.setPadding(12, 12, 12, 12);
            imageContainer.setGravity(Gravity.CENTER);

            GradientDrawable imageBackground = new GradientDrawable();
            imageBackground.setColor(Color.WHITE);
            imageBackground.setCornerRadius(24);
            imageContainer.setBackground(imageBackground);

            ImageButton imageButton = new ImageButton(this);
            Glide.with(this)
                    .load(p.getImage())
                    .sizeMultiplier(0.072f)
                    .into(imageButton);
            imageButton.setBackground(null);
            imageButton.setAdjustViewBounds(true);
            imageButton.setScaleType(ImageButton.ScaleType.FIT_CENTER);

            // aquí se implementa la navegación a InfoPokemon
            imageButton.setOnClickListener(view -> {
                Intent intent = new Intent(GeneralPokedex.this, InfoPokemon.class);
                intent.putExtra("nombre", p.getName());
                intent.putExtra("imagen", p.getImage());
                intent.putExtra("peso", String.valueOf(p.getPeso()));
                intent.putExtra("tamaño", String.valueOf(p.getTamaño()));
                intent.putExtra("historia", p.getHistoria());
                intent.putExtra("tipos", p.getTipos().toArray(new String[0]));

                startActivity(intent);
            });

            imageContainer.addView(imageButton);

            TextView numberText = new TextView(this);
            numberText.setText("No." + String.format("%03d", p.getNumber()));
            numberText.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
            numberText.setTextSize(14);
            numberText.setTypeface(Typeface.DEFAULT_BOLD);

            TextView nameText = new TextView(this);
            nameText.setText(p.getName());
            nameText.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
            nameText.setTextSize(14);

            card.addView(imageContainer);
            card.addView(numberText);
            card.addView(nameText);

            myGrid.addView(card);
        }

        currentBatch++;
        isLoading = false;
    }
}
