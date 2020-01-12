package com.example.sopadelletres;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Joc extends AppCompatActivity {

    public static final Integer ROWS = 9;
    public static final Integer COLUMNS = 9;


    LinearLayout tablero;

    Character[][] arrayTablero = new Character[Joc.ROWS][Joc.COLUMNS];

    String[] palabras = {"MAUL", "VADER", "GALERA", "GUAPO", "ANAKIN"};

    Character[] ABC = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joc);

        this.tablero = findViewById(R.id.tablero);


        this.rellenarArrayTablero();

        this.crearTablero();
    }

    public void rellenarArrayTablero() {

        for (int i = 0; i < palabras.length; i++) {

            int positionRandom = (int) (0 + (Math.random() * (3 - 0)));

            switch (positionRandom) {
                case 0:
                    this.rellenarPalabraEnX(i);
                    break;
                case 1:
                    this.rellenarPalabraEnY(i);
                    break;
                case 2:
                    this.rellenarPalabraEnDiagonal(i);
                    break;
            }
        }

        //    this.letrasAleatorias();


    }

    public void letrasAleatorias() {

        for (int i = 0; i < arrayTablero.length; i++) {

            for (int j = 0; j < arrayTablero[0].length; j++) {

                int poscion = (int) (0 + (Math.random() * (ABC.length - 0)));

                if (this.arrayTablero[i][j] == null) {
                    this.arrayTablero[i][j] = this.ABC[poscion];
                }


            }
        }
    }

    public void rellenarPalabraEnX(Integer palabraPosicion) {

        int positionX = (int) (0 + (Math.random() * ((Joc.COLUMNS) - 0)));
        int positionY = (int) (0 + (Math.random() * ((Joc.ROWS - palabras[palabraPosicion].length()) - 0)));

        for (int i = 0; i < palabras[palabraPosicion].length(); i++) {

            this.arrayTablero[positionX][positionY + i] = palabras[palabraPosicion].toCharArray()[i];
        }


    }

    public void rellenarPalabraEnY(Integer palabraPosicion) {

        int positionX = (int) (0 + (Math.random() * ((Joc.COLUMNS - palabras[palabraPosicion].length()) - 0)));
        int positionY = (int) (0 + (Math.random() * ((Joc.ROWS) - 0)));


        for (int i = 0; i < palabras[palabraPosicion].length(); i++) {
            this.arrayTablero[positionX + i][positionY] = palabras[palabraPosicion].toCharArray()[i];
        }

    }


    public void rellenarPalabraEnDiagonal(Integer palabraPosicion) {

        int positionX = (int) (0 + (Math.random() * ((Joc.COLUMNS - palabras[palabraPosicion].length()) - 0)));
        int positionY = (int) (0 + (Math.random() * ((Joc.ROWS - palabras[palabraPosicion].length()) - 0)));


        for (int i = 0; i < palabras[palabraPosicion].length(); i++) {

            this.arrayTablero[positionX + i][positionY + i] = palabras[palabraPosicion].toCharArray()[i];
        }


    }


    public void crearTablero() {

        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        rowParams.setMargins(10, 10, 10, 10);

        LinearLayout.LayoutParams casillaParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        casillaParams.gravity = Gravity.CENTER;

        LinearLayout.LayoutParams divParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);

        ShapeDrawable sd = new ShapeDrawable();
        sd.setShape(new RectShape());
        sd.getPaint().setColor(Color.BLACK);
        sd.getPaint().setStrokeWidth(1f);
        sd.getPaint().setStyle(Paint.Style.STROKE);

        for (int i = 0; i < arrayTablero.length; i++) {

            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setLayoutParams(rowParams);

            for (int j = 0; j < arrayTablero[0].length; j++) {

                LinearLayout div = new LinearLayout(this);
                div.setOrientation(LinearLayout.VERTICAL);
                div.setLayoutParams(divParams);
                div.setPadding(1, 1, 1, 1);

                TextView casilla = new TextView(this);

                casilla.setPadding(10, 10, 10, 10);
                casilla.setLayoutParams(casillaParams);
                casilla.setGravity(View.TEXT_ALIGNMENT_CENTER);
                casilla.setBackground(sd);


                if (arrayTablero[i][j] != null) {
                    casilla.setText(arrayTablero[i][j].toString());
                }
                div.addView(casilla);
                row.addView(div);


            }

            this.tablero.addView(row);

        }

    }


}
