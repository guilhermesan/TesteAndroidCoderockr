package com.teste.testeandroid;

import java.sql.SQLException;
import java.util.List;


import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import android.widget.AdapterView;


import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;

import com.sherlock.navigationdrawer.compat.SherlockActionBarDrawerToggle;
import com.teste.database.DatabaseManager;
import com.teste.model.Marca;
import com.teste.model.Product;








public class MarcasFragment extends SherlockFragment {
	
	private DrawerLayout mDrawerLayout;
	public  ListView lvMarcas,lvProdutos;
	private TextView tvDescription;
	public  LinearLayout llPrincipal;
	public static MarcasFragment instancia;
	private LinearLayout llMenuEsquerdo;
	private MarcaArrayAdapter adapter;
	private ProdutoUmaColunaArrayAdapter produtoAdapter;

	private ActionBarHelper mActionBar;

	private SherlockActionBarDrawerToggle mDrawerToggle;

	public static Fragment newInstance() {
		instancia = new MarcasFragment();
		return (Fragment)instancia;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		setHasOptionsMenu(true);
		instancia = this;
		try {
			DatabaseManager.init(this.getActivity());
			List<Marca> list = DatabaseManager.getHelper().getMarcaDao().queryForAll();
			adapter = new MarcaArrayAdapter(this.getActivity());
			produtoAdapter = new ProdutoUmaColunaArrayAdapter(this.getActivity());
			for (Marca marca : list){
				adapter.add(marca);
				PrincipalActivity.marcaAtual = marca;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	protected String getStringValue(int idString){
		return getResources().getString(idString);
	}
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.marcas_fragment, container, false);
		mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
		lvMarcas = (ListView)view.findViewById(R.id.lvMarcas);
		lvMarcas.setOnItemClickListener(new DrawerItemClickListener());
		//tratar Abertura e fechamento do Menu lateral com movimento do dedo
		mDrawerLayout.setDrawerListener(new onDrawerListener());
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		
		SearchView search = (SearchView)view.findViewById(R.id.searchCategoria);
		search.setQueryHint("Categoria");
		search.setIconified(false);
		mActionBar = createActionBarHelper();
		mActionBar.init();

		llMenuEsquerdo = (LinearLayout) view.findViewById(R.id.llMenuEsquerdo);
		llPrincipal = (LinearLayout) view.findViewById(R.id.llPrincipal);
		tvDescription = (TextView) view.findViewById(R.id.tvDescription);
		
		
		mDrawerToggle = new SherlockActionBarDrawerToggle(this.getActivity(), mDrawerLayout, R.drawable.ic_drawer_light, R.string.abs__action_bar_home_description,R.string.abs__action_bar_home_description );
		mDrawerToggle.syncState();
		lvMarcas.setAdapter(adapter);
		lvProdutos = (ListView)view.findViewById(R.id.lvProdutos);
		lvProdutos.setDivider(null);
		if (PrincipalActivity.marcaAtual != null)
			atualizaMarca(PrincipalActivity.marcaAtual);
		return view;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater = ((SherlockFragmentActivity)getActivity()).getSupportMenuInflater();
		inflater.inflate(R.menu.principal, menu);
		super.onCreateOptionsMenu(menu, inflater);
		
        
        
	}
	
	 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Marca marca = ((Marca)view.getTag());
			
			
			atualizaMarca(marca);
			
		}
	}
	
	public void atualizaMarca(Marca marca){
		mActionBar.setTitle(marca.getName());
		mDrawerLayout.closeDrawer(llMenuEsquerdo);
		tvDescription.setText(marca.getDescription());
		produtoAdapter.clear();
		mActionBar.setTitle(marca.getName());
		for (Product produto : marca.getProduct_collection()){
			produtoAdapter.add(produto);
		}
		lvProdutos.setAdapter(produtoAdapter);
	}


	private class onDrawerListener implements DrawerLayout.DrawerListener {
		@Override
		public void onDrawerOpened(View drawerView) {
			mDrawerToggle.onDrawerOpened(drawerView);
			mActionBar.onDrawerOpened();
		}

		@Override
		public void onDrawerClosed(View drawerView) {
			mDrawerToggle.onDrawerClosed(drawerView);
			mActionBar.onDrawerClosed();
		}

		@Override
		public void onDrawerSlide(View drawerView, float slideOffset) {
			mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
		}

		@Override
		public void onDrawerStateChanged(int newState) {
			mDrawerToggle.onDrawerStateChanged(newState);
		}
	}

	
	private ActionBarHelper createActionBarHelper() {
		return new ActionBarHelper(getActivity());
	}

	

	
	
	

}
