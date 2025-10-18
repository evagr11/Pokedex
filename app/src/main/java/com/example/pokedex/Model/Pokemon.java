package com.example.pokedex.Model;

public class Pokemon {
    String name;
    int number;
    String image;

    public Pokemon(String name, String image, int number) {
        this.name = name;
        this.image = image;
        this.number = number;
    }

    public String getName() { return name; }
    public int getNumber() { return number; }
    public String getImage() { return image; }

    public void setName(String name) { this.name = name; }
    public void setNumber(int number) { this.number = number; }
    public void setImage(String image) { this.image = image; }
}
