package com.example.sopadelletres;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button button;
    //tres declaraciones para la bd y la lsita donde iran las querys
    private final String BBDD ="Sopa";
    private final String TAULA = "puntuacion";
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button = findViewById(R.id.bt1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empezarJuego();
            }
        });

        //Creació de la BBDD/taula, query per accedir a les dades i mostrarles despres
        lista = (ListView) findViewById(R.id.llista);
        ArrayList<String> resultats = new ArrayList<String>();

        SQLiteDatabase baseDades = null;
        try {

            baseDades = this.openOrCreateDatabase(BBDD, MODE_PRIVATE, null);


            baseDades.execSQL("CREATE TABLE IF NOT EXISTS "
                    + TAULA
                    + " (data VARCHAR,"
                    + " puntuacio INT);");


            Cursor c = baseDades.rawQuery("SELECT data, puntuacio"
                            + " FROM " + TAULA
                            + " WHERE puntuacio > 1 LIMIT 5;",
                    null);



            int columnaData = c.getColumnIndex("data");
            int columnaPuntuacio = c.getColumnIndex("puntuacio");



            if (c != null) {
                if(c.getCount() > 0) {
                    if (c.isBeforeFirst()) {
                        c.moveToFirst();
                        int i = 0;

                        do {
                            i++;
                            String data = c.getString(columnaData);
                            int puntuacio = c.getInt(columnaPuntuacio);

                            String nomColumnaPuntuacio = c.getColumnName(columnaPuntuacio);


                            resultats.add("" + i
                                    + " - " + nomColumnaPuntuacio + ": " + puntuacio + " | "+data);

                        } while (c.moveToNext());
                    }
                }
            }

        } finally {
            if (baseDades != null) {
                baseDades.close();
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                resultats );

        lista.setAdapter(arrayAdapter);

    }

    public void empezarJuego() {
        Intent intent = new Intent(this, Joc.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                abrirInfoJoc();
                return true;
            case R.id.item2:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void abrirInfoJoc() {
        Intent intent = new Intent(this, InfoJoc.class);
        startActivity(intent);
    }
}
