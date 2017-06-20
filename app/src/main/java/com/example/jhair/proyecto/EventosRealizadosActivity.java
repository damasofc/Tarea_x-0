package com.example.jhair.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jhair.proyecto.clases.Evento;
import com.example.jhair.proyecto.clases.EventoDeportivo;
import com.example.jhair.proyecto.clases.EventoMusical;
import com.example.jhair.proyecto.clases.EventoReligioso;

import java.util.ArrayList;
import java.util.Calendar;

public class EventosRealizadosActivity extends AppCompatActivity {
    ArrayList <Evento> EventosRealizados;
    int contadorDeportivos = 0;
    int contadorReligiosos = 0;
    int contadorMusicales = 0;
    double montoTotal = 0;
    TextView DeportivosR;
    TextView ReligiososR;
    TextView MusicalesR;
    TextView MontoTotalR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos_realizados);
        DeportivosR = (TextView) findViewById(R.id.textViewDeportivosR);
        ReligiososR = (TextView) findViewById(R.id.textViewReligiososR);
        MusicalesR = (TextView) findViewById(R.id.textViewMusicalesR);
        MontoTotalR = (TextView) findViewById(R.id.textViewMontoTotalR);

        /*/lo que voy a hacer es que voy a crear otro arrayList que contenga los eventos ya
        realizados, extrayendo la info del arrayList que esta en el MainClass, para asi no alterar
        la info que hay en el arraylist original
        */
        EventosRealizados = new ArrayList<>();
        for(Evento e : MainClass.eventos) {
            if(Calendar.getInstance().after(e.getFecha())) {
                EventosRealizados.add(e);
                montoTotal += e.getMontoPagar();
                if(e instanceof EventoDeportivo) {
                    contadorDeportivos++;
                }
                else if (e instanceof EventoMusical) {
                    contadorMusicales++;
                }
                else if(e instanceof EventoReligioso) {
                    contadorReligiosos++;
                }
            }
        }
        ListAdapter pambisitoAdapter = new CustomAdapter(getApplicationContext(), EventosRealizados);
        ListView ListEventosRealizados = (ListView) findViewById(R.id.List_eventos_realizados);
        ListEventosRealizados.setAdapter(pambisitoAdapter);

        //ahora a poner los contadores en los textViews
        DeportivosR.setText("Eventos deportivos: "+String.valueOf(contadorDeportivos));
        ReligiososR.setText("Eventos religiosos: "+String.valueOf(contadorReligiosos));
        MusicalesR.setText("Eventos musicales: "+String.valueOf(contadorMusicales));
        MontoTotalR.setText("Monto total: "+String.valueOf(montoTotal));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EventosRealizadosActivity.this, MenuReportes.class);
        startActivity(intent);
        finish();
    }
}
