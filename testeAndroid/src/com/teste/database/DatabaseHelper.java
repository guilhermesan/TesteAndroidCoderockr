package com.teste.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.teste.model.Marca;
import com.teste.model.Product;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	
	 private static final String DATABASE_NAME = "UPMarket.sqlite";

	    // any time you make changes to your database objects, you may have to increase the database version
	 private static final int DATABASE_VERSION = 24;

	    // the DAO object we use to access the SimpleData table
	
	 private Dao<Product, Integer> productDao = null;
	 private Dao<Marca, Integer> marcaDao = null;
	 	 
	 public Dao<Product, Integer> getProductDao() {
	        if (null == productDao) {
	            try {
	            	productDao = getDao(Product.class);
	            }catch (java.sql.SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return productDao;
	    }
	 
	 public Dao<Marca, Integer> getMarcaDao() {
	        if (null == marcaDao) {
	            try {
	            	marcaDao = getDao(Marca.class);
	            }catch (java.sql.SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return marcaDao;
	    }
	 
	 

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase database,
			ConnectionSource connectionSource) {
		try {         
            TableUtils.createTableIfNotExists(connectionSource, Product.class);
            TableUtils.createTableIfNotExists(connectionSource, Marca.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

	}

	@Override
	public void onUpgrade(SQLiteDatabase db,
			ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			onCreate(db,connectionSource);
            List<String> allSql = new ArrayList<String>();
            switch(oldVersion) 
            {
              case 1: 
            	  //sql de atualização
              
            }
            for (String sql : allSql) {
                db.execSQL(sql);
            }
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "exception during onUpgrade", e);
            throw new RuntimeException(e);
        }
        

	}
	
	@Override
	public ConnectionSource getConnectionSource(){
		return this.connectionSource;
	}

}
