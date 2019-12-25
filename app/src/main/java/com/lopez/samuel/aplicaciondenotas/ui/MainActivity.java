package com.lopez.samuel.aplicaciondenotas.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lopez.samuel.aplicaciondenotas.R;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // desaparecer el toolbar
        getSupportActionBar().hide();

        // referencia del boton
        btnLogin = findViewById(R.id.buttonLogin);

        // evento click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pasar al siguiente activity
                Intent i = new Intent(MainActivity.this, DashbardActivity.class);
                startActivity(i);
            }
        });
    }
}
