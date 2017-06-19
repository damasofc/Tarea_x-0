package com.example.jhair.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jhair.proyecto.Usuarios.Usuario;
import com.example.jhair.proyecto.Usuarios.Validaciones;

public class MainActivity extends AppCompatActivity {
    Button entrar;
    Button registrate;
    TextView nombresi;
    TextView passworde;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }
    private void initComponents(){
        Usuario nw = new Usuario("admin","supersecreto","Administrador",20);
        Validaciones.usuarioss.add(nw);
        entrar = (Button)findViewById(R.id.button2);
        registrate =(Button)findViewById(R.id.button);
        nombresi =(TextView)findViewById(R.id.txtnombi);
        passworde =(TextView)findViewById(R.id.txtpaswwi);


        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombresi.getText().toString().equals("")&&passworde.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Usuario o contrasena incorrectas",Toast.LENGTH_LONG).show();
                }else{


                Intent intent = new Intent(MainActivity.this, MenuMainActivity.class);
                startActivity(intent);
                finish();}

            }
        });


        registrate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent hola = new Intent(MainActivity.this,Registro.class);
                startActivity(hola);
                finish();

            }


        });

    }

}
