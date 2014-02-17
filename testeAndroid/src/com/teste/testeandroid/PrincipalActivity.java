package com.teste.testeandroid;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.teste.database.DatabaseManager;
import com.teste.web.SyncThread;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;


public class PrincipalActivity extends SherlockFragmentActivity {

	ProgressDialog pd;
	public static boolean sincronizou = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Theme_Sherlock_Light);
		super.onCreate(savedInstanceState);
		if (!isTablet(this))
			setContentView(R.layout.activity_principal);
		else
			setContentView(R.layout.activity_principal_tablet);
		DatabaseManager.init(this);
		
		Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				processaMsg(msg);
				
			}

			
		};
		if (!sincronizou){
			SyncThread sync = new SyncThread(handler);
			sync.start();
			pd = new ProgressDialog(this);
			pd.setTitle("Sincronizando");
			pd.setMessage("Baixando dados do servidor!");
			pd.show();
			sincronizou = true;
		}
		
	}
	
	
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}



	public boolean isTablet(Context context) {  
        return (context.getResources().getConfiguration().screenLayout   
                & Configuration.SCREENLAYOUT_SIZE_MASK)    
                >= Configuration.SCREENLAYOUT_SIZE_LARGE; 
    }
	
	protected void toast(String txt){
		Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_SHORT).show();
	}
	
	private void processaMsg(Message msg) {
		switch (msg.what) {
		case 0:
			pd.cancel();
			toast("Não foi possível conectar-se com o servidor");
			break;
		case 1:
			toast("Nova marca recebida");
			break;
		case 2:
			toast("Novo produto recebido");
			break;	
		case 3:
			pd.cancel();
			toast("Sincronização concluída");
			break;

		default:
			break;
		}
		
	}

	

}
