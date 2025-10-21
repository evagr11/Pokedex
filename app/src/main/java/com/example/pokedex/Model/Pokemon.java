package com.example.pokedex.Model;

import java.util.ArrayList;

public class Pokemon {
    private String name;
    private int number;
    private String image;
    private double peso;
    private double tamaño;
    private String historia;
    private ArrayList<String> tipos;

    public Pokemon(String name, String image, int number, double peso, double tamaño, String historia) {
        this.name = name;
        this.image = image;
        this.number = number;
        this.peso = peso;
        this.tamaño = tamaño;
        this.historia = historia;
        this.tipos = new ArrayList<>(); // Inicializa lista vacía por defecto
    }

    public String getName() { return name; }
    public int getNumber() { return number; }
    public String getImage() { return image; }
    public double getPeso() { return peso; }
    public double getTamaño() { return tamaño; }
    public String getHistoria() { return historia; }
    public ArrayList<String> getTipos() { return tipos; }

    public void setName(String name) { this.name = name; }
    public void setNumber(int number) { this.number = number; }
    public void setImage(String image) { this.image = image; }
    public void setPeso(double peso) { this.peso = peso; }
    public void setTamaño(double tamaño) { this.tamaño = tamaño; }
    public void setHistoria(String historia) { this.historia = historia; }
    public void setTipos(ArrayList<String> tipos) { this.tipos = tipos; }
}