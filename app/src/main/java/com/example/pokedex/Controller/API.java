package com.example.pokedex.Controller;

import android.content.Context;
import android.os.StrictMode;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokedex.Model.Pokemon;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class API {


    static ArrayList<Pokemon> myPokedex = new ArrayList<>();
    private static String[] fullPokeList = new String[1028];
    private static String[] fullURLList = new String[1028];

    public static void obtainAllPokemon(Context ct, Runnable onComplete){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        RequestQueue queue = Volley.newRequestQueue(ct);
        String url = "https://pokeapi.co/api/v2/pokemon?limit=1028&offset=0";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            for (int i = 0; i < jObj.getJSONArray("results").length(); i++) {
                                fullPokeList[i] = jObj.getJSONArray("results").getJSONObject(i).getString("name");
                                fullURLList[i] = jObj.getJSONArray("results").getJSONObject(i).getString("url");
                            }
                            gatherAllPokemonInfo(ct);
                            if (onComplete != null) {
                                onComplete.run();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }

    public static void gatherAllPokemonInfo(Context ct){
        myPokedex.clear();

        RequestQueue queue = Volley.newRequestQueue(ct);
        queue.stop();

        for (int i = 0; i < 100; i++) {
            queue.add(gatherIndividualPokemonInfo(fullURLList[i]));
        }

        queue.start();
    }


    static StringRequest gatherIndividualPokemonInfo(String URL) {
        return new StringRequest(Request.Method.GET, URL,
                response -> {
                    try {
                        JSONObject jObj = new JSONObject(response);
                        String name = jObj.getString("name");
                        int id = jObj.getInt("id");
                        String image = jObj.getJSONObject("sprites").getString("front_default");

                        // Verificamos si ya existe antes de agregar
                        boolean exists = false;
                        for (Pokemon p : myPokedex) {
                            if (p.getNumber() == id) {
                                exists = true;
                                break;
                            }
                        }

                        if (!exists) {
                            myPokedex.add(new Pokemon(name, image, id));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    // Puedes loguear el error aquí si quieres
                }
        );
    }


    public static ArrayList<Pokemon> getMyPokedex() {
        return myPokedex;
    }

}