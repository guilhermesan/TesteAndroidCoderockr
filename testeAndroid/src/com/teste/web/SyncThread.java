package com.teste.web;



import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teste.database.DatabaseManager;
import com.teste.model.Marca;
import com.teste.model.Product;
import com.teste.testeandroid.PrincipalActivity;




import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SyncThread extends Thread{
	private Handler handler;
	
	
	
	public SyncThread(Handler handler) {
		super();
		this.handler = handler;
	}



	@Override
	public void run() {
		try{
			
			
			try {
				HttpService ws = new HttpService("http://soa.coderockr.com/brand");
				String resposta = ws.call();
				GsonBuilder gsonb = new GsonBuilder();
				Gson gson = gsonb.setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
				JSONArray ja = new JSONArray(resposta);
				for (int i =0 ;i<ja.length();i++){
					Marca marca = gson.fromJson(ja.getJSONObject(i).toString(), Marca.class);
					marca.setImagePath(ws.downloadImage(marca.getImage(), "marca_"+marca.getId()+".png"));
					DatabaseManager.getHelper().getMarcaDao().createOrUpdate(marca);
					Message message = new Message(); 
					message.what = 1;//nova marca baixada 
					handler.sendMessage(message);
					for (Product produto : marca.getProduct_collection()){
						produto.setImagePath(ws.downloadImage(produto.getSnapshot(), "marca_"+marca.getId()+"product_"+produto.getId()+".png"));
						produto.setMarca(marca);
						DatabaseManager.getHelper().getProductDao().createOrUpdate(produto);
						message = new Message(); 
						message.what = 2; 
						handler.sendMessage(message);
					}
					PrincipalActivity.marcaAtual = marca;
				}
				
				Message message = new Message(); 
				message.what = 3; 
				handler.sendMessage(message);
					
			} catch (Exception e) {
				Message message = new Message(); 
				message.what = 0; 
				handler.sendMessage(message);
			}
			
		}catch(Exception e){
			Log.e("erro Sync Thread", e.getMessage(),e);
		}
		

	}
	
	
	


}
