package com.example.pokedex.Model;

import java.util.List;
public class PokemonSpecies {
    //Contiene una lista de objetos FlavorTextEntry, que son las descripciones del Pokémon.
    private List<FlavorTextEntry> flavor_text_entries;
    //El metodo getFlavorTextEntries() te permite acceder a esa lista.
    public List<FlavorTextEntry> getFlavorTextEntries() { return flavor_text_entries; }

    public class FlavorTextEntry {
        private String flavor_text;
        private Language language;

        public String getFlavorText() { return flavor_text; }
        public Language getLanguage() { return language; }
    }

    public class Language {
        private String name;
        public String getName() { return name; }
    }
    /*
    {
      "flavor_text_entries": [
        {
          "flavor_text": "Cuando se enfada, lanza electricidad.",
          "language": {
            "name": "es",
            "url": "https://pokeapi.co/api/v2/language/7/"
          },
          "version": {
            "name": "red",
            "url": "https://pokeapi.co/api/v2/version/1/"
          }
        },
        {
          "flavor_text": "Spits fire that is hot enough to melt boulders.",
          "language": {
            "name": "en",
            "url": "https://pokeapi.co/api/v2/language/9/"
          },
          "version": {
            "name": "blue",
            "url": "https://pokeapi.co/api/v2/version/2/"
          }
        }
      ]
    }
    */
}
