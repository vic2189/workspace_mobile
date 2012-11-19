package br.com.tcc.android;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

	public class AdapterListView extends BaseAdapter {

	    private LayoutInflater mInflater;
	    private ArrayList<ItemListView> itens;

	    public AdapterListView(Context context, ArrayList<ItemListView> itens) {
	        //Itens que preencheram o listview
	        this.itens = itens;
	        //responsavel por pegar o Layout do item.
	        mInflater = LayoutInflater.from(context);
	    }

	    /**
	     * Retorna a quantidade de itens
	     *
	     * @return
	     */
	    public int getCount() {
	        return itens.size();
	    }

	    /**
	     * Retorna o item de acordo com a posicao dele na tela.
	     *
	     * @param position
	     * @return
	     */
	    public ItemListView getItem(int position) {
	        return itens.get(position);
	    }

	    /**
	     * Sem implementa��o
	     *
	     * @param position
	     * @return
	     */
	    public long getItemId(int position) {
	        return position;
	    }

	    public View getView(int position, View view, ViewGroup parent) {
	        //Pega o item de acordo com a pos��o.
	        ItemListView item = itens.get(position);
	        //infla o layout para podermos preencher os dados
	        view = mInflater.inflate(R.layout.item_list, null);

	        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
	        //ao item e definimos as informa��es.
	        ((TextView) view.findViewById(R.id.text)).setText(item.getTexto());
	        ((ImageView) view.findViewById(R.id.imagemview)).setImageResource(item.getIconeRid());

	        return view;
	    }
	}

