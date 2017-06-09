package com.example.jhair.proyecto;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jhair.proyecto.clases.Evento;
import com.example.jhair.proyecto.clases.EventoDeportivo;
import com.example.jhair.proyecto.clases.EventoMusical;
import com.example.jhair.proyecto.clases.EventoReligioso;

import org.w3c.dom.Text;

import java.util.Calendar;

public class DatosEventoActivity extends AppCompatActivity {
    LinearLayout layoutDatos;
    TextView tituloEvento;
    TextView tipoEvento;
    TextView fechaEvento;
    ImageButton btn_regresar;
    TextView eventCancel;
    TextView codigo_Evento;
    TextView descripcion;
    Button montoPagar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_evento);
        initComponents();
    }

    private void initComponents(){
        montoPagar = (Button)findViewById(R.id.montoPagar);
        descripcion = (TextView) findViewById(R.id.descripcionEvent);
        codigo_Evento = (TextView) findViewById(R.id.codigo_Evento);
        eventCancel = (TextView) findViewById(R.id.txt_EventCancel);
        btn_regresar = (ImageButton) findViewById(R.id.imageButton_regresar);
        tipoEvento = (TextView) findViewById(R.id.tipoEvento);
        fechaEvento = (TextView) findViewById(R.id.fechaEvento);
        btn_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatosEventoActivity.this, Ver_EventoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        tituloEvento = (TextView) findViewById(R.id.tituloEvent);
        layoutDatos = (LinearLayout)findViewById(R.id.layoutDatosEvent);
        int codigo = getIntent().getExtras().getInt("codigo");
        final Evento event = MainClass.buscarEvento(codigo) == null?MainClass.buscarEventoCancelado(codigo):MainClass.buscarEvento(codigo);
        tituloEvento.setText(event.getTitulo());
        codigo_Evento.setText(String.valueOf(event.getCodigo()));
        fechaEvento.setText(event.getFechaString());
        descripcion.setText(event.getDescripcion());
        String pago = "Lps. "+ MainClass.formatMontoPago(event.getMontoPagar());
        montoPagar.setText("Monto a pagar: "+ pago);
        if(MainClass.buscarEventoCancelado(codigo) != null){
            eventCancel.setText("Evento cancelado");
            if(event instanceof EventoReligioso){
                montoPagar.setText("No hay cobro por ser evento religioso");
                montoPagar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DatosEventoActivity.this,"Se cancelo el evento un dia antes, por ser religioso no hay ningun cobro",Toast.LENGTH_LONG).show();
                    }
                });
            }
            else{
                montoPagar.setText("Monto a pagar: "+pago);
                montoPagar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DatosEventoActivity.this,"Se cancelo el evento un dia antes, se paga el 50% de "+MainClass.formatMontoPago(event.getMontoPagar()*2)+ " por indemnizacion",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
        else if(event.getFecha().before(Calendar.getInstance())){
            eventCancel.setText("Evento ya realizado");
        }
        if(event instanceof EventoReligioso){
            montoPagar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(DatosEventoActivity.this,"Se cobran Lps. 2,000 mas, por el seguro de la grama:\n"+(event.getMontoPagar()-2000)+" + "+2000+"(Seguro) = Lps."+event.getMontoPagar(),Toast.LENGTH_LONG).show();
                }
            });
            tipoEvento.setText("Religioso");
        }
        else if(event instanceof EventoDeportivo){

            tipoEvento.setText("Deportivo");
        }
        else{
            tipoEvento.setText("Musical - "+((EventoMusical)event).getTipoMusica());
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DatosEventoActivity.this,Ver_EventoActivity.class);
        startActivity(intent);
        finish();
    }
}
