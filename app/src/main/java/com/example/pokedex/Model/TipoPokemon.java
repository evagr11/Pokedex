package com.example.pokedex.Model;

public class TipoPokemon {
    private String nombre;
    private int imagenResId;
    private int colorResId;

    public TipoPokemon(String nombre, int imagenResId, int colorResId) {
        this.nombre = nombre;
        this.imagenResId = imagenResId;
        this.colorResId = colorResId;
    }

    public String getNombre() { return nombre; }
    public int getImagenResId() { return imagenResId; }
    public int getColorResId() { return colorResId; }
}
