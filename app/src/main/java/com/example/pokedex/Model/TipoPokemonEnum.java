package com.example.pokedex.Model;

import com.example.pokedex.R;

public enum TipoPokemonEnum {
    NORMAL("normal", R.mipmap.normal, R.color.normalColor),
    FIRE("fire", R.mipmap.fuego, R.color.fuegoColor),
    WATER("water", R.mipmap.agua, R.color.aguaColor),
    ELECTRIC("electric", R.mipmap.electrico, R.color.electricoColor),
    GRASS("grass", R.mipmap.planta, R.color.plantaColor),
    ICE("ice", R.mipmap.hielo, R.color.hieloColor),
    FIGHTING("fighting", R.mipmap.lucha, R.color.luchaColor),
    POISON("poison", R.mipmap.veneno, R.color.venenoColor),
    GROUND("ground", R.mipmap.tierra, R.color.tierraColor),
    FLYING("flying", R.mipmap.volador, R.color.voladorColor),
    PSYCHIC("psychic", R.mipmap.psiquico, R.color.psiquicoColor),
    BUG("bug", R.mipmap.bicho, R.color.bichoColor),
    ROCK("rock", R.mipmap.roca, R.color.rocaColor),
    GHOST("ghost", R.mipmap.fantasma, R.color.fantasmaColor),
    DRAGON("dragon", R.mipmap.dragon, R.color.dragonColor),
    DARK("dark", R.mipmap.siniestro, R.color.siniestroColor),
    STEEL("steel", R.mipmap.acero, R.color.aceroColor),
    FAIRY("fairy", R.mipmap.hada, R.color.hadaColor);

    private final String nombre;
    private final int imagenResId;
    private final int colorResId;

    TipoPokemonEnum(String nombre, int imagenResId, int colorResId) {
        this.nombre = nombre;
        this.imagenResId = imagenResId;
        this.colorResId = colorResId;
    }

    public String getNombre() { return nombre; }
    public int getImagenResId() { return imagenResId; }
    public int getColorResId() { return colorResId; }

    public static TipoPokemonEnum fromName(String nombre) {
        for (TipoPokemonEnum tipo : values()) {
            if (tipo.nombre.equalsIgnoreCase(nombre)) {
                return tipo;
            }
        }
        return null;
    }
}