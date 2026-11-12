package com.example.pokedex.Model;

import java.util.ArrayList;

/**
 * Modelo de un Pokémon con los datos que la app descarga de la PokéAPI.
 *
 * <p>Guarda el nombre, el número de la Pokédex, la URL de la ilustración oficial,
 * el peso, la altura, la descripción en español y la lista de tipos.</p>
 */
public class Pokemon {
    private String name;
    private int number;
    private String image;
    private double peso;
    private double tamaño;
    private String historia;
    private ArrayList<String> tipos;

    /**
     * Crea un Pokémon sin tipos; se añaden después con {@link #setTipos(ArrayList)}.
     *
     * @param name    nombre del Pokémon
     * @param image   URL de la ilustración oficial
     * @param number  número de la Pokédex nacional
     * @param peso    peso en kilogramos
     * @param tamaño  altura en metros
     * @param historia descripción de la Pokédex en español
     */
    public Pokemon(String name, String image, int number, double peso, double tamaño, String historia) {
        this.name = name;
        this.image = image;
        this.number = number;
        this.peso = peso;
        this.tamaño = tamaño;
        this.historia = historia;
        this.tipos = new ArrayList<>(); // Inicializa lista vacía por defecto
    }

    /**
     * @return el nombre del Pokémon
     */
    public String getName() { return name; }
    /**
     * @return el número de la Pokédex
     */
    public int getNumber() { return number; }
    /**
     * @return la URL de la ilustración oficial
     */
    public String getImage() { return image; }
    /**
     * @return el peso en kilogramos
     */
    public double getPeso() { return peso; }
    /**
     * @return la altura en metros
     */
    public double getTamaño() { return tamaño; }
    /**
     * @return la descripción en español, o «Sin historia» si no la hay
     */
    public String getHistoria() { return historia; }
    /**
     * @return los tipos del Pokémon (en inglés, como los da la API)
     */
    public ArrayList<String> getTipos() { return tipos; }

    /**
     * @param name nuevo nombre
     */
    public void setName(String name) { this.name = name; }
    /**
     * @param number nuevo número de la Pokédex
     */
    public void setNumber(int number) { this.number = number; }
    /**
     * @param image nueva URL de la ilustración
     */
    public void setImage(String image) { this.image = image; }
    /**
     * @param peso nuevo peso en kilogramos
     */
    public void setPeso(double peso) { this.peso = peso; }
    /**
     * @param tamaño nueva altura en metros
     */
    public void setTamaño(double tamaño) { this.tamaño = tamaño; }
    /**
     * @param historia nueva descripción
     */
    public void setHistoria(String historia) { this.historia = historia; }
    /**
     * @param tipos lista de tipos del Pokémon
     */
    public void setTipos(ArrayList<String> tipos) { this.tipos = tipos; }
}