package com.example.gamesjhon;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Concentrese extends Activity {

    //variables para losc componentes de la vista
    private ImageButton imb00, imb01, imb02,imb03, imb04, imb05,imb06,imb07,imb08,imb09,imb10,imb11,imb12,imb13,imb14,imb15;

    private ImageButton[] tablero = new ImageButton[16];

    private Button botonReiniciar, botonSalir;

    private TextView textoPuntuacion;

    private int puntuacion, aciertos;

    //imagenes

    private int imagenes[];

    private int fondo;

    //varibales del juego

    private ArrayList<Integer> arrayDesordenado;

    private ImageButton primero;

    private int numeroPrimero, numeroSegudno;

    private boolean bloqueo = false;

    private final Handler handler = new Handler();

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.concentrese);

        init();
    }

    private void cargarImagenesTablero(){

        imb00 = (ImageButton) findViewById(R.id.boton00);
        imb01 = (ImageButton) findViewById(R.id.boton01);
        imb02 = (ImageButton) findViewById(R.id.boton02);
        imb03 = (ImageButton) findViewById(R.id.boton03);
        imb04 = (ImageButton) findViewById(R.id.boton04);
        imb05 = (ImageButton) findViewById(R.id.boton05);
        imb06 = (ImageButton) findViewById(R.id.boton06);
        imb07 = (ImageButton) findViewById(R.id.boton07);
        imb08 = (ImageButton) findViewById(R.id.boton08);
        imb09 = (ImageButton) findViewById(R.id.boton09);
        imb10 = (ImageButton) findViewById(R.id.boton10);
        imb11 = (ImageButton) findViewById(R.id.boton11);
        imb12 = (ImageButton) findViewById(R.id.boton12);
        imb13 = (ImageButton) findViewById(R.id.boton13);
        imb14 = (ImageButton) findViewById(R.id.boton14);
        imb15 = (ImageButton) findViewById(R.id.boton15);

        tablero[0] = imb00;
        tablero[1] = imb01;
        tablero[2] = imb02;
        tablero[3] = imb03;
        tablero[4] = imb04;
        tablero[5] = imb05;
        tablero[6] = imb06;
        tablero[7] = imb07;
        tablero[8] = imb08;
        tablero[9] = imb09;
        tablero[10] = imb10;
        tablero[11] = imb11;
        tablero[12] = imb12;
        tablero[13] = imb13;
        tablero[14] = imb14;
        tablero[15] = imb15;


    }

    private void cargarBotones(){

        botonReiniciar = (Button) findViewById(R.id.btnReiniciiar);

        botonSalir = (Button) findViewById(R.id.botonSalir);

        botonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
            }
        });

        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void cargarTexto(){

        textoPuntuacion = (TextView) findViewById(R.id.puntuacion);

        puntuacion = 0;

        aciertos = 0;
        textoPuntuacion.setText("Puntuación : " +puntuacion);
    }

    private void cargarImagenes(){

        imagenes = new int[] {
                R.drawable.c1,
                R.drawable.c2,
                R.drawable.c3,
                R.drawable.c4,
                R.drawable.c5,
                R.drawable.c6,
                R.drawable.c7,
                R.drawable.c8
        };

        fondo = R.drawable.carta;

    }

    private ArrayList<Integer> barajar (int longitud){

        ArrayList<Integer> result = new ArrayList<Integer>();

        for(int i=0; i < longitud * 2; i++){

            result.add(i % longitud);
        }
        //colocar en desorden el resultado de la coleción
        Collections.shuffle(result);

        System.out.println(Arrays.toString(result.toArray()));

        return result;

    }

    private void comprobar(int i, final ImageButton imgB){

        if(primero == null){

            primero = imgB;
            primero.setScaleType(ImageView.ScaleType.CENTER);
            primero.setImageResource(imagenes[arrayDesordenado.get(i)]);
            primero.setEnabled(false);
            numeroPrimero = arrayDesordenado.get(i);

        }else{

            bloqueo = true;
            imgB.setScaleType(ImageView.ScaleType.CENTER);
            imgB.setImageResource(imagenes[arrayDesordenado.get(i)]);
            imgB.setEnabled(false);
            numeroSegudno = arrayDesordenado.get(i);

            if(numeroPrimero == numeroSegudno){
                primero = null;
                bloqueo = false;
                aciertos++;
                puntuacion++;
                textoPuntuacion.setText("Puntuación: " + puntuacion);

                if(aciertos == imagenes.length){
                    Toast toast = Toast.makeText(getApplicationContext(), "Has ganado!!", Toast.LENGTH_LONG);
                    toast.show();
                }

            }else{

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        primero.setScaleType(ImageView.ScaleType.CENTER);
                        primero.setImageResource(fondo);
                        primero.setEnabled(true);

                        imgB.setScaleType(ImageView.ScaleType.CENTER);
                        imgB.setImageResource(fondo);
                        imgB.setEnabled(true);

                        bloqueo = false;
                        primero = null;
                    }
                }, 1000);

            }

        }
    }

    private void init(){
        cargarImagenesTablero();
        cargarBotones();
        cargarTexto();
        cargarImagenes();

        arrayDesordenado = barajar(imagenes.length);

        for(int i=0; i<tablero.length; i++){
            tablero[i].setScaleType(ImageView.ScaleType.CENTER);
            tablero[i].setImageResource(imagenes[arrayDesordenado.get(i)]);
            //tablero[i].setImageResource(fondo);
        }


        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                for(int i=0; i<tablero.length; i++){
                    tablero[i].setScaleType(ImageView.ScaleType.CENTER);
                    //tablero[i].setImageResource(imagenes[arrayDesordenado.get(i)]);
                    tablero[i].setImageResource(fondo);
                }
            }
        },500);

        for(int i=0; i<tablero.length; i++){

            final int j = i;

            tablero[i].setEnabled(true);

            tablero[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!bloqueo){
                        comprobar(j, tablero[j]);
                    }
                }
            });
        }

    }
}
