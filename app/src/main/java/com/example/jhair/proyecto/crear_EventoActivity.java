package com.example.jhair.proyecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class crear_EventoActivity extends AppCompatActivity {
    TextView txt_fecha;
    private Spinner tipoEvento;
    Button btn_crear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear__evento);
        initComponents();
    }
    private void initComponents(){
        tipoEvento = (Spinner)findViewById(R.id.tipo_Evento);
        String [] tiposEvents = new String[3];
        tiposEvents[0] = "Deportivo";
        tiposEvents[1] = "Musical";
        tiposEvents[2] = "Religioso";
        ArrayAdapter<String> adapterEvents = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tiposEvents);
        adapterEvents.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoEvento.setAdapter(adapterEvents);
        txt_fecha = (TextView) findViewById(R.id.edit_date);
        txt_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePicker();

            }
        });
        btn_crear = (Button)findViewById(R.id.nuevoEvent);
        btn_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void mostrarDatePicker(){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
