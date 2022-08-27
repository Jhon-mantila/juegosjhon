package com.example.gamesjhon;

import java.util.Random;

public class Partida {

    public Partida(int dificultad){

        this.dificultad = dificultad;

        System.out.println("Paritda dificulta: " + this.dificultad);
        jugador = 1;

        //Relleno el array en ceros
        casillas_ocupadas = new int[9];

        for (int i = 0 ; i < 9; i++){

            casillas_ocupadas[i]=0;
        }

       for(int j=0; j<casillas_ocupadas.length; j++){
           System.out.println("posición: " + j + " valor: " +casillas_ocupadas[j]);
       }

    }

    public boolean comprueba_casilla_ocupada(int casilla){

        if(casillas_ocupadas[casilla] != 0){
            return false;
        }else{
            casillas_ocupadas[casilla] = jugador;
        }

        for(int j=0; j<casillas_ocupadas.length; j++){
            System.out.println("posición: " + j + " valor: " +casillas_ocupadas[j]);
        }

        return true;
    }

    //para los turnos, empate, victoria ceros o equis, ningna de las anterior
    public int  turno(){

        boolean empate = true;

        boolean ultimo_movimiento = true;

        for(int i=0; i<COMBINACIONES.length; i++) {

            for(int pos: COMBINACIONES[i]) {

                System.out.println("Valor en posición: " + pos + " " + casillas_ocupadas[pos]);

                if(casillas_ocupadas[pos] != jugador) ultimo_movimiento = false;

                if(casillas_ocupadas[pos] == 0) empate = false;

            }//For anidado
            System.out.println("-----------------------------------------------------");

            if(ultimo_movimiento) return jugador;

            ultimo_movimiento = true;


        }//For principal
        //encaso de empate
        if(empate){

            return 3;
        }
        jugador++;

        if(jugador>2){

            jugador = 1;

        }

        return 0;
    }

    public int dosEnRaya(int jugador_en_turno){

        int casilla=-1;

        int cuantas_lleva = 0;

        for(int i=0; i<COMBINACIONES.length; i++) {

            for(int pos: COMBINACIONES[i]) {

                if(casillas_ocupadas[pos] == jugador_en_turno) cuantas_lleva++;

                if(casillas_ocupadas[pos] == 0 ) casilla = pos;

            }//For anidado

            if(cuantas_lleva == 2 && casilla != -1) return  casilla;

            //Error que tenía y no me tenía encuenta los difrentes niveles de dificulta
            casilla = -1;

            cuantas_lleva = 0;

        }//For principal

        return -1;
    }

    //inteligencia artificial
    public int ia(){

        int casilla;

        casilla = dosEnRaya(2);
        System.out.println("Dos en raya 2 :" + casilla);
        if(casilla != -1) return casilla;


        if(dificultad > 0){

            casilla = dosEnRaya(1);
            System.out.println("Dos en raya 1: " + casilla);
            if(casilla != -1) return  casilla;

        }

        if(dificultad == 2){

            if(casillas_ocupadas[0] == 1 && casillas_ocupadas[2] == 0 && casillas_ocupadas[6] == 0 && casillas_ocupadas[8] == 0) return 3;

            if(casillas_ocupadas[0] == 0 && casillas_ocupadas[2] == 1 && casillas_ocupadas[6] == 0 && casillas_ocupadas[8] == 0) return 3;

            if(casillas_ocupadas[0] == 0 && casillas_ocupadas[2] == 0 && casillas_ocupadas[6] == 1 && casillas_ocupadas[8] == 0) return 5;

            if(casillas_ocupadas[0] == 0 && casillas_ocupadas[2] == 0 && casillas_ocupadas[6] == 0 && casillas_ocupadas[8] == 1) return 4;

            if(casillas_ocupadas[0] == 0) return 0;

            if(casillas_ocupadas[2] == 0) return 2;

            if(casillas_ocupadas[6] == 0) return 6;

            if(casillas_ocupadas[8] == 0) return 8;



        }

        Random casilla_azar = new Random();

        casilla = casilla_azar.nextInt(9);

        //regreso el numero aleatorio de 0 a 8
        return casilla;

    }
    public int jugador;

    public final int dificultad;

    private int casillas_ocupadas[];

    private final int [][] COMBINACIONES = {
            {0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}
    };
}
