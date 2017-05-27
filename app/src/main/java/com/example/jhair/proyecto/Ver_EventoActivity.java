package com.example.jhair.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jhair.proyecto.clases.Evento;

public class Ver_EventoActivity extends AppCompatActivity {
    Button verEvent;
    EditText codigoEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__evento);
        initComponents();
    }
    private void initComponents(){
        verEvent = (Button) findViewById(R.id.btn_ver);
        codigoEvent = (EditText) findViewById(R.id.codigoVer);
        verEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int codigoVer = codigoEvent.length() > 0?Integer.parseInt(codigoEvent.getText().toString()):-1;
                if(MainClass.existeEvento(codigoVer)){
                    Intent intent = new Intent(Ver_EventoActivity.this,DatosEventoActivity.class);
                    intent.putExtra("codigo",codigoVer);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(Ver_EventoActivity.this,codigoVer == -1? "Porfavor escriba un codigo de evento":"Este evento no existe, porfavor escriba un codigo de evento existente",Toast.LENGTH_LONG).show();
                    codigoEvent.setText("");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Ver_EventoActivity.this,admin_EventosActivity.class);
        startActivity(intent);
        finish();

    }
}
