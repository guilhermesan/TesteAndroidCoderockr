package com.teste.web;


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teste.database.DatabaseManager;
import com.teste.model.Marca;
import com.teste.model.Product;



import android.os.Environment;
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
					marca.setImagePath(downloadImage(marca.getImage(), "marca_"+marca.getId()));
					DatabaseManager.getHelper().getMarcaDao().createOrUpdate(marca);
					Message message = new Message(); 
					message.what = 1; 
					handler.sendMessage(message);
					for (Product produto : marca.getProduct_collection()){
						produto.setImagePath(downloadImage(produto.getSnapshot(), "product_"+produto.getId()));
						DatabaseManager.getHelper().getProductDao().createOrUpdate(produto);
						message = new Message(); 
						message.what = 2; 
						handler.sendMessage(message);
					}
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
	
	private String downloadImage(String urlImg,String nome) {
		        URL url;
				try {
					url = new URL(urlImg);
				
		        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		        	
		        
		        urlConnection.setRequestMethod("GET");
		        urlConnection.setRequestProperty("Content-Type", 
		             "application/x-www-form-urlencoded");	
		        urlConnection.setUseCaches (false);
		        urlConnection.setDoInput(true);
		        urlConnection.setDoOutput(true);

		        DataOutputStream wr = new DataOutputStream (
		        		urlConnection.getOutputStream ());
		        wr.flush ();
		        wr.close ();
		        
		        File file = new File(PATH_IMAGES());
		        
		        if (!file.exists())
		        	file.mkdirs();
		        file = new File(file,nome); 
		        String path = file.getPath();
		        FileOutputStream fileOutput = new FileOutputStream(file);
		        InputStream inputStream = urlConnection.getInputStream();
		        int totalSize = urlConnection.getContentLength();
		        int downloadedSize = 0;
		        byte[] buffer = new byte[1024];
		        int bufferLength = 0; 
	
		        while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
		                fileOutput.write(buffer, 0, bufferLength);
		                downloadedSize += bufferLength;
		        }
		        fileOutput.close();
		        return path;
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					return "";
				} catch (IOException e) {
					// TODO Auto-generated catch block
					return "";
				}
	}
	

	public static  String PATH_IMAGES(){
		File SDCardRoot = Environment.getExternalStorageDirectory();
		return SDCardRoot+"/testeAndroid/Images/";
	
	}


}
