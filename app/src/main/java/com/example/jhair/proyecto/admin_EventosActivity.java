package com.example.jhair.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin_EventosActivity extends AppCompatActivity {
    Button btn_crear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__eventos);
        initComponents();
    }

    public void initComponents() {
        btn_crear = (Button) findViewById(R.id.btn_crear);
        btn_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_EventosActivity.this, crear_EventoActivity.class);
                startActivity(intent);

            }
        });
    }
}
