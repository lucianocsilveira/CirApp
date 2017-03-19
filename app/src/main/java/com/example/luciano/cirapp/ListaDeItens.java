package com.example.luciano.cirapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Quando um dos anúncios for clicado, esta activity deve decidir
 * se chama "Detalhes do Anúncio" caso seja um visitante ou
 * "EditarAnuncio, se for o autor o usuário em ação
 */

public class ListaDeItens extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_itens);

    }

    public void btnNovoAnuncio(View view){
        Intent novoAnuncio = new Intent(this, CadastroAnuncioActivity.class);
        startActivity(novoAnuncio);
    }
}
