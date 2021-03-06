package com.example.luciano.cirapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.luciano.cirapp.adapter.RecyclerAnuncioAdapter;
import com.example.luciano.cirapp.api.CirService;
import com.example.luciano.cirapp.model.Anuncio;
import com.example.luciano.cirapp.util.ClickRecyclerView_Interface;
import com.example.luciano.cirapp.util.RecyclerItemClickListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Quando um dos anúncios for clicado, esta activity deve decidir
 * se chama "Detalhes do Anúncio" caso seja um visitante ou
 * "EditarAnuncio, se for o autor o usuário em ação
 */

public class ListaDeItens extends AppCompatActivity implements ClickRecyclerView_Interface {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerAnuncioAdapter adapter;
    private List<Anuncio> anunciosListas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_itens);

        setaRecyclerView();

    }

    public void setaRecyclerView(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CirService.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CirService service = retrofit.create(CirService.class);
        Call<List<Anuncio>> anuncios = service.getAllAnuncios();
        anuncios.enqueue(new Callback<List<Anuncio>>() {
            @Override
            public void onResponse(Call<List<Anuncio>> call, Response<List<Anuncio>> response) {
                List<Anuncio> anuncioList = response.body();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                //Log.i("JONY", anuncioList.get(1).getImagem());

                for (Anuncio a : anuncioList){

                    Anuncio anuncio = new Anuncio();

                    if(a.getDescricao() == null || a.getImagem() == null){
                        anuncio.setDescricao(null);
                        anuncio.setImagem(imageString);
                    }else{
                        anuncio.setDescricao(a.getDescricao().toString());
                        anuncio.setImagem(a.getImagem().toString());
                    }

                    anuncio.setTitulo(a.getTitulo().toString());
                    anuncio.setData(a.getData().toString());
                    anuncio.setCidade(a.getCidade().toString());
                    anuncio.setId(a.getId());
                    anuncio.setUsuariO_ID(a.getUsuariO_ID());

                    anunciosListas.add(anuncio);
                    adapter.notifyDataSetChanged();//Eis o segredo
                }
            }

            @Override
            public void onFailure(Call<List<Anuncio>> call, Throwable t) {
                Log.i("OnFailure", t.getMessage());
            }
        });
        //Aqui é instanciado o Recyclerview
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_recyclerteste);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new RecyclerAnuncioAdapter(this, anunciosListas, this);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position){
                Toast.makeText(getBaseContext(),anunciosListas.get(position).getTitulo().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //position += 1;
                String id_usuario = Integer.toString(anunciosListas.get(position).getUsuariO_ID());
                boolean editarAnuncio = false;

                //TENHO QUE CRIAR UMA SHAREDPREFERENCES EM VEZ DESSES INTENTS PARA SALVAR OS IDS
                Bundle extras = getIntent().getExtras();
                Log.i("TESTE DO ID", extras.getString("USER_ID_LOGIN"));

                if(extras.getString("USER_ID_LOGIN").equals(id_usuario)){
                    editarAnuncio = true;
                }else{
                    Log.i("TESTE DO ID", "NEGATIVO");
                }

                //Toast.makeText(getBaseContext(),"teste = "+ position, Toast.LENGTH_SHORT).show();
                ChamarSelecActivity(anunciosListas.get(position).getTitulo(),
                                    anunciosListas.get(position).getDescricao(),
                                    anunciosListas.get(position).getImagem(),
                                    anunciosListas.get(position).getCidade(),
                                    editarAnuncio);

            }
        }));
    }

    @Override
    public void onCustomClick(Object object) {

    }

    private void ChamarSelecActivity(String titulo_anuncio, String descricao, String img, String cidade, boolean user_id){

        Intent intent = new Intent(this, DetalheAnuncio.class);
        intent.putExtra("TITULO_ANUNCIO", titulo_anuncio);
        intent.putExtra("DESCRICAO_ANUNCIO", descricao);
        intent.putExtra("IMG_ANUNCIO", img);
        intent.putExtra("CIDADE_ANUNCIO", cidade);
        intent.putExtra("USER_ID_BOOLEAN", user_id);

        startActivity(intent);

        finish();

    }
    public void btnNovoAnuncio(View view){
        Intent novoAnuncio = new Intent(this, CadastroAnuncioActivity.class);
        startActivity(novoAnuncio);
    }
    public void abreMapa(View view){
        Intent abreMapa = new Intent(this, MapsActivity.class);
        startActivity(abreMapa);
    }

}
