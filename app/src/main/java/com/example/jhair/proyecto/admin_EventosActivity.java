package com.example.jhair.proyecto;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin_EventosActivity extends AppCompatActivity {
    FloatingActionButton btn;
    Button btn_eliminar;
    Button btn_Editar;
    Button btn_ver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__eventos);
        initComponents();
    }

    public void initComponents() {
        btn = (FloatingActionButton) findViewById(R.id.fab_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_EventosActivity.this, crear_EventoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_eliminar = (Button) findViewById(R.id.btn_eliminarEvent);
        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_EventosActivity.this,Eliminar_EventoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_Editar = (Button)findViewById(R.id.btn_editar);
        btn_Editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Este intent me dirige a la activity Eliminar evento, pero en esa actividad tengo un if
                para comprobar cual es la fuente del parametro que le mando, si es 1 entonce viene del boton editar y cambia
                los textos
                 */
                Intent intent = new Intent(admin_EventosActivity.this,Eliminar_EventoActivity.class);
                intent.putExtra("FUENTE",1);
                startActivity(intent);
                finish();

            }
        });
        btn_ver = (Button) findViewById(R.id.btn_verEvento);
        btn_ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_EventosActivity.this, Ver_EventoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(admin_EventosActivity.this,MenuMainActivity.class);
        startActivity(intent);
        finish();
    }
}
