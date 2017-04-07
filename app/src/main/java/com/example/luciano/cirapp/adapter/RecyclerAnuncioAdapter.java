package com.example.luciano.cirapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;


import com.example.luciano.cirapp.R;
import com.example.luciano.cirapp.model.Anuncio;
import com.example.luciano.cirapp.util.ClickRecyclerView_Interface;

import java.util.List;

/**
 * Created by Stanley on 07/04/2017.
 */
public class RecyclerAnuncioAdapter extends RecyclerView.Adapter<RecyclerAnuncioAdapter.RecyclerAnuncioViewHolder> {

    public static ClickRecyclerView_Interface clickRecyclerViewInterface;
    Context mctx;
    private List<Anuncio> mList;

    public RecyclerAnuncioAdapter(Context ctx, List<Anuncio> list, ClickRecyclerView_Interface clickRecyclerViewInterface) {
        this.mctx = ctx;
        this.mList = list;
        this.clickRecyclerViewInterface = clickRecyclerViewInterface;
    }

    @Override
    public RecyclerAnuncioViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_lista_itens, viewGroup, false);
        return new RecyclerAnuncioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerAnuncioViewHolder viewHolder, int i) {
        Anuncio anuncio = mList.get(i);

        viewHolder.viewTitulo.setText(anuncio.getTitulo());
        viewHolder.viewData.setText(anuncio.getData());
        viewHolder.viewCidade.setText(anuncio.getCidade());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    protected class RecyclerAnuncioViewHolder extends RecyclerView.ViewHolder {

        protected TextView viewTitulo;
        protected TextView viewData;
        protected TextView viewCidade;

        public RecyclerAnuncioViewHolder(final View itemView) {
            super(itemView);

            viewTitulo = (TextView) itemView.findViewById(R.id.tvTituloAnuncio);
            viewData = (TextView) itemView.findViewById(R.id.tvDataHoraAnuncio);
            viewCidade = (TextView) itemView.findViewById(R.id.tvLocalAnuncio);

            //Setup the click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    clickRecyclerViewInterface.onCustomClick(mList.get(getLayoutPosition()));
                    Log.i("Pegou", ""+mList.get(getLayoutPosition()).getCidade());

                }
            });
        }
    }
}