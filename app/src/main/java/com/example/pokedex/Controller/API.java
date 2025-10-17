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

    private static String[] fullPokeList = new String[1024];
    private static String[] fullURlList = new String[1024];

    public static void obtainAllPokemon(Context ct){
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
                            for(int i = 0; i < jObj.getJSONArray("results").length(); i ++){
                                fullPokeList[i] = jObj.getJSONArray("results").getJSONObject(i).getString("name");
                                fullURlList[i] = jObj.getJSONArray("results").getJSONObject(i).getString("url");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(stringRequest);
    }

    public static void gatherAllPokemonInfo(Context ct){

    }
}
