package com.example.luciano.cirapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastroAnuncioActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinCategoria;
    String[] categoriaList = {"Categoria", "Plástico", "Vidro", "Borracha", "Papel", "Óleo", "Eletrônicos"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_anuncio);

        spinCategoria = (Spinner) findViewById(R.id.spinCategoria);

        ArrayAdapter<String> spinCategAdapter = new ArrayAdapter<String>(this,
                                                    R.layout.support_simple_spinner_dropdown_item,
                                                    categoriaList);

        spinCategoria.setAdapter(spinCategAdapter);
        spinCategoria.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getBaseContext(), ""+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void btnDetalhes(View view){
        Intent detalhes = new Intent(this, DetalheAnuncio.class);
        startActivity(detalhes);
    }

    public void btnEdit(View view){
        Intent editar = new Intent(this, EditarAnuncio.class);
        startActivity(editar);
    }
}
