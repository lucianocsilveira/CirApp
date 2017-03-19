package com.example.luciano.cirapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Uma vez modificado o anúncio, atualizar a data!
 */

public class EditarAnuncio extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinCategoria;
    String[] categoriaList = {"Categoria", "Plástico", "Vidro", "Borracha", "Papel", "Óleo", "Eletrônicos"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_anuncio);

        spinCategoria = (Spinner) findViewById(R.id.spinCategoria);

        ArrayAdapter<String> spinCategAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,
                categoriaList);

        spinCategoria.setAdapter(spinCategAdapter);
        spinCategoria.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
