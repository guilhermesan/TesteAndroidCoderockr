package com.teste.testeandroid;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.teste.model.Marca;
import com.teste.web.HttpService;


public class MarcaArrayAdapter extends ArrayAdapter<Marca> {
	
	Context ctx;
	
	public boolean isTablet(Context context) {  
        return (context.getResources().getConfiguration().screenLayout   
                & Configuration.SCREENLAYOUT_SIZE_MASK)    
                >= Configuration.SCREENLAYOUT_SIZE_LARGE; 
    }
	
	public MarcaArrayAdapter(Context context) {
		super(context,android.R.layout.simple_list_item_1);
		ctx = context;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		Marca marca = getItem(position);
		if (rowView == null){
			LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			if (!isTablet(ctx))
				rowView = inflater.inflate(R.layout.item_list_marcas, parent,false);
			else
				rowView = inflater.inflate(R.layout.item_list_marcas_tablet, parent,false);
			
			Options  op = new BitmapFactory.Options();
			op.inPreferredConfig = Config.RGB_565;
			if (!marca.getImagePath().equals("")){
				Bitmap image = BitmapFactory.decodeFile(marca.getImagePath());
				ImageView ivMarca = (ImageView)rowView.findViewById(R.id.ivMarca);
				ivMarca.setImageBitmap(image);
			}
			rowView.setTag(marca);
		}
			return rowView;
	}
	

}
