package com.teste.testeandroid;

import java.sql.SQLException;
import java.util.List;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.widget.DrawerLayout;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import com.sherlock.navigationdrawer.compat.SherlockActionBarDrawerToggle;
import com.teste.database.DatabaseManager;
import com.teste.model.Marca;
import com.teste.model.Product;


public class MarcasFragmentTablet extends SherlockFragment {
	
	
	public  ListView lvMarcas,lvProdutos;
	private TextView tvDescription;
	public  LinearLayout llPrincipal;
	public static MarcasFragmentTablet instancia;
	private LinearLayout llMenuEsquerdo;
	private MarcaArrayAdapter adapter;
	private ProdutoDuasColunasArrayAdapter produtoAdapter;

	private ActionBarHelper mActionBar;

	private SherlockActionBarDrawerToggle mDrawerToggle;

	public static Fragment newInstance() {
		instancia = new MarcasFragmentTablet();
		return (Fragment)instancia;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		setHasOptionsMenu(true);
		instancia = this;
		atualizaListaMarcas();
		
	
	}
	
	protected String getStringValue(int idString){
		return getResources().getString(idString);
	}
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.marcas_fragment_tablet, container, false);
		
		lvMarcas = (ListView)view.findViewById(R.id.lvMarcas);
		lvMarcas.setOnItemClickListener(new onItemClickListener());
		
		
		//carregaMenu();
		mActionBar = createActionBarHelper();
		mActionBar.init();

		llMenuEsquerdo = (LinearLayout) view.findViewById(R.id.llMenuEsquerdo);
		llPrincipal = (LinearLayout) view.findViewById(R.id.llPrincipal);
		tvDescription = (TextView) view.findViewById(R.id.tvDescription);
		
		
		
		
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
		
	}

	
	private class onItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Marca marca = ((Marca)view.getTag());
			
			
			atualizaMarca(marca);
			
		}
	}

	
	public void atualizaListaMarcas(){
		try {
			DatabaseManager.init(this.getActivity());
			List<Marca> list = DatabaseManager.getHelper().getMarcaDao().queryForAll();
			adapter = new MarcaArrayAdapter(this.getActivity());
			produtoAdapter = new ProdutoDuasColunasArrayAdapter(this.getActivity());
			for (Marca marca : list){
				adapter.add(marca);
				PrincipalActivity.marcaAtual = marca;
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void atualizaMarca(Marca marca){
		mActionBar.setTitle(marca.getName());
		tvDescription.setText(marca.getDescription());
		produtoAdapter.clear();
		mActionBar.setTitle(marca.getName());
		Product produto1 = null,produto2 = null;
		for (Product produto : marca.getProduct_collection()){
			if (produto1 == null)
				produto1 = produto;
			else if (produto2 == null)
				produto2 = produto;
			else {
				Pair<Product, Product> pair = new Pair<Product, Product>(produto1, produto2);
				produto1 = null;
				produto2 = null;
				produtoAdapter.add(pair);
			}
			
		}
		if (produto1 != null){
			Pair<Product, Product> pair = new Pair<Product, Product>(produto1, produto2);
			produtoAdapter.add(pair);
		}
		lvProdutos.setAdapter(produtoAdapter);
		lvMarcas.setAdapter(adapter);
	}
	
	
	private ActionBarHelper createActionBarHelper() {
		return new ActionBarHelper(getActivity());
	}

	

	
	
	

}
