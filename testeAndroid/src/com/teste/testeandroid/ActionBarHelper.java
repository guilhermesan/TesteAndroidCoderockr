package com.teste.testeandroid;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.widget.SearchView;


public class ActionBarHelper {
	private final ActionBar mActionBar;
	private CharSequence mDrawerTitle;
	private TextView mTitle;
	private Activity act;

	ActionBarHelper(Activity act) {
		mActionBar = ((SherlockFragmentActivity)act).getSupportActionBar();
		mActionBar.setDisplayShowCustomEnabled(true);
		if (isTablet(act)){
			mActionBar.setCustomView(R.layout.action_bar_tablet);
			mActionBar.setDisplayShowCustomEnabled(true);
			SearchView search = (SearchView)mActionBar.getCustomView().findViewById(R.id.searchCategoria);
			search.setQueryHint("Categoria");
			search.setIconified(false);
		}else{
			mActionBar.setCustomView(R.layout.action_bar);
		}
		mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
		mTitle = (TextView)mActionBar.getCustomView().findViewById(R.id.tvTitulo);
		
		this.act = act;
		
		
	
	}
	
	public Context getThemedCotext(){
		return mActionBar.getThemedContext();
	}
	
	public boolean isTablet(Context context) {  
        return (context.getResources().getConfiguration().screenLayout   
                & Configuration.SCREENLAYOUT_SIZE_MASK)    
                >= Configuration.SCREENLAYOUT_SIZE_LARGE; 
    }

	public void init() {
		if (!isTablet(act)){
			mActionBar.setDisplayHomeAsUpEnabled(true);
			mActionBar.setHomeButtonEnabled(true);
		}
	
	}

	
	public void onDrawerClosed() {
	
	}

	public void onDrawerOpened() {
		mActionBar.setTitle(mDrawerTitle);
	}

	public void setTitle(CharSequence title) {
		mTitle.setText(title);
	}
}
