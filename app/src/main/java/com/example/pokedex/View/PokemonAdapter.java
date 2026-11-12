package com.example.pokedex.View;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokedex.Model.Pokemon;
import com.example.pokedex.R;

import java.util.ArrayList;

/**
 * Adaptador del {@code RecyclerView} que pinta cada Pokémon con su número, nombre e imagen.
 */
public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private final ArrayList<Pokemon> pokemons;
    private final Context context;

    /**
     * @param pokemons lista que se muestra
     * @param context  contexto para inflar las vistas y abrir la ficha
     */
    public PokemonAdapter(ArrayList<Pokemon> pokemons, Context context) {
        this.pokemons = pokemons;
        this.context = context;
    }

    /**
     * Infla la tarjeta {@code item_pokemon}.
     *
     * @param parent   grupo de vistas padre
     * @param viewType tipo de vista (solo hay uno)
     * @return el ViewHolder creado
     */
    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false);
        return new PokemonViewHolder(view);
    }

    /**
     * Rellena la tarjeta y, al pulsarla, abre {@link InfoPokemon} con sus datos.
     *
     * @param holder   tarjeta que se rellena
     * @param position posición en la lista
     */
    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {
        Pokemon p = pokemons.get(position);

        holder.pokemonNumber.setText("No." + String.format("%03d", p.getNumber()));
        holder.pokemonName.setText(p.getName());

        Glide.with(context)
                .load(p.getImage())
                .thumbnail(0.1f)
                .dontAnimate()
                .into(holder.pokemonImage);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, InfoPokemon.class);
            intent.putExtra("nombre", p.getName());
            intent.putExtra("imagen", p.getImage());
            intent.putExtra("peso", String.valueOf(p.getPeso()));
            intent.putExtra("tamaño", String.valueOf(p.getTamaño()));
            intent.putExtra("historia", p.getHistoria());
            intent.putExtra("tipos", p.getTipos().toArray(new String[0]));
            context.startActivity(intent);
        });
    }

    /**
     * @return el número de Pokémon de la lista
     */
    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    /**
     * Referencias a las vistas de una tarjeta.
     */
    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        ImageView pokemonImage;
        TextView pokemonNumber;
        TextView pokemonName;

        /**
         * @param itemView vista de la tarjeta
         */
        public PokemonViewHolder(View itemView) {
            super(itemView);
            pokemonImage = itemView.findViewById(R.id.pokemonImage);
            pokemonNumber = itemView.findViewById(R.id.pokemonNumber);
            pokemonName = itemView.findViewById(R.id.pokemonName);
        }
    }
}
