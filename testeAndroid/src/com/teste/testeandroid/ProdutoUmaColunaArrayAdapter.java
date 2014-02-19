package com.teste.testeandroid;

import android.content.Context;
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
import com.teste.model.Product;
import com.teste.web.HttpService;


public class ProdutoUmaColunaArrayAdapter extends ArrayAdapter<Product> {
	
	Context ctx;
	
	public ProdutoUmaColunaArrayAdapter(Context context) {
		super(context,android.R.layout.simple_list_item_1);
		ctx = context;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		Product produto = getItem(position);
		if (rowView == null){
			LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.item_list_produtos, parent,false);
			
			Options  op = new BitmapFactory.Options();
			op.inPreferredConfig = Config.RGB_565;
			if (!produto.getImagePath().equals("")){
				Bitmap image = BitmapFactory.decodeFile(produto.getImagePath());
				ImageView ivProduto = (ImageView)rowView.findViewById(R.id.ivProduto);
				ivProduto.setImageBitmap(image);
			}
			TextView tvNome = (TextView)rowView.findViewById(R.id.tvNomeProduto);
			//TextView tvDescricao = (TextView)rowView.findViewById(R.id.tvDescricaoProduto);
			TextView tvPreco = (TextView)rowView.findViewById(R.id.tvPrecoProduto);
			tvNome.setText(produto.getDescription());
			tvPreco.setText(String.format("R$%5.2f",produto.getPrice()));
			rowView.setTag(produto);
		}
			return rowView;
	}
	

}
