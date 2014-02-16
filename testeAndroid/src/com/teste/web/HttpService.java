package com.teste.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.net.ParseException;

public class HttpService {
	
	private String url;
	
	

	
	
	public HttpService(){
		
	}
	
	public HttpService(String url){
		this.url = url;
		
	}
	
	public HttpService(String url,boolean isGetFuncion){
		
		this.url = url;
	}
	
	
	
	public String call() throws ClientProtocolException, IOException{
		 HttpClient httpclient = new DefaultHttpClient();
		 httpclient.getParams().setIntParameter("http.socket.timeout", 2000000);
		 httpclient.getParams().setIntParameter("http.connection.timeout", 10000);
		 
		 HttpGet httppost;
		 
		 
		 httppost = new HttpGet(url);
		 httppost.addHeader("Content-type", "application/x-www-form-urlencoded");
		 httppost.addHeader("Authorization", "85e4a615f62c711d3aac0e7def5b4903");   	
		
	     HttpResponse webServerAnswer = httpclient.execute(httppost);  
	     String result = getResponseBody(webServerAnswer);
	     return  URLDecoder.decode(result,"UTF-8");
		       
		
		
		
	}
	
	public static String getResponseBody(HttpResponse response) {

		String response_text = null;

		HttpEntity entity = null;

		try {

			entity = response.getEntity();
	
			response_text = _getResponseBody(entity);
			
			entity = null;
			System.gc();

		} catch (ParseException e) {

			e.printStackTrace();

		} catch (IOException e) {

			if (entity != null) {
	
				try {
		
					entity.consumeContent();
	
				} catch (IOException e1) {

				}

			}

		}

		return response_text;

	}

	
	public static String _getResponseBody(final HttpEntity entity) throws IOException, ParseException {

		if (entity == null) { throw new IllegalArgumentException("HTTP entity may not be null"); }

		InputStream instream = entity.getContent();

		if (instream == null) { return ""; }

		if (entity.getContentLength() > Integer.MAX_VALUE) { 
			throw new IllegalArgumentException(	"HTTP entity too large to be buffered in memory"); 
		}

		String charset = getContentCharSet(entity);

		if (charset == null) {

			charset = HTTP.DEFAULT_CONTENT_CHARSET;

		}

		Reader reader = new InputStreamReader(instream, charset);

		StringBuilder buffer = new StringBuilder();

		try {

			char[] tmp = new char[1024];

			int l;

			while ((l = reader.read(tmp)) != -1) {

				buffer.append(tmp, 0, l);

			}

		} finally {

			reader.close();
			reader = null;

		}

		String result =buffer.toString();
		buffer = null;
		System.gc();
		return result;

	}

	public static String getContentCharSet(final HttpEntity entity) throws ParseException {

		if (entity == null) { throw new IllegalArgumentException("HTTP entity may not be null"); }

		String charset = null;

		if (entity.getContentType() != null) {

		HeaderElement values[] = entity.getContentType().getElements();

			if (values.length > 0) {
	
				NameValuePair param = values[0].getParameterByName("charset");
		
				if (param != null) {
		
					charset = param.getValue();
		
				}
	
			}

		}

		return charset;

	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
