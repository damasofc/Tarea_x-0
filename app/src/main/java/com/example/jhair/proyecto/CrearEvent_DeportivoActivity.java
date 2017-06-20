package com.example.jhair.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jhair.proyecto.Usuarios.Usuario;
import com.example.jhair.proyecto.Usuarios.UsuarioAdmin;
import com.example.jhair.proyecto.Usuarios.UsuarioNormal;
import com.example.jhair.proyecto.clases.Evento;
import com.example.jhair.proyecto.clases.EventoDeportivo;

import java.util.Calendar;

public class CrearEvent_DeportivoActivity extends AppCompatActivity {
    Spinner tipoDeporte;
    TextView tipDeporte;
    EditText equipo1;
    EditText equipo2;
    Button nuevoEvento;
    int fuente;
    int codigo;
    TextView titulo_Deporte;

    ViewGroup griLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_event__deportivo);
        initComponents();
    }
    private void initComponents(){
        titulo_Deporte = (TextView)findViewById(R.id.titulo_deportivo);
        codigo = getIntent().getExtras().getInt("Codigo");
        tipDeporte = (TextView)findViewById(R.id.tipoDeporte);
        griLayout = (ViewGroup)findViewById(R.id.griLayout);
        fuente = getIntent().getExtras().getInt("FUENTE");
        tipoDeporte = (Spinner)findViewById(R.id.tipo_Deporte);
        equipo1 = (EditText) findViewById(R.id.equipo1);
        equipo2 = (EditText)findViewById(R.id.equipo2);
        nuevoEvento = (Button)findViewById(R.id.nuevoEventDeport);
        EventoDeportivo.Deportes [] items = EventoDeportivo.Deportes.values();
        ArrayAdapter<EventoDeportivo.Deportes> adapterEvents = new ArrayAdapter<EventoDeportivo.Deportes>(this,android.R.layout.simple_spinner_item,items);
        adapterEvents.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoDeporte.setAdapter(adapterEvents);
        if(fuente == 2){
            nuevoEvento.setText("Guardar Equipos");
            griLayout.removeView(tipoDeporte);
            griLayout.removeView(tipDeporte);
            titulo_Deporte.setText("Editar equipos");
            equipo1.setText(((EventoDeportivo)MainClass.buscarEvento(codigo)).getEquipo1());
            equipo2.setText(((EventoDeportivo)MainClass.buscarEvento(codigo)).getEquipo2());

        }
        nuevoEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fuente == 2){
                    setEquipos(codigo);
                    Toast.makeText(CrearEvent_DeportivoActivity.this,"Equipos actualizados exitosamente",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CrearEvent_DeportivoActivity.this,EditarEvento.class);
                    intent.putExtra("codEvent",codigo);
                    startActivity(intent);
                    finish();
                }else {
                    //inicia,todos los atributos que vienen de crear_EventoActivity
                    String titulo = getIntent().getExtras().getString("Titulo");
                    String descript = getIntent().getExtras().getString("descripcion");
                    Calendar fecha = (Calendar) getIntent().getExtras().get("fecha");
                    double monto = getIntent().getExtras().getDouble("monto");
                    //Termina,todos los atributos que vienen de crear_EventoActivity
                    String tiposport = tipoDeporte.getSelectedItem().toString();
                    if (comprobarEspaciosLlenos()) {
                        if (MainClass.existeEvento(codigo) || MainClass.buscarEventoCancelado(codigo) != null) {
                            Toast.makeText(CrearEvent_DeportivoActivity.this, "Este codigo de evento ya existe", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CrearEvent_DeportivoActivity.this, crear_EventoActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            EventoDeportivo ed = new EventoDeportivo(codigo, titulo, fecha, monto, descript, tiposport, equipo1.getText().toString(), equipo2.getText().toString());
                            MainClass.añadirEvento(ed);
                            Usuario usu = MainActivity.usuarioActivo;
                            if(usu instanceof UsuarioAdmin){
                                UsuarioAdmin.agregarEventoAdmin(ed);
                            }else if(usu instanceof UsuarioNormal){
                                UsuarioNormal.agregarEventoNormal(ed);
                            }
                            Toast.makeText(CrearEvent_DeportivoActivity.this, "Evento creado exitosamente", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(CrearEvent_DeportivoActivity.this, MenuMainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Toast.makeText(CrearEvent_DeportivoActivity.this, "Porfavor llene todos los datos", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void setEquipos(int codigo){
        EventoDeportivo eve = (EventoDeportivo)MainClass.buscarEvento(codigo);
        eve.setEquipo1(equipo1.getText().toString());
        eve.setEquipo2(equipo2.getText().toString());

    }
    private boolean comprobarEspaciosLlenos(){
        if(equipo1.length() > 0 && equipo2.length() > 0){
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if(fuente == 1) {
            Intent intent = new Intent(CrearEvent_DeportivoActivity.this, crear_EventoActivity.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(CrearEvent_DeportivoActivity.this,EditarEvento.class);
            intent.putExtra("codEvent",codigo);
            startActivity(intent);
            finish();

        }
    }
}
