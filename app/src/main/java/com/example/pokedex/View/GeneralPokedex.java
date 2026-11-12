package com.example.pokedex.View;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.Controller.API;
import com.example.pokedex.Model.Pokemon;
import com.example.pokedex.R;

import java.util.ArrayList;

/**
 * Pantalla con la cuadrícula de Pokémon y el buscador.
 *
 * <p>Muestra los Pokémon en un {@code RecyclerView} de 3 columnas, cargándolos
 * de 50 en 50 al hacer scroll, y filtra por nombre o número mientras se escribe.</p>
 */
public class GeneralPokedex extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchView searchBar;
    private PokemonAdapter adapter;

    private ArrayList<Pokemon> pokedex;
    private ArrayList<Pokemon> filteredList;

    private int currentBatch = 0;
    private final int batchSize = 50;
    private boolean isLoading = false;

    /**
     * Prepara la cuadrícula, carga el primer bloque y conecta el scroll y el buscador.
     *
     * @param savedInstanceState estado guardado de la Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_general_pokedex);

        recyclerView = findViewById(R.id.pokedexRecycler);
        searchBar = findViewById(R.id.SearchBar);

        pokedex = API.getMyPokedex();
        if (pokedex == null) pokedex = new ArrayList<>();

        filteredList = new ArrayList<>();

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new PokemonAdapter(filteredList, this);
        recyclerView.setAdapter(adapter);

        loadBatch();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView rv, int dx, int dy) {
                super.onScrolled(rv, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) rv.getLayoutManager();
                if (!isLoading && layoutManager != null &&
                        layoutManager.findLastVisibleItemPosition() >= filteredList.size() - 6) {
                    isLoading = true;
                    loadBatch();
                }
            }
        });

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterPokemon(newText);
                return true;
            }
        });
    }

    /**
     * Al volver a la pantalla, recarga la lista o vuelve a aplicar la búsqueda escrita.
     */
    @Override
    protected void onResume() {
        super.onResume();

        pokedex = API.getMyPokedex();
        if (pokedex == null) pokedex = new ArrayList<>();

        if (searchBar.getQuery().toString().isEmpty()) {
            filteredList.clear();
            adapter.notifyDataSetChanged(); // avisa al adaptador de que la lista se ha vaciado antes de volver a llenarla
            currentBatch = 0;
            loadBatch();
        } else {
            filterPokemon(searchBar.getQuery().toString());
        }
    }

    /**
     * Deja en la lista solo los Pokémon cuyo nombre o número contiene el texto.
     *
     * @param query texto escrito en el buscador
     */
    private void filterPokemon(String query) {
        filteredList.clear();
        currentBatch = 0;

        String lowerQuery = query.toLowerCase();
        for (Pokemon p : pokedex) {
            String numberStr = String.valueOf(p.getNumber());
            if (p.getName().toLowerCase().contains(lowerQuery) || numberStr.contains(lowerQuery)) {
                filteredList.add(p);
            }
        }

        adapter.notifyDataSetChanged();
    }

    /**
     * Añade a la cuadrícula el siguiente bloque de 50 Pokémon.
     */
    private void loadBatch() {
        if (pokedex == null || pokedex.isEmpty()) return;

        int start = currentBatch * batchSize;
        if (start >= pokedex.size()) return;

        int end = Math.min(start + batchSize, pokedex.size());

        for (int i = start; i < end; i++) {
            filteredList.add(pokedex.get(i));
        }

        adapter.notifyItemRangeInserted(start, end - start);
        currentBatch++;
        isLoading = false;
    }
}
