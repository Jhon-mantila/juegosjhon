package com.example.gamesjhon;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Pelota extends Activity {

    int record;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pelota_rebote);
    }

    public void ayuda(View vista){

        Intent intencion = new Intent(this, AyudaActividad.class);

        startActivity(intencion);

    }

    public void dificultad(View vista){

        String dific = (String) ((Button) vista).getText();

        System.out.println("Texto boton: " + dific);

        int dificultad = 1;

        if(dific.equals(getString(R.string.medio)) || dific.equals("Normal")) dificultad =2;

        if(dific.equals("Dificult") || dific.equals("Dificil")) dificultad =3;

        Intent in = new Intent(this, Gestion.class);

        in.putExtra("DIFICULTAD", dificultad);

        //startActivity(in);

        startActivityForResult(in, 1);

    }

    protected void onActivityResult(int peticion, int codigo, Intent puntuacion) {

        super.onActivityResult(peticion, codigo, puntuacion);

        if(peticion != 1 || codigo != RESULT_OK) return;

        int resultado = puntuacion.getIntExtra("PUNTUACION", 0);

        if(resultado > record){

            record = resultado;

            TextView caja = (TextView) findViewById(R.id.record);

            caja.setText("Record: " + record);

            guardaRecord();

        }else{

            String puntación_partida = "Toques: " + resultado;

            Toast mitoast = Toast.makeText(this, puntación_partida, Toast.LENGTH_SHORT);

            mitoast.setGravity(Gravity.CENTER, 0,0);

            mitoast.show();
        }


    }

    public void onResume() {

        super.onResume();

        leeRecord();
    }

    private void guardaRecord(){

        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor mieditor = datos.edit();

        mieditor.putInt("RECORD", record);

        mieditor.apply();
    }

    public void leeRecord(){

        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);

        record = datos.getInt("RECORD", 0);

        TextView caja = (TextView) findViewById(R.id.record);

        caja.setText("Record:" + record);

    }
}
