package com.example.gamesjhon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton triki, concentrese, pelota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        concentrese = findViewById(R.id.memory);

        triki = findViewById(R.id.triki);

        pelota = findViewById(R.id.pelota_rebote);

        concentrese.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println("Pasando a xml de concentrese");
                iniciarConcentrese();
            }
        });

        triki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Pansando al xml de triki");
                iniciarTriki();
            }
        });

        pelota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Pasando al xml de pelota");
                iniciarPelota();
            }
        });
    }

    private void iniciarConcentrese(){
        Intent i = new Intent(this, Concentrese.class);
        startActivity(i);
    }

    private void iniciarTriki(){
        Intent i = new Intent(this, Triki.class);
        startActivity(i);
    }

    private void iniciarPelota(){
        Intent i = new Intent(this, Pelota.class);
        startActivity(i);
    }
}