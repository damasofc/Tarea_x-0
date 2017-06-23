package com.damasofc.tarea_x_0;

import android.app.usage.UsageEvents;
import android.graphics.drawable.Drawable;
import android.provider.CalendarContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton [][] cuadros;
    ViewGroup mainLinear;
    TextView turnoPlayer;
    Button jugarNuevo;
    TextView empates;
    TextView ganadosX;
    TextView ganados0;
    int turno;
    char [][] tablero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }
    private void initComponents(){
        tablero = new char[3][3];
        turno = 1;
        empates = (TextView)findViewById(R.id.empates);
        ganados0 = (TextView)findViewById(R.id.ganados0);
        ganadosX = (TextView)findViewById(R.id.ganadosX);
        jugarNuevo = (Button) findViewById(R.id.jugarNuevo);
        jugarNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
        ganadosX.setText(String.valueOf(MainClass.statics[0]));
        ganados0.setText(String.valueOf(MainClass.statics[1]));
        empates.setText(String.valueOf(MainClass.statics[2]));
        turnoPlayer = (TextView)findViewById(R.id.turnoPlayer);
        turnoPlayer.setText("Turno de x");
        mainLinear =(ViewGroup) findViewById(R.id.mainLinear);
        cuadros = new ImageButton[3][3];
        almacenarCuadros();
        setClick(1);
    }

    private void setClick(int fuente){
        if(fuente == 1) {
            for (int i = 0; i < cuadros.length; i++) {
                for (int m = 0; m < cuadros[i].length; m++) {
                    cuadros[i][m].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setClickListener();
                            ganadosX.setText(String.valueOf(MainClass.statics[0]));
                            ganados0.setText(String.valueOf(MainClass.statics[1]));
                            empates.setText(String.valueOf(MainClass.statics[2]));
                        }
                    });

                }
            }
        }else{
            for (int i = 0; i < cuadros.length; i++) {
                for (int m = 0; m < cuadros[i].length; m++) {
                    cuadros[i][m].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this,"El juego ha terminado ya",Toast.LENGTH_SHORT).show();
                            ganadosX.setText(String.valueOf(MainClass.statics[0]));
                            ganados0.setText(String.valueOf(MainClass.statics[1]));
                            empates.setText(String.valueOf(MainClass.statics[2]));
                        }
                    });

                }
            }
        }
    }
    private void setClickListener(){
        for(int i = 0;i < cuadros.length;i++){
            for(int m =0; m < cuadros[i].length;m++){
                if(cuadros[i][m].isPressed() == true){
                    if(comprobarEspacioVacio(i,m)) {
                        if (turno % 2 != 0) {
                            cuadros[i][m].setImageResource(R.drawable.x_png_18);
                            cuadros[i][m].setScaleType(ImageView.ScaleType.CENTER_CROP);
                            tablero[i][m] = 'x';
                            if(comprobarGanador('x') == true){
                                Toast ts = Toast.makeText(MainActivity.this,"Ha ganado X",Toast.LENGTH_SHORT);
                                ts.setGravity(Gravity.TOP,0,0);
                                ts.show();
                                MainClass.statics[0] += 1;
                                setClick(2);
                            }else {
                                if(comprobarEmpate()){
                                    Toast ts = Toast.makeText(MainActivity.this,"Empate",Toast.LENGTH_SHORT);
                                    ts.setGravity(Gravity.TOP,0,0);
                                    ts.show();
                                    MainClass.statics[2] += 1;
                                    setClick(2);
                                }else {
                                    turno++;
                                    turnoPlayer.setText("Turno de 0");
                                }
                            }
                        } else {
                            cuadros[i][m].setImageResource(R.drawable.letter_o);
                            cuadros[i][m].setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                            tablero[i][m] = '0';
                            if(comprobarGanador('0') == true){
                                Toast ts = Toast.makeText(MainActivity.this,"Ha ganado 0",Toast.LENGTH_SHORT);
                                ts.setGravity(Gravity.TOP,0,0);
                                ts.show();
                                MainClass.statics[1] += 1;
                                setClick(2);
                            }else {
                                if(comprobarEmpate()){
                                    Toast ts = Toast.makeText(MainActivity.this,"Empate",Toast.LENGTH_SHORT);
                                    ts.setGravity(Gravity.TOP,0,0);
                                    ts.show();
                                    MainClass.statics[2] += 1;
                                    setClick(2);
                                }else {
                                    turno++;
                                    turnoPlayer.setText("Turno de X");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private void almacenarCuadros(){
        int fila = 0;
        for(int i = 0;i < mainLinear.getChildCount();i++){
            if(mainLinear.getChildAt(i) instanceof LinearLayout){
                LinearLayout ly = (LinearLayout)mainLinear.getChildAt(i);
                int colum = 0;
                for (int m = 0; m <ly.getChildCount();m++){
                    if(ly.getChildAt(m)instanceof ImageButton){
                        cuadros[fila][colum] =(ImageButton) ly.getChildAt(m);
                        colum++;
                    }
                }
                fila++;
            }
        }
    }

    private boolean comprobarEspacioVacio(int fil,int col){
        if(tablero[fil][col] == 'x' || tablero[fil][col] == '0'){
            Toast.makeText(this, "Este espacio ya esta ocupado", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    private boolean comprobarGanador(char pieza){
        int contadorFilas = 0;
        int contadorColumna = 0;
        for(int i = 0; i < tablero.length;i++){
            for(int m = 0; m <tablero[i].length;m++){
                if(tablero[i][m] == pieza){
                    contadorFilas += 1;
                }
                if(tablero[m][i] == pieza){
                    contadorColumna += 1;
                }

            }
            if(contadorFilas == 3 || contadorColumna == 3){
                return true;
            }else{
                contadorFilas = 0;
                contadorColumna = 0;
            }
        }
        if((tablero[0][0] == pieza && tablero[1][1] == pieza && tablero[2][2] == pieza) ||
                (tablero[0][2] == pieza && tablero[1][1] == pieza && tablero[2][0] == pieza)){
            return true;
        }
        return false;
    }
    private boolean comprobarEmpate(){
        int cont = 0;
        for(int i = 0; i < tablero.length;i++) {
            for (int m = 0; m < tablero[i].length; m++) {
                if(tablero[i][m] == 'x' || tablero[i][m] == '0'){
                    cont++;
                }
            }
        }
        if(cont == 9){
            return true;
        }else{
            return false;
        }
    }
}
