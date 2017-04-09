package com.example.luciano.cirapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luciano.cirapp.api.CirService;
import com.example.luciano.cirapp.api.ServiceGenerator;
import com.example.luciano.cirapp.model.RespostaLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText edtUser;
    private EditText edtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuario = edtUser.getText().toString();
                String senha = edtPassword.getText().toString();

                CirService service = ServiceGenerator.createService(CirService.class);

                Call<RespostaLogin> call = service.respostaLogin("password", senha,usuario);
                call.enqueue(new Callback<RespostaLogin>() {
                    @Override
                    public void onResponse(Call<RespostaLogin> call, Response<RespostaLogin> response) {

                        if(response.isSuccessful()){
                            startActivity(new Intent(getBaseContext(), ListaDeItens.class));
                        }else{
                            Toast.makeText(getBaseContext(), "Erro ao Logar", Toast.LENGTH_SHORT).show();
                        }

                        Log.i("ONRESPONSE", ""+response.isSuccessful());
                    }

                    @Override
                    public void onFailure(Call<RespostaLogin> call, Throwable t) {
                        Log.i("ONFAILURE", t.getMessage());

                    }
                });

            }
        });


    }

    public void cadastrarUsuario(View view){
        Intent cadastroUsuario = new Intent(this, CadastroUsr.class);
        startActivity(cadastroUsuario);
    }

    public void login (View view){


        Intent irParaLista = new Intent(this,ListaDeItens.class);
        startActivity(irParaLista);
    }
}
