package com.teste.model;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import android.text.Html;
import android.util.Pair;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;


public class Marca extends Model {
	
	  @DatabaseField(id=true)
	  private int id;
	  
	  
	  @DatabaseField
	  private String description;
	  
	  @DatabaseField
	  private String name;
	  
	  @DatabaseField
	  private String image;
	  
	  
	 @ForeignCollectionField
	 private ForeignCollection<Product> product_collection;
	
	  
	

	@Override
	public ValidacaoModel validacao() {
		// TODO implementar função de validação
		return super.validacao();
	}
	  
	  

}
