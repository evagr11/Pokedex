package com.example.pokedex.Controller;

import android.content.Context;
import com.example.pokedex.Model.TipoPokemon;
import com.example.pokedex.R;
import java.util.HashMap;

public class TipoManager {
    private static HashMap<String, TipoPokemon> tipos = new HashMap<>();

    public static void init(Context context) {
        tipos.put("normal", new TipoPokemon("normal", R.mipmap.normal, context.getColor(R.color.normalColor)));
        tipos.put("fire", new TipoPokemon("fire", R.mipmap.fuego, context.getColor(R.color.fuegoColor)));
        tipos.put("water", new TipoPokemon("water", R.mipmap.agua, context.getColor(R.color.aguaColor)));
        tipos.put("electric", new TipoPokemon("electric", R.mipmap.electrico, context.getColor(R.color.electricoColor)));
        tipos.put("grass", new TipoPokemon("grass", R.mipmap.planta, context.getColor(R.color.plantaColor)));
        tipos.put("ice", new TipoPokemon("ice", R.mipmap.hielo, context.getColor(R.color.hieloColor)));
        tipos.put("fighting", new TipoPokemon("fighting", R.mipmap.lucha, context.getColor(R.color.luchaColor)));
        tipos.put("poison", new TipoPokemon("poison", R.mipmap.veneno, context.getColor(R.color.venenoColor)));
        tipos.put("ground", new TipoPokemon("ground", R.mipmap.tierra, context.getColor(R.color.tierraColor)));
        tipos.put("flying", new TipoPokemon("flying", R.mipmap.volador, context.getColor(R.color.voladorColor)));
        tipos.put("psychic", new TipoPokemon("psychic", R.mipmap.psiquico, context.getColor(R.color.psiquicoColor)));
        tipos.put("bug", new TipoPokemon("bug", R.mipmap.bicho, context.getColor(R.color.bichoColor)));
        tipos.put("rock", new TipoPokemon("rock", R.mipmap.roca, context.getColor(R.color.rocaColor)));
        tipos.put("ghost", new TipoPokemon("ghost", R.mipmap.fantasma, context.getColor(R.color.fantasmaColor)));
        tipos.put("dragon", new TipoPokemon("dragon", R.mipmap.dragon, context.getColor(R.color.dragonColor)));
        tipos.put("dark", new TipoPokemon("dark", R.mipmap.siniestro, context.getColor(R.color.siniestroColor)));
        tipos.put("steel", new TipoPokemon("steel", R.mipmap.acero, context.getColor(R.color.aceroColor)));
        tipos.put("fairy", new TipoPokemon("fairy", R.mipmap.hada, context.getColor(R.color.hadaColor)));
    }

    public static TipoPokemon getTipo(String nombre) {
        return tipos.get(nombre);
    }
}
