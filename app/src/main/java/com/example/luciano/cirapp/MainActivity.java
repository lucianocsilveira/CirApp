package com.example.luciano.cirapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cadastrarUsuario(View view){
        Intent cadastroUsuario = new Intent(this, CadastroUsr.class);
        startActivity(cadastroUsuario);
    }

    public void btnCadAnuncio(View view) {
        Intent intent = new Intent(this, CadastroAnuncioActivity.class);
        startActivity(intent);
    }
}
