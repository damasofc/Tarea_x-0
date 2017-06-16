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
    TextView txt_Dato1;
    TextView edit_Dato1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_evento);
        initComponents();
    }

    private void initComponents(){
        //INICIO: TODOS LOS COMPONENTES
        txt_Dato1 = (TextView) findViewById(R.id.txt_Dato1);
        edit_Dato1 = (TextView) findViewById(R.id.edit_Dato1);
        montoPagar = (Button)findViewById(R.id.montoPagar);
        descripcion = (TextView) findViewById(R.id.descripcionEvent);
        codigo_Evento = (TextView) findViewById(R.id.codigo_Evento);
        eventCancel = (TextView) findViewById(R.id.txt_EventCancel);
        btn_regresar = (ImageButton) findViewById(R.id.imageButton_regresar);
        tipoEvento = (TextView) findViewById(R.id.tipoEvento);
        fechaEvento = (TextView) findViewById(R.id.fechaEvento);
        tituloEvento = (TextView) findViewById(R.id.tituloEvent);
        layoutDatos = (LinearLayout)findViewById(R.id.layoutDatosEvent);
        //FINALIZA: TODOS LOS COMPONENTES
        btn_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatosEventoActivity.this, Ver_EventoActivity.class);
                startActivity(intent);
                finish();
            }
        });

        int codigo = getIntent().getExtras().getInt("codigo");
        final Evento eve = MainClass.buscarEvento(codigo) == null?MainClass.buscarEventoCancelado(codigo):MainClass.buscarEvento(codigo);
        setDatos(eve);
        if(MainClass.buscarEventoCancelado(codigo) != null){
            eventCancel.setText("Evento cancelado");
            if(eve instanceof EventoReligioso){
                montoPagar.setText("No hay cobro por ser evento religioso");
                montoPagar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DatosEventoActivity.this, "Se cancelo el evento, por ser religioso no hay ningun cobro", Toast.LENGTH_LONG).show();
                    }
                });

            }
            else{
                montoPagar.setText("Monto a pagar: "+"Lps. "+ MainClass.formatMontoPago(eve.getMontoPagar()));
                montoPagar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DatosEventoActivity.this,"Se cancelo el evento un dia antes, se paga el 50% de "+MainClass.formatMontoPago(eve.getMontoPagar()*2)+ " por indemnizacion",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
        else if(eve.getFecha().before(Calendar.getInstance())){
            eventCancel.setText("Evento ya realizado");
            if(eve instanceof EventoReligioso){
                txt_Dato1.setText("Personas convertidas: ");
                txt_Dato1.setTextSize(20);
                edit_Dato1.setText(String.valueOf(((EventoReligioso) eve).getPersonasConvertidas()));
                edit_Dato1.setTextSize(20);
            }
        }

    }

    private void setDatos(final Evento event){
        tituloEvento.setText(event.getTitulo());
        codigo_Evento.setText(String.valueOf(event.getCodigo()));
        fechaEvento.setText(event.getFechaString());
        descripcion.setText(event.getDescripcion());
        String pago = "Lps. "+ MainClass.formatMontoPago(event.getMontoPagar());
        montoPagar.setText("Monto a pagar: "+ pago);
        edit_Dato1.setText("");
        txt_Dato1.setText("");
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
            tipoEvento.setText("Deportivo - "+ ((EventoDeportivo) event).getTipoDeporte());
            txt_Dato1.setText("Equipos: ");
            edit_Dato1.setText(((EventoDeportivo) event).getEquipo1()+ " vrs "+((EventoDeportivo) event).getEquipo2());

        }
        else{
            tipoEvento.setText("Musical - "+((EventoMusical)event).getTipoMusica());
            txt_Dato1.setText("Ver Staff");
            txt_Dato1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            montoPagar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(DatosEventoActivity.this,"Se cobra Lps. 30% sobre "+(event.getMontoPagar()-(((EventoMusical) event).getSeguroGrama()))+" por seguro de Grama ",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DatosEventoActivity.this,Ver_EventoActivity.class);
        startActivity(intent);
        finish();
    }

}
