package com.example.luciano.cirapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class DetalheAnuncio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_anuncio);

        TextView titulo_anuncio = (TextView) findViewById(R.id.tv_titulo_anuncio);
        TextView descricao_anuncio = (TextView) findViewById(R.id.txtAnuncioDescricao);
        TextView cidade_anuncio = (TextView) findViewById(R.id.txtAnuncioCidade);
       // Button editar_anuncio = (Button) findViewById(R.id.btnEditarDeDetalhe);
        //Button voltar_anuncio = (Button) findViewById(R.id.btnVoltarDeDetalhe);
        //Button contatar_anuncio = (Button) findViewById(R.id.btnContatar);

            Bundle extras = getIntent().getExtras();
            titulo_anuncio.setText(extras.getString("TITULO_ANUNCIO"));
            descricao_anuncio.setText(extras.getString("DESCRICAO_ANUNCIO"));
            if(extras.getBoolean("USER_ID_BOOLEAN")){
                cidade_anuncio.setText("MSM ID");
            }else{
                cidade_anuncio.setText("ID DIFERENTE");
            }
            //cidade_anuncio.setText(extras.getString("CIDADE_ANUNCIO"));
            decodeImg(extras.getString("IMG_ANUNCIO"));

    }
    public void decodeImg(String img) {

        ImageView image = (ImageView) findViewById(R.id.ivAnuncio);
        String imageString = img;
        byte[] decodedString = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        image.setImageBitmap(decodedImage);

    }
    public void voltarDeDetalhe(View view){
        startActivity(new Intent(getBaseContext(), ListaDeItens.class));
    }
    public void editarDeDetalhe(View view){

        Intent intent = new Intent(this, EditarAnuncio.class);
        //String id_usuario = Integer.toString(userId);
        //intent.putExtra("USER_ID_LOGIN", id_usuario);
        startActivity(intent);

        finish();

    }
    public void contatarDeDetalhe(){

    }
}
