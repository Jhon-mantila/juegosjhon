package com.example.gamesjhon;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

public class Gestion extends Activity {

    private Partida_pelota partida;

    private  int dificultad;

    private int fps = 30;

    private int botes;

    private Handler temporizador;

    MediaPlayer golpeo;

    MediaPlayer fin;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        botes = 0;

        Bundle extras = getIntent().getExtras();

        dificultad = extras.getInt("DIFICULTAD");

        partida = new Partida_pelota(getApplicationContext(), dificultad);

        setContentView(partida);

        temporizador = new Handler();

        temporizador.postDelayed(elhilo, 2000);

        golpeo = MediaPlayer.create(this, R.raw.golpe);

        fin = MediaPlayer.create(this, R.raw.fin);
    }

    private Runnable elhilo = new Runnable() {
        @Override
        public void run() {

            if(partida.movimientoBola()) {
                fin();
            }else{

                partida.invalidate(); //Elimina el contenido de ImageView y llama de nuevo al metodo onDraw

                temporizador.postDelayed(elhilo, 1000/fps);

            }


        }
    };

    public boolean onTouchEvent(MotionEvent evento){

        int x = (int) evento.getX();

        int y = (int) evento.getY();

        //System.out.println("X: " + x +" Y: " + y);
        //inicio el sonido
        golpeo.start();

        //paso el punto concreto con el usuario ha tocado la pantalla y la cantidad de toques
        if(partida.toque(x, y)) botes++;

        return false;
    }

    public void fin(){

        temporizador.removeCallbacks(elhilo); // para matar el hilo

        fin.start();

        Intent in = new Intent();

        in.putExtra("PUNTUACION", botes*dificultad);

        setResult(RESULT_OK,in);

        finish();//destruye la actividad actual
    }

}
