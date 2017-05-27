package com.example.jhair.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jhair.proyecto.clases.Evento;
import com.example.jhair.proyecto.clases.EventoDeportivo;
import com.example.jhair.proyecto.clases.EventoMusical;

import java.util.Calendar;

public class Eliminar_EventoActivity extends AppCompatActivity {
    Button cancelar;
    EditText codigoCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar__evento);
        initComponents();
    }
    private void initComponents(){
        codigoCancel = (EditText) findViewById(R.id.codigoEvento);
        cancelar = (Button)findViewById(R.id.cancelEvento);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int codEvent = codigoCancel.length() > 0?Integer.parseInt(codigoCancel.getText().toString()):-1;
                if(MainClass.existeEvento(codEvent)){
                    Evento eve = MainClass.buscarEvento(codEvent);
                    Calendar hoy = Calendar.getInstance();
                    if(eve.getFecha().after(hoy)){
                        //en este if, reviso si el evento es evento musical o deportivo,
                        // porque si es Religioso, no se hace ningun cobro adicional al cancelarlo
                        if(eve instanceof EventoDeportivo || eve instanceof EventoMusical){
                            /*
                            Este gran IF, es el que comprueba si el dia que se quiere cancelar el evento, es un dia antes del evento, porque si es asi
                            se les cobra el 50% del monto establecido
                             */
                            if(eve.getFecha().get(Calendar.YEAR) == hoy.get(Calendar.YEAR) &&
                                    eve.getFecha().get(Calendar.MONTH) == hoy.get(Calendar.MONTH) &&
                                    (eve.getFecha().get(Calendar.DAY_OF_MONTH)-hoy.get(Calendar.DAY_OF_MONTH)== 1)){
                                eve.setMontoPagar(eve.getMontoPagar()/2);
                                Toast ts = Toast.makeText(Eliminar_EventoActivity.this,"Debera pagar el 50% por indemnizacion por cancelacion de un dia antes",Toast.LENGTH_LONG);
                                ts.setGravity(Gravity.TOP,0,0);
                                ts.show();

                            }
                        }
                        MainClass.eventosCancelados.add(eve);
                        MainClass.borrarEvento(codEvent);
                        Toast.makeText(Eliminar_EventoActivity.this,"El evento "+eve.getTitulo()+" ha sido cancelado",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Eliminar_EventoActivity.this,MenuMainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(Eliminar_EventoActivity.this,"El evento no puede ser cancelado porque ya paso",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Eliminar_EventoActivity.this,codEvent == -1?"Porfavor escriba un codigo de evento":"No hay ningun evento con ese codigo",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
