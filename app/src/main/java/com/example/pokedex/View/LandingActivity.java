package com.example.pokedex.View;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokedex.Controller.API;
import com.example.pokedex.Model.Pokemon;
import com.example.pokedex.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_landing);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.generalPokedex), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        API.obtainAllPokemon(this);

        Button btnAcceder;

        btnAcceder = (Button)findViewById(R.id.btnAcceder);

        btnAcceder.setOnClickListener(v -> {
            ViewGroup main = (ViewGroup) btnAcceder.getParent();
            main.removeView(btnAcceder);

            new Handler().postDelayed(new Runnable(){
                @Override
                public void run(){
                    startActivity(new Intent(LandingActivity.this, GeneralPokedex.class));
                    Collections.sort(API.getMyPokedex(), new Comparator<Pokemon>() {
                        @Override
                        public int compare(Pokemon pokemon, pokemon t1){
                            return Integer.compare(pokemon.getNumber(), t1.getNumber());
                        }
                    });
                    finish();
                }
            }, 6000);
        });
    }




}