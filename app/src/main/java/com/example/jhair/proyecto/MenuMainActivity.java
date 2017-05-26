package com.example.jhair.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuMainActivity extends AppCompatActivity {
    Button btn_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);
        initComponents();
    }

    public void initComponents(){
        btn_admin = (Button)findViewById(R.id.btn_eventos);
        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuMainActivity.this,admin_EventosActivity.class);
                startActivity(intent);

            }
        });
    }
}
