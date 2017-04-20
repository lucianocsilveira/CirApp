package com.example.luciano.cirapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.luciano.cirapp.api.CirService;
import com.example.luciano.cirapp.model.CadAnuncio;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastroAnuncioActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinCategoria;
    int categoriaPosition;
    EditText edtTitulo;
    EditText edtDesc;
    Button btnSalvarAnuncio;
    String[] categoriaList = {"Categoria", "Plástico", "Vidro", "Borracha", "Papel", "Óleo", "Eletrônicos"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_anuncio);

        spinCategoria = (Spinner) findViewById(R.id.spinCategoria);
        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtDesc = (EditText) findViewById(R.id.edtDesc);
        btnSalvarAnuncio = (Button) findViewById(R.id.btnSalvarAnuncio);


        ArrayAdapter<String> spinCategAdapter = new ArrayAdapter<String>(this,
                                                    R.layout.support_simple_spinner_dropdown_item,
                                                    categoriaList);

        spinCategoria.setAdapter(spinCategAdapter);
        spinCategoria.setOnItemSelectedListener(this);


        btnSalvarAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int categoria = categoriaPosition;
                String titulo = edtTitulo.getText().toString();
                String descricao = edtDesc.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(CirService.URL_BASE)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                CirService service = retrofit.create(CirService.class);
                CadAnuncio a = new CadAnuncio(titulo,descricao,11,categoria);

                Call<Integer> anunc = service.inserirAnuncio(a);
                anunc.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {

                        if(response.isSuccessful()){
                            Toast.makeText(getBaseContext(), "Cadastrou", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getBaseContext(), ListaDeItens.class));
                        }

                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Toast.makeText(getBaseContext(), "ONFAILURE", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getBaseContext(), ""+position, Toast.LENGTH_SHORT).show();
        categoriaPosition = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void btnEdit(View view){
        Intent editar = new Intent(this, EditarAnuncio.class);
        startActivity(editar);
    }
}
