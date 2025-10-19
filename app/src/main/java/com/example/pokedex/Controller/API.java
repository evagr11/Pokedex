package com.example.pokedex.Controller;

import android.content.Context;
import android.os.StrictMode;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokedex.Model.Pokemon;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class API {

    // lista global donde se guardan todos los Pokémon descargados
    static ArrayList<Pokemon> myPokedex = new ArrayList<>();
    // lista de URLs individuales para cada Pokémon (se llenan desde la API principal)
    private static String[] fullURLList = new String[1028];

    //metodo principal que inicia la descarga de todos los Pokémon
    public static void obtainAllPokemon(Context ct, Runnable onComplete) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // cola de peticiones HTTP usando Volley
        RequestQueue queue = Volley.newRequestQueue(ct);
        String url = "https://pokeapi.co/api/v2/pokemon?limit=1028&offset=0";

        // petición para obtener esa lista
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        // convierte la respuesta en JSON
                        JSONObject jObj = new JSONObject(response);
                        // recorre cada resultado y guarda su URL
                        for (int i = 0; i < jObj.getJSONArray("results").length(); i++) {
                            fullURLList[i] = jObj.getJSONArray("results").getJSONObject(i).getString("url");
                        }
                        // llama al siguiente paso: obtener la info detallada de cada Pokémon
                        gatherAllPokemonInfo(ct, onComplete);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {}
        );
        // añade la petición a la cola
        queue.add(stringRequest);
    }




    // metodo que recorre cada URL y obtiene la info completa de cada Pokémon
    public static void gatherAllPokemonInfo(Context ct, Runnable onComplete) {
        myPokedex.clear(); // limpia la lista antes de empezar
        RequestQueue queue = Volley.newRequestQueue(ct);
        queue.stop();// detiene la cola para evitar que se procesen antes de tiempo

        // cuenta cuántas URLs son válidas (no nulas)
        final int total = (int) java.util.Arrays.stream(fullURLList)
                .filter(java.util.Objects::nonNull)
                .count();

        // contador para saber cuántas respuestas se han completado
        AtomicInteger completed = new AtomicInteger(0);

        // recorre cada URL para obtener la info detallada
        for (int i = 0; i < fullURLList.length; i++) {
            String url = fullURLList[i];
            if (url == null) continue;

            // petición para obtener datos del Pokémon
            queue.add(new StringRequest(Request.Method.GET, url,
                    response -> {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            String name = jObj.getString("name");
                            int id = jObj.getInt("id");
                            String image = jObj.getJSONObject("sprites").getString("front_default");
                            double weight = jObj.getDouble("weight") / 10.0;
                            double height = jObj.getDouble("height") / 10.0;

                            // extraer tipos
                            ArrayList<String> tipos = new ArrayList<>();
                            for (int j = 0; j < jObj.getJSONArray("types").length(); j++) {
                                JSONObject tipoObj = jObj.getJSONArray("types").getJSONObject(j).getJSONObject("type");
                                tipos.add(tipoObj.getString("name"));
                            }

                            // Segunda petición para obtener la historia
                            String speciesUrl = "https://pokeapi.co/api/v2/pokemon-species/" + id;
                            queue.add(new StringRequest(Request.Method.GET, speciesUrl,
                                    speciesResponse -> {
                                        try {
                                            JSONObject speciesObj = new JSONObject(speciesResponse);
                                            String historia = "Sin historia";

                                            // busca la historia en español
                                            for (int k = 0; k < speciesObj.getJSONArray("flavor_text_entries").length(); k++) {
                                                JSONObject entry = speciesObj.getJSONArray("flavor_text_entries").getJSONObject(k);
                                                if (entry.getJSONObject("language").getString("name").equals("es")) {
                                                    historia = entry.getString("flavor_text").replace("\n", " ").replace("\f", " ");
                                                    break;
                                                }
                                            }

                                            // crea el objeto Pokémon con todos los datos
                                            Pokemon pokemon = new Pokemon(name, image, id, weight, height, historia);
                                            pokemon.setTipos(tipos); // asignar tipos al objeto

                                            myPokedex.add(pokemon); // lo añade a la lista

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        } finally {
                                            // si ya se completaron todos, ejecuta el callback
                                            if (completed.incrementAndGet() == total && onComplete != null) {
                                                onComplete.run();
                                            }
                                        }
                                    },
                                    error -> {
                                        // si falla, igual incrementa el contador
                                        if (completed.incrementAndGet() == total && onComplete != null) {
                                            onComplete.run();
                                        }
                                    }));

                        } catch (JSONException e) {
                            e.printStackTrace();
                            if (completed.incrementAndGet() == total && onComplete != null) {
                                onComplete.run();
                            }
                        }
                    },
                    error -> {
                        if (completed.incrementAndGet() == total && onComplete != null) {
                            onComplete.run();
                        }
                    }));
        }

        queue.start(); // inicia la cola para procesar todas las peticiones
    }


    // metodo para acceder a la lista de Pokémon desde otras clases
    public static ArrayList<Pokemon> getMyPokedex() {
        return myPokedex;
    }
}
