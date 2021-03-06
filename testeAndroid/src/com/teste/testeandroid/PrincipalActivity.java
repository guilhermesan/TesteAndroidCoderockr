package com.teste.testeandroid;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Window;
import com.teste.database.DatabaseManager;
import com.teste.model.Marca;
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
	public static Marca marcaAtual;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Theme_Sherlock_Light);
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
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
			toast("Sincronizando dados com o servidor");
			
		}else{
			setSupportProgressBarIndeterminateVisibility(false);
		}
		if (marcaAtual != null){
			if (isTablet(this))
				MarcasFragmentTablet.instancia.atualizaMarca(this.marcaAtual);
			else
				MarcasFragment.instancia.atualizaMarca(this.marcaAtual);
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
			toast("Não foi possível conectar-se com o servidor");
			setSupportProgressBarIndeterminateVisibility(false);
			break;
		case 1:
			//toast("Nova marca recebida");
			break;
		case 2:
			//toast("Novo produto recebido");
			break;	
		case 3:
			toast("Sincronização concluída");
			sincronizou = true;
			setSupportProgressBarIndeterminateVisibility(false);
			if (marcaAtual != null){
				if (isTablet(this)){
					MarcasFragmentTablet.instancia.atualizaListaMarcas();
					MarcasFragmentTablet.instancia.atualizaMarca(this.marcaAtual);
				}else{
					MarcasFragment.instancia.atualizaListaMarcas();
					MarcasFragment.instancia.atualizaMarca(this.marcaAtual);
				}
			}
			break;

		default:
			break;
		}
		
	}

	

}
