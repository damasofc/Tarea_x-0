package com.example.jhair.proyecto;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jhair.proyecto.Usuarios.UsuarioLimitado;
import com.example.jhair.proyecto.Usuarios.UsuarioNormal;
import com.example.jhair.proyecto.Usuarios.Validaciones;

import java.lang.reflect.Array;

public class Registro extends AppCompatActivity {

    private EditText usuario;
    private EditText contra;
    private EditText nomcompleto;
    private EditText edad;
    private Button registrarte;
    private Button regresar;
    private Spinner tipusuarios;

//Toast.makeText(Registro.this, "Normal", Toast.LENGTH_SHORT).show();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        initComponents();
    }

    private void initComponents(){
        tipusuarios=(Spinner)findViewById(R.id.spiusu);
        usuario=(EditText)findViewById(R.id.etnom);
        contra=(EditText)findViewById(R.id.etpassw);
        nomcompleto= (EditText)findViewById(R.id.etnomc);
        edad=(EditText)findViewById(R.id.etedad);
        registrarte=(Button)findViewById(R.id.btregist);
        regresar=(Button)findViewById(R.id.btregre);

        regresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro.this,MainActivity.class);
                startActivity(intent);

            }
        });

        final String tiposUsers[] = new String[2];
        tiposUsers[0]="Normal";
        tiposUsers[1]="Limitado";
        ArrayAdapter<String> adapterUsers  = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tiposUsers);
        adapterUsers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipusuarios.setAdapter(adapterUsers);

        registrarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ComprobarEspacios()){
                    String selectusu=tipusuarios.getSelectedItem().toString();

                    switch (selectusu) {

                        default:
                            if (Validaciones.existeuser(usuario.getText().toString())==true) {
                                Toast.makeText(Registro.this, "Este usuario ya existe, escriba otro", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Registro.this, "Se creo el usuario "+tipusuarios.getSelectedItem(), Toast.LENGTH_SHORT).show();
                            }
                            break;

                    }
                }else{
                    Toast.makeText(Registro.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
    private boolean ComprobarEspacios(){
        if(usuario.length()>0&&contra.length()>0&&nomcompleto.length()>0&&edad.length()>0){
            return true;
        }else {
            return false;
        }
    }
}
