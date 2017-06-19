package com.example.jhair.proyecto;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jhair.proyecto.clases.Evento;
import com.example.jhair.proyecto.clases.EventoDeportivo;
import com.example.jhair.proyecto.clases.EventoMusical;
import com.example.jhair.proyecto.clases.EventoReligioso;

import java.util.Calendar;

public class EditarEvento extends AppCompatActivity {
    TextView tipoEdtEvento;
    EditText editarCode;
    EditText editarTitulo;
    EditText editarDescripcion;
    TextView editarDate;
    EditText editarMonto;
    Button editarEvent;
    TextView txtDato1;
    EditText editarDato1;
    TextView txtDato2;
    TextView txtDato3;
    TextView editarDato3;
    TextView editarDato2;
    Spinner tipMusic;
    ViewGroup grid;
    int codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_evento);
        initComponents();
    }
    Evento eve;
    private void initComponents(){
        txtDato3 = (TextView)findViewById(R.id.txtDato3);
        editarDato3 = (TextView)findViewById(R.id.editarDato3);
        grid = (ViewGroup)findViewById(R.id.gridEdtLayout);
        tipoEdtEvento = (TextView)findViewById(R.id.tipoEdtEvento);
        editarDate = (TextView)findViewById(R.id.editarDate);
        editarCode = (EditText)findViewById(R.id.editarCode);
        editarTitulo = (EditText)findViewById(R.id.editarTitulo);
        editarDescripcion = (EditText)findViewById(R.id.editarDescripcion);
        editarMonto = (EditText)findViewById(R.id.editarMonto);
        editarEvent = (Button)findViewById(R.id.editarEvent);
        txtDato1 = (TextView)findViewById(R.id.txtDato1);
        editarDato1 = (EditText) findViewById(R.id.editarDato1);
        txtDato2 = (TextView)findViewById(R.id.txtDato2);
        editarDato2 = (TextView)findViewById(R.id.editarDato2);
        codigo = (Integer) getIntent().getExtras().get("codEvent");
        eve = MainClass.buscarEvento(codigo);
        setDatos(eve,1);
        editarDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePicker();
            }
        });
        editarEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarDatos(eve);
                Toast.makeText(EditarEvento.this,"Datos Actualizados",Toast.LENGTH_LONG).show();
                setDatos(eve,2);

            }
        });
        if(eve instanceof EventoMusical){
            editarDato2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EditarEvento.this, ViewStaff.class);
                    intent.putExtra("codigoEvento",eve.getCodigo());
                    intent.putExtra("FUENTE",1);
                    startActivity(intent);
                    finish();
                }
            });
        }
        else if(eve instanceof EventoDeportivo){
            editarDato2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EditarEvento.this,EditarJugadores.class);
                    intent.putExtra("codEvent",codigo);
                    startActivity(intent);
                    finish();

                }
            });
            editarDato3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EditarEvento.this,CrearEvent_DeportivoActivity.class);
                    intent.putExtra("FUENTE",2);
                    intent.putExtra("Codigo",codigo);
                    startActivity(intent);
                    finish();
                }
            });
        }


    }
    private void actualizarDatos(Evento e){
        int codigoEvent = (Integer) getIntent().getExtras().get("codEvent");
        int cod = Integer.valueOf(editarCode.getText().toString());
        String title = editarTitulo.getText().toString();
        String descripcion = editarDescripcion.getText().toString();
        Calendar date;
        if(e.getFechaString().equals(editarDate.getText().toString())){}
        else{
            date = ((DatePickerFragment) nwFragment).getSc();
            e.setFecha(date);
        }
        double monto = Double.parseDouble(editarMonto.getText().toString());
        if(cod == codigoEvent){
            e.setCodigo(cod);
        }else if(MainClass.buscarEvento(cod) != null || MainClass.buscarEventoCancelado(cod) != null) {
            Toast.makeText(EditarEvento.this,"Este codigo introducido ya existe",Toast.LENGTH_LONG).show();
        }else{
            e.setCodigo(cod);
        }
        e.setTitulo(title);
        e.setDescripcion(descripcion);
        e.setMontoPagar(monto);
        if(e instanceof EventoReligioso && e.getFecha().before(Calendar.getInstance())){
            try {
                int personasConver = Integer.valueOf(editarDato1.getText().toString());
                ((EventoReligioso) e).setPersonasConvertidas(personasConver);
            }catch (Exception ex){
                ((EventoReligioso) e).setPersonasConvertidas(0);
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EditarEvento.this,Eliminar_EventoActivity.class);
        intent.putExtra("FUENTE",1);
        startActivity(intent);
        finish();
    }
    private void setDatos(Evento e, int fuente){
        editarCode.setText(String.valueOf(e.getCodigo()));
        editarTitulo.setText(e.getTitulo());
        editarDescripcion.setText(e.getDescripcion());
        editarDate.setText(e.getFechaString());
        editarMonto.setText(String.valueOf(e.getMontoPagar()));
        txtDato1.setText(" ");
        editarDato1.setText(" ");
        editarDato1.setEnabled(false);
        if(e instanceof EventoDeportivo){
            tipoEdtEvento.setText("DEPORTIVO");
            txtDato1.setText("Deporte: ");
            txtDato2.setText("Jugadores: ");
            editarDato2.setText("Editar Jugadores");
            txtDato3.setText("Editar Equipos:");
            editarDato3.setText("Ir a equipos");
            if(fuente == 1) {
                grid.removeView(editarDato1);
                tipMusic = new Spinner(EditarEvento.this);
                grid.addView(tipMusic, grid.getChildCount() - 5);
                //INICIO: datos para spinner
                EventoDeportivo.Deportes [] items = EventoDeportivo.Deportes.values();
                ArrayAdapter<EventoDeportivo.Deportes> adapterEvents = new ArrayAdapter<EventoDeportivo.Deportes>(this, android.R.layout.simple_spinner_item, items);
                adapterEvents.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                tipMusic.setAdapter(adapterEvents);
                //FIN: datos para spinner
                tipMusic.setSelection(setTipoDeporte(((EventoDeportivo) e).getTipoDeporte()));
                tipMusic.setLayoutParams(new GridLayout.LayoutParams(editarDato1.getLayoutParams()));
            }else{
                ((EventoDeportivo) e).setTipoDeporte(tipMusic.getSelectedItem().toString());
            }
        }
        else if(e instanceof EventoMusical){
            tipoEdtEvento.setText("MUSICAL");
            editarMonto.setText(String.valueOf(e.getMontoPagar()-((EventoMusical) e).getSeguroGrama()));
            txtDato1.setText("Tipo de Musica: ");
            txtDato2.setText("Staff: ");
            editarDato2.setText("Editar Staff");
            if(fuente == 1) {
                grid.removeView(editarDato1);
                tipMusic = new Spinner(EditarEvento.this);
                grid.addView(tipMusic, grid.getChildCount() - 3);
                //INICIO: datos para spinner
                EventoMusical.Musica[] items = EventoMusical.Musica.values();
                ArrayAdapter<EventoMusical.Musica> adapterEvents = new ArrayAdapter<EventoMusical.Musica>(this, android.R.layout.simple_spinner_item, items);
                adapterEvents.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                tipMusic.setAdapter(adapterEvents);
                //FIN: datos para spinner
                tipMusic.setSelection(setTipoMusic(((EventoMusical) e).getTipoMusica()));
                tipMusic.setLayoutParams(new GridLayout.LayoutParams(editarDato1.getLayoutParams()));
            }else{
                ((EventoMusical) e).setTipoMusica(tipMusic.getSelectedItem().toString());
            }
        }
        else{
            tipoEdtEvento.setText("RELIGIOSO");
            editarMonto.setText(String.valueOf(e.getMontoPagar()-2000));
            if(e.getFecha().before(Calendar.getInstance())) {
                editarDato1.setEnabled(true);
                txtDato1.setText("Convertidos: ");
                editarDato1.setText(String.valueOf(((EventoReligioso)e).getPersonasConvertidas()));
            }
        }
    }
    public DialogFragment nwFragment;
    private void mostrarDatePicker(){
        nwFragment = new DatePickerFragment();
        nwFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private int setTipoMusic(EventoMusical.Musica tipo){
        for(int i = 0 ; i < tipMusic.getCount(); i ++ ){
            String item = tipMusic.getItemAtPosition(i).toString();
            if(item.equals(tipo.toString())){
                return i;
            }
        }
        return -1;
    }
    private int setTipoDeporte(EventoDeportivo.Deportes tipo){
        for(int i = 0 ; i < tipMusic.getCount(); i ++ ){
            String item = tipMusic.getItemAtPosition(i).toString();
            if(item.equals(tipo.toString())){
                return i;
            }
        }
        return -1;
    }
}
