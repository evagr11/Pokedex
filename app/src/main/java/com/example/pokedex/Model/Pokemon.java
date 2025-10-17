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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

    /*
    {
      "id": 6,
      "name": "charizard",
      "height": 17,
      "weight": 905,
      "types": [
        {
          "slot": 1,
          "type": {
            "name": "fire",
            "url": "https://pokeapi.co/api/v2/type/10/"
          }
        },
        {
          "slot": 2,
          "type": {
            "name": "flying",
            "url": "https://pokeapi.co/api/v2/type/3/"
          }
        }
      ],
      "sprites": {
        "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png"
      }
    }
     */

