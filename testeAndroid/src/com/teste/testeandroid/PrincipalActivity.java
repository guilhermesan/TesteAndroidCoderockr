package com.teste.testeandroid;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.teste.database.DatabaseManager;
import com.teste.web.SyncThread;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;


public class PrincipalActivity extends SherlockFragmentActivity {

	ProgressDialog pd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Theme_Sherlock_Light);
		super.onCreate(savedInstanceState);
		//FragmentManager fgtManager = getSupportFragmentManager();
		//android.support.v4.app.Fragment fragment = null;
		
		setContentView(R.layout.activity_principal);
		DatabaseManager.init(this);
		
		Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				processaMsg(msg);
				
			}

			
		};
		
		SyncThread sync = new SyncThread(handler);
		sync.start();
		pd = new ProgressDialog(this);
		pd.setTitle("Sincronizando");
		pd.setMessage("Baixando dados do servidor!");
		pd.show();
		
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
