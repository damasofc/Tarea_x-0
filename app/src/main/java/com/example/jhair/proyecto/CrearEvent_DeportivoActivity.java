package com.example.jhair.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jhair.proyecto.clases.EventoDeportivo;

import java.util.Calendar;

public class CrearEvent_DeportivoActivity extends AppCompatActivity {
    Spinner tipoDeporte;
    EditText equipo1;
    EditText equipo2;
    Button nuevoEvento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_event__deportivo);
        initComponents();
    }
    private void initComponents(){
        tipoDeporte = (Spinner)findViewById(R.id.tipo_Deporte);
        equipo1 = (EditText) findViewById(R.id.equipo1);
        equipo2 = (EditText)findViewById(R.id.equipo2);
        nuevoEvento = (Button)findViewById(R.id.nuevoEventDeport);
        EventoDeportivo.Deportes [] items = EventoDeportivo.Deportes.values();
        ArrayAdapter<EventoDeportivo.Deportes> adapterEvents = new ArrayAdapter<EventoDeportivo.Deportes>(this,android.R.layout.simple_spinner_item,items);
        adapterEvents.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoDeporte.setAdapter(adapterEvents);
        nuevoEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inicia,todos los atributos que vienen de crear_EventoActivity
                int codigo = getIntent().getExtras().getInt("Codigo");
                String titulo = getIntent().getExtras().getString("Titulo");
                String descript = getIntent().getExtras().getString("descripcion");
                Calendar fecha = (Calendar) getIntent().getExtras().get("fecha");
                double monto = getIntent().getExtras().getDouble("monto");
                //Termina,todos los atributos que vienen de crear_EventoActivity
                String tiposport = tipoDeporte.getSelectedItem().toString();
                if(comprobarEspaciosLlenos()){
                    if(MainClass.existeEvento(codigo)){
                        Toast.makeText(CrearEvent_DeportivoActivity.this,"Este codigo de evento ya existe",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CrearEvent_DeportivoActivity.this,crear_EventoActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        EventoDeportivo ed = new EventoDeportivo(codigo,titulo,fecha,monto,descript,tiposport,equipo1.getText().toString(),equipo2.getText().toString());
                        MainClass.añadirEvento(ed);
                        Toast.makeText(CrearEvent_DeportivoActivity.this,"Evento creado exitosamente",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(CrearEvent_DeportivoActivity.this,MenuMainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else{
                    Toast.makeText(CrearEvent_DeportivoActivity.this,"Porfavor llene todos los datos",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private boolean comprobarEspaciosLlenos(){
        if(equipo1.length() > 0 && equipo2.length() > 0){
            return true;
        }
        return false;
    }
}
