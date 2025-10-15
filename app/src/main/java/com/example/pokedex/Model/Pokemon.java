package com.example.pokedex.Model;

import java.util.List;

public class Pokemon {

    private int id;
    private String name;
    private int height;
    private int weight;
    private List<TypeSlot> types;

    public class TypeSlot {
        private Type type;
        public Type getType() { return type; }
    }

    public class Type {
        private String name;
        public String getName() { return name; }
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

}
