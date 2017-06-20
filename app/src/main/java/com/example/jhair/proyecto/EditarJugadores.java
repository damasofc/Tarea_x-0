package com.example.jhair.proyecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.example.jhair.proyecto.clases.Evento;
import com.example.jhair.proyecto.clases.EventoDeportivo;
import com.example.jhair.proyecto.clases.EventoMusical;

import java.util.ArrayList;

public class EditarJugadores extends AppCompatActivity {
    GridLayout jugadoresGridLayout;
    ArrayList<String> jugadors1;
    ArrayList<String> jugadors2;
    int codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_jugadores);
        initComponents();
    }
    private void initComponents(){
        codigo = getIntent().getExtras().getInt("codEvent");
        jugadoresGridLayout = (GridLayout) findViewById(R.id.jugadoresGridLayout);
        EventoDeportivo eve =(EventoDeportivo) MainClass.buscarEvento(codigo);
        jugadors1 = eve.getListadoEquipo1();
        jugadors2 = eve.getListadoEquipo2();
        setDatos(1);
        setDatos(2);
    }

    //TODO: por aca voy, editando que se coloquen los textos de cada jugador.
    private void setDatos(int equipo){
        if(equipo == 1){
            if(jugadors1.size() == 0){

            }
            else{

            }
        }else{
            if(jugadors2.size() == 0){

            }
            else{

            }
        }
    }
}
