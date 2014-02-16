package com.teste.testeandroid;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.teste.model.Marca;


public class MarcaArrayAdapter extends ArrayAdapter<Marca> {
	
	Context ctx;
	
	public MarcaArrayAdapter(Context context) {
		super(context,android.R.layout.simple_list_item_1);
		ctx = context;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (rowView == null){
			LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.item_list_marcas, parent,false);
			ImageView ivMarca = (ImageView)rowView.findViewById(R.id.ivMarca);
		}
			return rowView;
	}
	

}
