package com.teste.model;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import android.text.Html;
import android.util.Pair;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;


public class Product extends Model {
	
	  @DatabaseField(id=true)
	  private int id;
	  
	  
	  @DatabaseField
	  private String description;
	  
	  
	  @DatabaseField
	  private Boolean featured;
	  
	  	  
	  @DatabaseField
	  private Double price;
	  
	  @DatabaseField
	  private int status;
	  
	  @DatabaseField
	  private String snapshot;
	  
	  @DatabaseField(foreign=true)
	  private Product product;
	
	  
	

	@Override
	public ValidacaoModel validacao() {
		// TODO implementar função de validação
		return super.validacao();
	}
	  
	  

}
