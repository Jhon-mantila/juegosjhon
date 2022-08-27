package com.example.gamesjhon;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Triki extends Activity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.triki);

        //Iniciamos el array casillas, que identifica las casillas del juego.

        casillas = new int[9];
        //inicializo el array con las posiciones de las imagenes
        casillas[0] = R.id.a1;
        casillas[1] = R.id.a2;
        casillas[2] = R.id.a3;
        casillas[3] = R.id.b1;
        casillas[4] = R.id.b2;
        casillas[5] = R.id.b3;
        casillas[6] = R.id.c1;
        casillas[7] = R.id.c2;
        casillas[8] = R.id.c3;
    }

    public void jugar(View vista){

        ImageView imagen;

        for(int cadaCasilla: casillas){
            //recorro cada posicion de las imagenees
            imagen = (ImageView) findViewById(cadaCasilla);
            //busco el recurso de la imagen con el siguiente metodo
            imagen.setImageResource(R.drawable.casilla);
        }

        jugadores = 1;

        //Solo capturo el segundo botón(vista)
        if(vista.getId()==R.id.dos_jug){

            jugadores = 2;
        }

        RadioGroup configDificultad = (RadioGroup) findViewById(R.id.configGrupo);

        int id_boton_radio = configDificultad.getCheckedRadioButtonId();

        int dificultad = 0;

        if(id_boton_radio == R.id.normal){


            dificultad = 1;
            System.out.println("Entro a normal: " + dificultad);

        }else if( id_boton_radio == R.id.imposible){

            dificultad = 2;
        }

        partida = new Partida(dificultad);

        findViewById(R.id.un_jug).setEnabled(false);

        findViewById(R.id.configGrupo).setAlpha(0);

        findViewById(R.id.dos_jug).setEnabled(false);


    }

    public void toque(View vista){

        if (partida == null){

            return;

        }
        int casilla = 0;

        System.out.println("Casilla tocada:"+ vista.getId());
        System.out.println("Array Casilla 0:" + casillas[0]);

        for(int i = 0; i < 9; i++){

            if(casillas[i] == vista.getId()){

                casilla = i;
                System.out.println("casilla valor i:"+ casilla);
                break;
            }
        }


        if(partida.comprueba_casilla_ocupada(casilla) == false){

            return;
        }

        marca(casilla);

        //Cambio los turnos
        int resultado = partida.turno();

        if(resultado > 0){

            termina(resultado);

            return;
        }

        if(jugadores == 1) {


            //llamo el metodo de inteligencia artificial
            casilla  = partida.ia();

            while (partida.comprueba_casilla_ocupada(casilla)!= true){

                casilla = partida.ia();
            }

            System.out.println("Número aleatorio: " +casilla);

            marca(casilla);

            resultado = partida.turno();

            if(resultado > 0 ){

                termina(resultado);

            }

        }

    }

    private void termina(int resultado){

        String mensaje;

        if(resultado == 1){
            mensaje = "Ha ganado los circulos";
        }else if(resultado == 2){
            mensaje = "Ha ganado las equis";
        }else{
            mensaje = "Empate";
        }

        Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);

        toast.setGravity(Gravity.CENTER, 0,10);

        toast.show();

        partida = null;

        findViewById(R.id.un_jug).setEnabled(true);

        findViewById(R.id.configGrupo).setAlpha(1);

        findViewById(R.id.dos_jug).setEnabled(true);

    }

    private void marca(int casilla){

        ImageView imagen;

        imagen = (ImageView) findViewById(casillas[casilla]);

        if(partida.jugador == 1){
            //asignar imagen
            imagen.setImageResource(R.drawable.circulo);
        }else {
            imagen.setImageResource(R.drawable.equis);
        }
    }

    private int jugadores;

    private int[] casillas;

    private Partida partida;
}
