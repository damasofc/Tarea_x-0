package com.example.jhair.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jhair.proyecto.clases.Evento;
import com.example.jhair.proyecto.clases.EventoDeportivo;
import com.example.jhair.proyecto.clases.EventoReligioso;

import org.w3c.dom.Text;

public class DatosEventoActivity extends AppCompatActivity {
    RelativeLayout layoutDatos;
    TextView tituloEvento;
    TextView tipoEvento;
    TextView fechaEvento;
    ImageButton btn_regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_evento);
        initComponents();
    }

    private void initComponents(){
        btn_regresar = (ImageButton) findViewById(R.id.imageButton_regresar);
        tipoEvento = (TextView) findViewById(R.id.tipoEvento);
        fechaEvento = (TextView) findViewById(R.id.fechaEvento);
        btn_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatosEventoActivity.this, Ver_EventoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        tituloEvento = (TextView) findViewById(R.id.tituloEvent);
        layoutDatos = (RelativeLayout)findViewById(R.id.layoutDatosEvent);
        int codigo = getIntent().getExtras().getInt("codigo");
        Evento event = MainClass.buscarEvento(codigo);
        tituloEvento.setText(event.getTitulo());
        fechaEvento.setText(event.getFechaString());
        if(event instanceof EventoReligioso){
            layoutDatos.setBackgroundResource(R.drawable.religioso);
            tipoEvento.setText("Religioso");
        }
        else if(event instanceof EventoDeportivo){
            layoutDatos.setBackgroundResource(R.drawable.deportivo);
            tipoEvento.setText("Deportivo");
        }
        else{
            layoutDatos.setBackgroundResource(R.drawable.musical);
            tipoEvento.setText("Musical");
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DatosEventoActivity.this,Ver_EventoActivity.class);
        startActivity(intent);
        finish();
    }
}
