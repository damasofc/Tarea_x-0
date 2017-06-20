package com.example.jhair.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.jhair.proyecto.clases.Evento;
import com.example.jhair.proyecto.clases.EventoDeportivo;
import com.example.jhair.proyecto.clases.EventoMusical;

import java.util.ArrayList;

public class EditarJugadores extends AppCompatActivity {
    GridLayout jugadoresGridLayout;
    ArrayList<String> jugadors1;
    ArrayList<String> jugadors2;
    EditText [][] arr = new EditText[2][10];
    int codigo;
    Button guardar;
    EventoDeportivo eve;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_jugadores);
        initComponents();
    }
    private void initComponents(){
        guardar = (Button)findViewById(R.id.guardarBtn);
        codigo = getIntent().getExtras().getInt("codEvent");
        jugadoresGridLayout = (GridLayout) findViewById(R.id.jugadoresGridLayout);
        eve =(EventoDeportivo) MainClass.buscarEvento(codigo);
        jugadors1 = eve.getListadoEquipo1();
        jugadors2 = eve.getListadoEquipo2();
        if(eve.getTipoDeporte().equals(EventoDeportivo.Deportes.TENIS)){
            for(int i =jugadoresGridLayout.getChildCount()-1; i > 5;i--){
                if(i == jugadoresGridLayout.getChildCount()-1){continue;}
                jugadoresGridLayout.removeViewAt(i);

            }
        }
        almacenarEdt();
        setDatos(1);
        setDatos(2);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();
                Toast.makeText(EditarJugadores.this,"Jugadores Guardados",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EditarJugadores.this,EditarEvento.class);
                intent.putExtra("codEvent",codigo);
                startActivity(intent);
                finish();
            }
        });
    }

    private void almacenarEdt(){
        int x1 = 0;
        int x2 = 0;
        for(int i =0; i < jugadoresGridLayout.getChildCount();i++){
            try {
                EditText tx = (EditText) jugadoresGridLayout.getChildAt(i);
                String t = tx.getText().toString();
                if ((t.charAt(t.length() - 1) == '1')) {
                    arr[0][x1] = tx;
                    x1++;
                } else {
                    arr[1][x2] = tx;
                    x2++;
                }
            }catch(Exception e){continue;}
        }
    }

    //TODO: por aca voy, editando que se coloquen los textos de cada jugador.
    private void setDatos(int equipo){

        if(equipo == 1){
            if(jugadors1.size() == 0){
                for(int i = 0; i < arr.length;i++) {
                    for (int m = 0; m < arr[i].length; m++) {
                        if(i==0){
                            if(arr[i][m] == null){continue;}
                            arr[i][m].setText("");
                            arr[i][m].setHint((m+1)+".Jugador1");
                        }
                    }
                }
            }
            else{
                for(int i = 0; i < arr.length;i++) {
                    int x = 0;
                    for (int m = 0; m < arr[i].length; m++) {
                        if(i==0){
                            if(x>jugadors1.size()-1 || arr[i][m]==null){continue;}
                            arr[i][m].setText("");
                            arr[i][m].setHint(jugadors1.get(x));
                            x++;
                        }
                    }
                }

            }
        }else{
            if(jugadors2.size() == 0){
                for(int i = 0; i < arr.length;i++) {
                    for (int m = 0; m < arr[i].length; m++) {
                        if(i==1){
                            if(arr[i][m] == null){continue;}
                            arr[i][m].setText("");
                            arr[i][m].setHint((m+1)+".Jugador");
                        }
                    }
                }
            }
            else{
                for(int i = 0; i < arr.length;i++) {
                    int x = 0;
                    for (int m = 0; m < arr[i].length; m++) {
                        if(i==1){
                            if(x>jugadors2.size()-1|| arr[i][m]==null){continue;}
                            arr[i][m].setText("");
                            arr[i][m].setHint(jugadors2.get(x));
                            x++;
                        }
                    }
                }
            }
        }
    }

    private void guardarDatos(){
        int tam = arr.length;
        for(int i = 0; i < arr.length;i++){
            for(int m = 0; m <arr[i].length; m++){
                if(arr[i][m] == null){continue;}
                if(i == 0) {
                    String name = "";
                    if(arr[i][m].getText().toString().length() > 0) {
                        name = arr[i][m].getText().toString();

                    }else{name = arr[i][m].getHint().toString();}
                    eve.addJugador(1,name);
                }else{
                    String name = "";
                    if(arr[i][m].getText().toString().length() > 0) {
                        name = arr[i][m].getText().toString();
                    }else{name = arr[i][m].getHint().toString();}
                    eve.addJugador(2,name);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EditarJugadores.this,EditarEvento.class);
        intent.putExtra("codEvent",codigo);
        startActivity(intent);
        finish();
    }
}
