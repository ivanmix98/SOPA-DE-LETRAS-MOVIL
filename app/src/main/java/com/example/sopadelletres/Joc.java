package com.example.sopadelletres;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Joc extends AppCompatActivity implements View.OnClickListener {

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
                    if (!this.rellenarPalabraEnX(i)) i--;
                    break;
                case 1:
                    if (!this.rellenarPalabraEnY(i)) i--;
                    break;
                case 2:
                    if (!this.rellenarPalabraEnDiagonal(i)) i--;
                    break;
            }
        }
        this.letrasAleatorias();
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

    public Boolean rellenarPalabraEnX(Integer palabraPosicion) {

        int positionX = (int) (0 + (Math.random() * ((Joc.COLUMNS) - 0)));
        int positionY = (int) (0 + (Math.random() * ((Joc.ROWS - palabras[palabraPosicion].length()) - 0)));

        for (int i = 0; i < palabras[palabraPosicion].length(); i++) {

            if (this.arrayTablero[positionX][positionY + i] != null) {
                return false;
            }
        }

        for (int i = 0; i < palabras[palabraPosicion].length(); i++) {

            this.arrayTablero[positionX][positionY + i] = palabras[palabraPosicion].toCharArray()[i];
        }

        return true;


    }

    public Boolean rellenarPalabraEnY(Integer palabraPosicion) {

        int positionX = (int) (0 + (Math.random() * ((Joc.COLUMNS - palabras[palabraPosicion].length()) - 0)));
        int positionY = (int) (0 + (Math.random() * ((Joc.ROWS) - 0)));


        for (int i = 0; i < palabras[palabraPosicion].length(); i++) {
            if (this.arrayTablero[positionX + i][positionY] != null) {
                return false;
            }
        }


        for (int i = 0; i < palabras[palabraPosicion].length(); i++) {
            this.arrayTablero[positionX + i][positionY] = palabras[palabraPosicion].toCharArray()[i];
        }

        return true;

    }


    public Boolean rellenarPalabraEnDiagonal(Integer palabraPosicion) {

        int positionX = (int) (0 + (Math.random() * ((Joc.COLUMNS - palabras[palabraPosicion].length()) - 0)));
        int positionY = (int) (0 + (Math.random() * ((Joc.ROWS - palabras[palabraPosicion].length()) - 0)));

        for (int i = 0; i < palabras[palabraPosicion].length(); i++) {
            if (this.arrayTablero[positionX + i][positionY + i] != null) {
                return false;
            }
        }

        for (int i = 0; i < palabras[palabraPosicion].length(); i++) {

            this.arrayTablero[positionX + i][positionY + i] = palabras[palabraPosicion].toCharArray()[i];
        }

        return true;

    }


    public void crearTablero() {

        int z = 0;

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
                z++;

                LinearLayout div = new LinearLayout(this);
                div.setOrientation(LinearLayout.VERTICAL);
                div.setLayoutParams(divParams);
                div.setPadding(1, 1, 1, 1);

                TextView casilla = new TextView(this);



                casilla.setPadding(10, 10, 10, 10);
                casilla.setLayoutParams(casillaParams);
                casilla.setGravity(View.TEXT_ALIGNMENT_CENTER);
                casilla.setBackground(sd);
                casilla.setId(100+z);

                if (arrayTablero[i][j] != null) {
                    casilla.setText(arrayTablero[i][j].toString());
                    System.out.println(casilla.getId());
                    //System.out.println(casilla.getText().toString());
                }
                div.addView(casilla);
                div.setId(z);
                div.setOnClickListener(this);
                row.addView(div);
            }
            this.tablero.addView(row);
        }
    }


    @Override
    public void onClick(View view) {
        LinearLayout casilla = findViewById(view.getId());
        TextView casillaText = findViewById(100+(int)casilla.getId());

        //para saber si la casilla está seleccionada o no
        int color = Color.TRANSPARENT;
        Drawable background = casilla.getBackground();
        if (background instanceof ColorDrawable){
            color = ((ColorDrawable) background).getColor();
        }


        if(color != Color.TRANSPARENT){
            casilla.setBackgroundColor(Color.TRANSPARENT);
        }
        if(color == Color.TRANSPARENT){
            casilla.setBackgroundColor(Color.RED);
            System.out.println(view.getId());
            System.out.println(casillaText.getText().toString());
        }


    }

    
}