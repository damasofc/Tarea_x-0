package com.example.jhair.proyecto;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jhair.proyecto.clases.Evento;
import com.example.jhair.proyecto.clases.EventoDeportivo;
import com.example.jhair.proyecto.clases.EventoMusical;

public class EditarEvento extends AppCompatActivity {
    TextView tipoEdtEvento;
    EditText editarCode;
    EditText editarTitulo;
    EditText editarDescripcion;
    TextView editarDate;
    EditText editarMonto;
    Button editarEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_evento);
        initComponents();
    }
    private void initComponents(){
        tipoEdtEvento = (TextView)findViewById(R.id.tipoEdtEvento);
        editarDate = (TextView)findViewById(R.id.editarDate);
        editarCode = (EditText)findViewById(R.id.editarCode);
        editarTitulo = (EditText)findViewById(R.id.editarTitulo);
        editarDescripcion = (EditText)findViewById(R.id.editarDescripcion);
        editarMonto = (EditText)findViewById(R.id.editarMonto);
        editarEvent = (Button)findViewById(R.id.editarEvent);
        int codigo = (Integer) getIntent().getExtras().get("codEvent");
        Evento eve = MainClass.buscarEvento(codigo);
        setDatos(eve);
        editarDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePicker();
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EditarEvento.this,Eliminar_EventoActivity.class);
        intent.putExtra("FUENTE",1);
        startActivity(intent);
        finish();
    }
    private void setDatos(Evento e){
        editarCode.setText(String.valueOf(e.getCodigo()));
        editarTitulo.setText(e.getTitulo());
        editarDescripcion.setText(e.getDescripcion());
        editarDate.setText(e.getFechaString());
        editarMonto.setText(String.valueOf(e.getMontoPagar()));
        if(e instanceof EventoDeportivo){
            tipoEdtEvento.setText("DEPORTIVO");
        }
        else if(e instanceof EventoMusical){
            tipoEdtEvento.setText("MUSICAL");
        }
        else{
            tipoEdtEvento.setText("RELIGIOSO");
        }
    }
    public DialogFragment newFragment;
    private void mostrarDatePicker(){
        newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
