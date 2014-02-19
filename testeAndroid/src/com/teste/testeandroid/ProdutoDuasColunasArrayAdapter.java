package com.teste.testeandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.util.Pair;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.teste.model.Marca;
import com.teste.model.Product;
import com.teste.web.HttpService;


public class ProdutoDuasColunasArrayAdapter extends ArrayAdapter<Pair<Product, Product>> {
	
	Context ctx;
	
	public ProdutoDuasColunasArrayAdapter(Context context) {
		super(context,android.R.layout.simple_list_item_1);
		ctx = context;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		Pair<Product, Product> pair = getItem(position);
		if (rowView == null){
			LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.item_list_produtos_tablet, parent,false);
			
			Options  op = new BitmapFactory.Options();
			op.inPreferredConfig = Config.RGB_565;
			Product produto = pair.first;
			if (!produto.getImagePath().equals("")){
				Bitmap image = BitmapFactory.decodeFile(produto.getImagePath());
				ImageView ivProduto = (ImageView)rowView.findViewById(R.id.ivProduto);
				ivProduto.setImageBitmap(image);
			}
		
			Display display = ((WindowManager) ((Activity)ctx).getSystemService(((Activity)ctx).WINDOW_SERVICE))  .getDefaultDisplay();
		
			TextView tvNome = (TextView)rowView.findViewById(R.id.tvNomeProduto);
			//TextView tvDescricao = (TextView)rowView.findViewById(R.id.tvDescricaoProduto);
			TextView tvPreco = (TextView)rowView.findViewById(R.id.tvPrecoProduto);
			tvNome.setText(produto.getDescription());
			tvPreco.setText(String.format("R$%5.2f",produto.getPrice()));
			
			
			ImageView ivProduto = (ImageView)rowView.findViewById(R.id.ivProduto2);
			tvNome = (TextView)rowView.findViewById(R.id.tvNomeProduto2);
			tvPreco = (TextView)rowView.findViewById(R.id.tvPrecoProduto2);
			produto = pair.second;
			if (produto != null){
				if (!produto.getImagePath().equals("")){
					Bitmap image = BitmapFactory.decodeFile(produto.getImagePath());
					ivProduto.setImageBitmap(image);
				}
				tvNome.setText(produto.getDescription());
				tvPreco.setText(String.format("R$%5.2f",produto.getPrice()));
			}else{
				tvNome.setVisibility(View.INVISIBLE);
				tvPreco.setVisibility(View.INVISIBLE);
				ivProduto.setVisibility(View.INVISIBLE);
			}
			rowView.setTag(pair);
		}
			return rowView;
	}
	

}
