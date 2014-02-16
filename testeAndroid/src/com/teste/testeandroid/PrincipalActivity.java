package com.teste.testeandroid;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.teste.database.DatabaseManager;
import com.teste.web.SyncThread;


import android.os.Bundle;
import android.os.Handler;


public class PrincipalActivity extends SherlockFragmentActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Theme_Sherlock_Light);
		super.onCreate(savedInstanceState);
		//FragmentManager fgtManager = getSupportFragmentManager();
		//android.support.v4.app.Fragment fragment = null;
		
		setContentView(R.layout.activity_principal);
		DatabaseManager.init(this);
		
		Handler handler = new Handler();
		
		SyncThread sync = new SyncThread(handler);
		sync.start();
		
	}

	

}
