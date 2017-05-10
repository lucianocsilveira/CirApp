package com.example.luciano.cirapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.luciano.cirapp.adapter.RecyclerAnuncioAdapter;
import com.example.luciano.cirapp.api.CirService;
import com.example.luciano.cirapp.model.Anuncio;
import com.example.luciano.cirapp.util.ClickRecyclerView_Interface;
import com.example.luciano.cirapp.util.RecyclerItemClickListener;

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

                Log.i("JONY", anuncioList.get(2).getImagem());

                for (Anuncio a : anuncioList){

                    Anuncio anuncio = new Anuncio();
                    anuncio.setTitulo(a.getTitulo().toString());
                    anuncio.setData(a.getData().toString());
                    anuncio.setCidade(a.getCidade().toString());
                    //anuncio.setDescricao(a.getDescricao().toString());
                    anuncio.setId(a.getId());
                    //anuncio.setImagem(a.getImagem().toString());

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
            public void onItemClick(View view, int position) {
                Toast.makeText(getBaseContext(),anunciosListas.get(position).getTitulo(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemLongClick(View view, int position) {
                //position += 1;
                Toast.makeText(getBaseContext(),"teste = "+ position, Toast.LENGTH_SHORT).show();
                ChamarSelecActivity(anunciosListas.get(position).getCidade());

            }
        }));
    }

    @Override
    public void onCustomClick(Object object) {

    }

    private void ChamarSelecActivity(String titulo_anuncio){

        Intent intent = new Intent(this, DetalheAnuncio.class);
        intent.putExtra("TITULO_ANUNCIO", titulo_anuncio);
        //intent.putExtra("DESCRICAO_ANUNCIO", descricao);

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
