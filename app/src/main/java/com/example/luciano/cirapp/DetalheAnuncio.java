package com.example.luciano.cirapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class DetalheAnuncio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_anuncio);

        TextView titulo_anuncio = (TextView) findViewById(R.id.tv_titulo_anuncio);

        if(getIntent().hasExtra("TITULO_ANUNCIO")){
            Bundle extras = getIntent().getExtras();
            titulo_anuncio.setText(extras.getString("TITULO_ANUNCIO"));
        }
    }
}
