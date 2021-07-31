package com.ismaias.aplicacinejemploicono;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Bienvenida extends AppCompatActivity {

    private TextView nombrecillo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        nombrecillo = findViewById(R.id.textoSuperX);

        String data = getIntent().getExtras().getString("esteEsUsuario","Chale, no hay nombre");
        nombrecillo.setText(data);


    }
}