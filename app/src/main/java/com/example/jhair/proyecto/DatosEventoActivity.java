package com.example.jhair.proyecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jhair.proyecto.clases.Evento;
import com.example.jhair.proyecto.clases.EventoDeportivo;
import com.example.jhair.proyecto.clases.EventoReligioso;

import org.w3c.dom.Text;

public class DatosEventoActivity extends AppCompatActivity {
    RelativeLayout layoutDatos;
    TextView tituloEvento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_evento);
        initComponents();
    }

    private void initComponents(){
        tituloEvento = (TextView) findViewById(R.id.tituloEvent);
        layoutDatos = (RelativeLayout)findViewById(R.id.layoutDatosEvent);
        int codigo = getIntent().getExtras().getInt("codigo");
        Evento event = MainClass.buscarEvento(codigo);
        tituloEvento.setText(event.getTitulo());
        if(event instanceof EventoReligioso){
            layoutDatos.setBackgroundResource(R.drawable.religioso);
        }
        else if(event instanceof EventoDeportivo){
            layoutDatos.setBackgroundResource(R.drawable.deportivo);
        }
        else{
            layoutDatos.setBackgroundResource(R.drawable.musical);
        }
    }
}
