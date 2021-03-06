package com.example.luciano.cirapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.luciano.cirapp.api.CirService;
import com.example.luciano.cirapp.model.CadAnuncio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
    String imagemEncoded;
    Button btnSalvarAnuncio;
    String[] categoriaList = {"Categoria", "Plástico", "Vidro", "Borracha", "Papel", "Óleo", "Eletrônicos"};
    ImageView mImageView;
    private static final int PICK_IMAGE = 200;
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_anuncio);

        spinCategoria = (Spinner) findViewById(R.id.spinCategoria);
        edtTitulo = (EditText) findViewById(R.id.edtCadastrarTitulo);
        edtDesc = (EditText) findViewById(R.id.edtCadastrarDescricao);
        btnSalvarAnuncio = (Button) findViewById(R.id.btnSalvarAnuncio);
        mImageView = (ImageView) findViewById(R.id.imgCadAnuncio);


        ArrayAdapter<String> spinCategAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,
                categoriaList);

        spinCategoria.setAdapter(spinCategAdapter);
        spinCategoria.setOnItemSelectedListener(this);


        btnSalvarAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*int categoria = categoriaPosition;
                String titulo = edtTitulo.getText().toString();
                String descricao = edtDesc.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(CirService.URL_BASE)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                CirService service = retrofit.create(CirService.class);
                CadAnuncio a = new CadAnuncio(categoria,"",11,descricao,titulo);

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
                });*/
                //cadastrarAnuncio();
                Log.i("JONY", imagemEncoded.toString());

            }
        });
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executarImagem();
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
    public void cadastrarAnuncio(){
        int categoria = categoriaPosition;
        String titulo = edtTitulo.getText().toString();
        String descricao = edtDesc.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CirService.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CirService service = retrofit.create(CirService.class);
        CadAnuncio a = new CadAnuncio(categoria,"",11,descricao,titulo);

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

    public void btnEdit(View view){
        Intent editar = new Intent(this, EditarAnuncio.class);
        startActivity(editar);
    }

    public void executarImagem(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();

            Log.i("TESTE", imageUri.toString());
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(imageUri.toString()));
                mImageView.setImageBitmap(bitmap);

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream .toByteArray();

                imagemEncoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                //Log.i("JONY", imagemEncoded.toString());


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
