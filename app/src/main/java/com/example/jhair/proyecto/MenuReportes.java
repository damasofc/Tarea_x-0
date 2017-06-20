package com.example.jhair.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuReportes extends AppCompatActivity {
    Button ButtonListRealizados, ButtonListFuture, ButtonCancelEvent, ButtonDateInput, ButtonProfile,
            ButtonReturnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_reportes);
        initComponents();
    }

    public void initComponents() {
        ButtonListRealizados = (Button) findViewById(R.id.button_list_events);
        ButtonListFuture = (Button) findViewById(R.id.button_list_future_events);
        ButtonCancelEvent = (Button) findViewById(R.id.button_canceled_events);
        ButtonDateInput = (Button) findViewById(R.id.button_date_input);
        ButtonProfile = (Button) findViewById(R.id.button_profile);
        ButtonReturnMenu = (Button) findViewById(R.id.button_return_menu);

        //a partir de aquí pondré lo que hacen los botones xD

        ButtonListRealizados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuReportes.this, EventosRealizadosActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ButtonListFuture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuReportes.this, EventosFuturosActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ButtonReturnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuReportes.this, MenuMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
