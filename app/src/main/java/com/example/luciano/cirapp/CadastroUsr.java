package com.example.luciano.cirapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luciano.cirapp.api.CirService;
import com.example.luciano.cirapp.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastroUsr extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtCpfCnpj;
    private EditText edtUf;
    private EditText edtCidade;
    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnGravarUsr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usr);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtCpfCnpj = (EditText) findViewById(R.id.edtCpfCnpj);
        //edtUf = (EditText) findViewById(R.id.edtUf);
        //edtCidade = (EditText) findViewById(R.id.edtCidade);
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
                Usuario u = new Usuario(nome,cpf_cnpj,11,email,senha);//Corrigir o id

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


}
