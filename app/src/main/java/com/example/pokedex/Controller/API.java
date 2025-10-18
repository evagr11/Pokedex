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

    static ArrayList<Pokemon> myPokedex = new ArrayList<>();
    private static String[] fullURLList = new String[1028];

    public static void obtainAllPokemon(Context ct, Runnable onComplete) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        RequestQueue queue = Volley.newRequestQueue(ct);
        String url = "https://pokeapi.co/api/v2/pokemon?limit=1028&offset=0";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject jObj = new JSONObject(response);
                        for (int i = 0; i < jObj.getJSONArray("results").length(); i++) {
                            fullURLList[i] = jObj.getJSONArray("results").getJSONObject(i).getString("url");
                        }
                        gatherAllPokemonInfo(ct, onComplete); // ✅ aquí pasamos el callback
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {}
        );

        queue.add(stringRequest);
    }




    public static void gatherAllPokemonInfo(Context ct, Runnable onComplete) {
        myPokedex.clear();
        RequestQueue queue = Volley.newRequestQueue(ct);
        queue.stop();

        final int total = (int) java.util.Arrays.stream(fullURLList)
                .filter(java.util.Objects::nonNull)
                .count();

        AtomicInteger completed = new AtomicInteger(0);

        for (String url : fullURLList) {
            if (url == null) continue;

            queue.add(new StringRequest(Request.Method.GET, url,
                    response -> {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            String name = jObj.getString("name");
                            int id = jObj.getInt("id");
                            String image = jObj.getJSONObject("sprites").getString("front_default");
                            double weight = jObj.getDouble("weight") / 10.0;
                            double height = jObj.getDouble("height") / 10.0;

                            // ✅ Extraer tipos
                            ArrayList<String> tipos = new ArrayList<>();
                            for (int i = 0; i < jObj.getJSONArray("types").length(); i++) {
                                JSONObject tipoObj = jObj.getJSONArray("types").getJSONObject(i).getJSONObject("type");
                                tipos.add(tipoObj.getString("name"));
                            }

                            // Segunda petición para obtener la historia
                            String speciesUrl = "https://pokeapi.co/api/v2/pokemon-species/" + id;
                            queue.add(new StringRequest(Request.Method.GET, speciesUrl,
                                    speciesResponse -> {
                                        try {
                                            JSONObject speciesObj = new JSONObject(speciesResponse);
                                            String historia = "Sin historia";

                                            for (int i = 0; i < speciesObj.getJSONArray("flavor_text_entries").length(); i++) {
                                                JSONObject entry = speciesObj.getJSONArray("flavor_text_entries").getJSONObject(i);
                                                if (entry.getJSONObject("language").getString("name").equals("es")) {
                                                    historia = entry.getString("flavor_text").replace("\n", " ").replace("\f", " ");
                                                    break;
                                                }
                                            }

                                            Pokemon pokemon = new Pokemon(name, image, id, weight, height, historia);
                                            pokemon.setTipos(tipos); // ✅ Asignar tipos al objeto

                                            myPokedex.add(pokemon);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        } finally {
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

        queue.start();
    }



    public static ArrayList<Pokemon> getMyPokedex() {
        return myPokedex;
    }
}
