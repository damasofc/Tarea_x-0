package com.example.jhair.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import com.example.jhair.proyecto.clases.EventoMusical;

public class CrearEvent_MusicalActivity extends AppCompatActivity {
    Spinner tipoMusica;
    Button nuevoMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_event__musical);
        initComponents();
    }

    public void initComponents(){
        tipoMusica = (Spinner)findViewById(R.id.tipo_Musica);
        nuevoMusic = (Button)findViewById(R.id.nuevoEventMusic);
        EventoMusical.Musica []items =  EventoMusical.Musica.values();
        ArrayAdapter<EventoMusical.Musica> adapterEvents = new ArrayAdapter<EventoMusical.Musica>(this,android.R.layout.simple_spinner_item,items);
        adapterEvents.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoMusica.setAdapter(adapterEvents);
        nuevoMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int codigo = getIntent().getExtras().getInt("Codigo");
                String titulo = getIntent().getExtras().getString("Titulo");
                String descript = getIntent().getExtras().getString("descripcion");
                Calendar fecha = (Calendar) getIntent().getExtras().get("fecha");
                double monto = getIntent().getExtras().getDouble("monto");
                String tipodeMusica = tipoMusica.getSelectedItem().toString();
                EventoMusical em = new EventoMusical(codigo,titulo,fecha,monto,descript,tipodeMusica);
                if(MainClass.existeEvento(em.getCodigo()) == false){
                    MainClass.añadirEvento(em);
                    Toast.makeText(CrearEvent_MusicalActivity.this,"Evento creado exitosamente",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CrearEvent_MusicalActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(CrearEvent_MusicalActivity.this,"Este codigo de evento ya existe",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CrearEvent_MusicalActivity.this,crear_EventoActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
}
