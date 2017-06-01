package com.example.luciano.cirapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
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
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Uma vez modificado o anúncio, atualizar a data!
 */

public class EditarAnuncio extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinCategoria;
    String[] categoriaList = {"Categoria", "Plástico", "Vidro", "Borracha", "Papel", "Óleo", "Eletrônicos"};
    ImageView mImageView;
    private static final int PICK_IMAGE = 200;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_anuncio);

        mImageView = (ImageView) findViewById((R.id.imgEditarAnuncio));
        spinCategoria = (Spinner) findViewById(R.id.spinCategoria);

        ArrayAdapter<String> spinCategAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,
                categoriaList);

        spinCategoria.setAdapter(spinCategAdapter);
        spinCategoria.setOnItemSelectedListener(this);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executarImagem();
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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

                String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                Log.i("JONY", encoded.toString());


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
