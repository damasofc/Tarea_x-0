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

public class EventosFuturosActivity extends AppCompatActivity {

    ArrayList<Evento> EventosFuturos;
    int contadorDeportivos = 0;
    int contadorReligiosos = 0;
    int contadorMusicales = 0;
    double montoTotal = 0;
    TextView DeportivosF;
    TextView ReligiososF;
    TextView MusicalesF;
    TextView MontoTotalF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos_futuros);
        DeportivosF = (TextView) findViewById(R.id.textViewDeportivosF);
        ReligiososF = (TextView) findViewById(R.id.textViewReligiososF);
        MusicalesF = (TextView) findViewById(R.id.textViewMusicalesF);
        MontoTotalF = (TextView) findViewById(R.id.textViewMontoTotalF);

        /*/lo que voy a hacer es que voy a crear otro arrayList que contenga los eventos ya
        realizados, extrayendo la info del arrayList que esta en el MainClass, para asi no alterar
        la info que hay en el arraylist original
        */
        EventosFuturos = new ArrayList<>();
        for(Evento e : MainClass.eventos) {
            if(Calendar.getInstance().before(e.getFecha())) {
                EventosFuturos.add(e);
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
        ListAdapter pambisitoAdapter = new CustomAdapter(getApplicationContext(), EventosFuturos);
        ListView ListEventosFuturos = (ListView) findViewById(R.id.List_eventos_futuros);
        ListEventosFuturos.setAdapter(pambisitoAdapter);

        //ahora a poner los contadores en los textViews
        DeportivosF.setText("Eventos deportivos: "+String.valueOf(contadorDeportivos));
        ReligiososF.setText("Eventos religiosos: "+String.valueOf(contadorReligiosos));
        MusicalesF.setText("Eventos musicales: "+String.valueOf(contadorMusicales));
        MontoTotalF.setText("Monto total: "+String.valueOf(montoTotal));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EventosFuturosActivity.this, MenuReportes.class);
        startActivity(intent);
        finish();
    }
}

