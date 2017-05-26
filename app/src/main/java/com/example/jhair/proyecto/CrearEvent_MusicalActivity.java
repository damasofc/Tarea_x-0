package com.example.jhair.proyecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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

            }
        });
    }
}
