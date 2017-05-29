package com.example.jhair.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button entrar;
    Button registrate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }
    private void initComponents(){
        entrar = (Button)findViewById(R.id.button2);
        registrate =(Button)findViewById(R.id.button);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MenuMainActivity.class);
                startActivity(intent);

            }
        });


        registrate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent hola = new Intent(MainActivity.this,Registro.class);
                startActivity(hola);

            }


        });

    }

}
