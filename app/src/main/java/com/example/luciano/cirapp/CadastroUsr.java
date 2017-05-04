package com.example.luciano.cirapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.luciano.cirapp.api.CirService;
import com.example.luciano.cirapp.model.CidadePorEstado;
import com.example.luciano.cirapp.model.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastroUsr extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtCpfCnpj;
    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnGravarUsr;
    Spinner spnEstado;
    List<String> cidadesNoRealmList = new ArrayList<String>();
    int categoriaPosition;
    String[] estadoList = {"Estado","AC","AL","AM","AP","BA","CE","DF","ES","GO","MA","MG","MS","MT","PA","PB",
            "PE","PI","PR","RJ","RN","RO","RR","RS","SC","SE","SP","TO"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usr);

        ArraySimplesEstado();

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtCpfCnpj = (EditText) findViewById(R.id.edtCpfCnpj);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnGravarUsr = (Button) findViewById(R.id.btnGravarUsr);

        btnGravarUsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = edtNome.getText().toString();
                String cpf_cnpj = edtCpfCnpj.getText().toString();
                //String cidade = edtCidade.getText().toString();
                //int cidade_id = Integer.parseInt(cidade);
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(CirService.URL_BASE)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                CirService service = retrofit.create(CirService.class);
                Usuario u = new Usuario(nome,cpf_cnpj,1292,email,senha);//Corrigir o id

                Call<Integer> user = service.inserirUsuario(u);
                user.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Inserido com sucesso!",Toast.LENGTH_LONG).show();
                            //Integer usuario_id = response.body();
                            startActivity(new Intent(getBaseContext(), MainActivity.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "Erro ao inserir!",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Falha no onFailure!",Toast.LENGTH_LONG).show();
                        Log.i("JONY", "FALHOUUUUUUUUU onfailure");
                    }
                });

            }
        });

    }
    public void popularSpinnerCidades(){

        spnEstado = (Spinner) findViewById(R.id.spnCidade);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cidadesNoRealmList);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnEstado.setAdapter(spinnerArrayAdapter);
        spnEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nome = parent.getSelectedItem().toString();
                Toast.makeText(getBaseContext(), "Cidade Selecionada: " + nome, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void pegarArrayCidades(String estado){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cirapi.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CirService service = retrofit.create(CirService.class);
        Call<List<CidadePorEstado>> cidadesCall = service.getCidadePorEstado(estado);
        cidadesCall.enqueue(new Callback<List<CidadePorEstado>>() {
            @Override
            public void onResponse(Call<List<CidadePorEstado>> call, Response<List<CidadePorEstado>> response) {

                List<CidadePorEstado> cpe = response.body();

                for(CidadePorEstado cid : cpe){
                    cidadesNoRealmList.add(cid.getCidade1().toString());
                    //cidadesNoRealmList.add(""+cid.getCidade_id());
                    popularSpinnerCidades();
                    Log.i("JONY", ""+cid.getCidade_id());
                }
            }

            @Override
            public void onFailure(Call<List<CidadePorEstado>> call, Throwable t) {

            }
        });
    }
    public void ArraySimplesEstado(){

        spnEstado = (Spinner) findViewById(R.id.spnUf);
        ArrayAdapter<String> spinCategAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,
                estadoList);
        spnEstado.setAdapter(spinCategAdapter);
        spnEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String nome = parent.getSelectedItem().toString();
                Toast.makeText(getBaseContext(), nome, Toast.LENGTH_SHORT).show();
                cidadesNoRealmList.clear();
                pegarArrayCidades(nome);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
