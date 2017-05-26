package com.example.jhair.proyecto;

import android.content.Intent;
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
    private Spinner tipoEvento;
    private EditText codigo;
    private EditText titulo;
    private EditText descripcion;
    private TextView txt_fecha;
    private EditText monto;
    private Button btn_crear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear__evento);
        initComponents();
    }
    private void initComponents(){
        tipoEvento = (Spinner)findViewById(R.id.tipo_Evento);
        codigo = (EditText)findViewById(R.id.editCodigo);
        titulo = (EditText)findViewById(R.id.editTitulo);
        descripcion = (EditText)findViewById(R.id.editDescripcion);
        txt_fecha = (TextView) findViewById(R.id.edit_date);
        monto = (EditText)findViewById(R.id.edtMonto);
        btn_crear = (Button)findViewById(R.id.nuevoEvent);
        String [] tiposEvents = new String[3];
        tiposEvents[0] = "Deportivo";
        tiposEvents[1] = "Musical";
        tiposEvents[2] = "Religioso";
        ArrayAdapter<String> adapterEvents = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tiposEvents);
        adapterEvents.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoEvento.setAdapter(adapterEvents);
        txt_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePicker();

            }
        });
        btn_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comprobarEspaciosLlenos()) {
                    String selectItm = tipoEvento.getSelectedItem().toString();
                    Intent intent;
                    if (selectItm == "Musical") {
                        intent = new Intent(crear_EventoActivity.this, CrearEvent_MusicalActivity.class);
                        int cod = Integer.parseInt(codigo.getText().toString());
                        intent.putExtra("Codigo", cod);
                        intent.putExtra("Titulo", titulo.getText().toString());
                        intent.putExtra("descripcion", descripcion.getText().toString());
                        intent.putExtra("fecha", ((DatePickerFragment) newFragment).getSc());
                        double mont = Double.parseDouble(monto.getText().toString());
                        intent.putExtra("monto", mont);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    Toast.makeText(crear_EventoActivity.this,"Porfavor llene todos los datos",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private boolean comprobarEspaciosLlenos(){
        if(codigo.length() > 0 && titulo.length() > 0 && descripcion.length() > 0 && txt_fecha.length()>0 && monto.length() > 0){
            return true;
        }
        else{
            return false;
        }
    }
    public DialogFragment newFragment;
    private void mostrarDatePicker(){
        newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
