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

import com.example.jhair.proyecto.Usuarios.Usuario;
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
                finish();

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
                String usuario1=usuario.getText().toString();
                String contra1=contra.getText().toString();
                String nombre1=nomcompleto.getText().toString();
                int edad1=Integer.valueOf(edad.getText().toString());


            if(usuario.getText().toString().equals("")||contra.getText().toString().equals("")||
                        nomcompleto.getText().toString().equals("")||edad.getText().toString().equals("")){
                Toast.makeText(Registro.this,"Debe llenar todos los campos",Toast.LENGTH_LONG).show();

            }else {
                if(Validaciones.comprobarUsuario(usuario.getText().toString())){
                    Toast.makeText(Registro.this,"Usuario ya en uso,escriba otro",Toast.LENGTH_LONG).show();

                }else{
                    String tipo=tipusuarios.getSelectedItem().toString();
                    switch (tipo){
                        case "Normal":
                            UsuarioNormal usuarioN =new UsuarioNormal(usuario1,contra1,nombre1,edad1);
                            Validaciones.addUsuario(usuarioN);
                            Toast.makeText(Registro.this,"A creado un Usuario Normal",Toast.LENGTH_LONG).show();

                            break;

                        case "Limitado":
                            UsuarioLimitado usuarioL = new UsuarioLimitado(usuario1,contra1,nombre1,edad1);
                            Validaciones.addUsuario(usuarioL);
                            Toast.makeText(Registro.this,"A creado un usuario Limitado",Toast.LENGTH_LONG).show();

                            break;

                    }
                    Intent intent = new Intent(Registro.this,MainActivity.class);
                    startActivity(intent);
                    finish();



                }
            }






            }
        });

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Registro.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
