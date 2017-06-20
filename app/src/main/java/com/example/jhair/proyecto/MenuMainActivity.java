package com.example.jhair.proyecto;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuMainActivity extends AppCompatActivity {
    Button btn_admin;
    Button btn_salir;
    Button btn_reporter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);
        initComponents();
    }

    public void initComponents(){
        btn_reporter = (Button)findViewById(R.id.btn_reportes);
        btn_reporter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuMainActivity.this,MenuReportes.class);
                startActivity(intent);
                finish();
            }
        });
        btn_salir = (Button)findViewById(R.id.btn_salir);
        btn_admin = (Button)findViewById(R.id.btn_eventos);
        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuMainActivity.this,admin_EventosActivity.class);
                startActivity(intent);
                finish();

            }
        });
        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuMainActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(MenuMainActivity.this,"Debe dar click en salir, si desea cerrar sesion",Toast.LENGTH_SHORT).show();
        Snackbar.make(findViewById(R.id.linear_MenuPrincipal),"click en salir, si desea cerrar sesion",Snackbar.LENGTH_SHORT)
                .setAction("Mas detalles", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();
    }
}
