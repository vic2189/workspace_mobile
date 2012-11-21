package br.com.tcc.android;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CustomAdapter extends ArrayAdapter<String> {

	 private final LayoutInflater inflater;
	 private final int resourceId;
	 private final int listaId;
	 
	 public CustomAdapter(Context context, int resource, int listaId, List<String> objects) {
			super(context, resource, listaId, objects);
			this.inflater = LayoutInflater.from(context);
			this.resourceId = resource;
			this.listaId = listaId;
		}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = inflater.inflate(resourceId, parent, false);
		
		ListView listView = (ListView) convertView.findViewById(android.R.id.list);
		if(position % 2 == 0){
			listView.setBackgroundColor(Color.LTGRAY);
		}else{
			listView.setBackgroundColor(Color.GREEN);
		}
		return convertView;
	}
}
