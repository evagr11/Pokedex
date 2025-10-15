package com.example.pokedex;

import com.example.pokedex.Model.Pokemon;
import com.example.pokedex.Model.PokemonSpecies;

import retrofit2.Call; //representa una llamada que devolverá un objeto
import retrofit2.http.GET; //indica que harás una petición HTTP tipo GET
import retrofit2.http.Query;
import retrofit2.http.Path;//permite insertar valores dinámicos en la URL

public interface PokeApiConexion {

    @GET("pokemon")
    Call<PokemonResponse> getPokemons(@Query("limit") int limit, @Query("offset") int offset);
    @GET("pokemon/{name}")
    Call<Pokemon> getPokemon(@Path("name") String name);

    @GET("pokemon-species/{name}")
    Call<PokemonSpecies> getPokemonSpecies(@Path("name") String name);
}
