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
	  
	  @DatabaseField
	  private String imagePath;
	  
	  
	  @ForeignCollectionField(eager=true)
	  private Collection<Product> product_collection;
	
	  
	

	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getImage() {
		return image;
	}




	public void setImage(String image) {
		this.image = image;
	}




	public String getImagePath() {
		return imagePath;
	}




	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}




	public Collection<Product> getProduct_collection() {
		return product_collection;
	}




	public void setProduct_collection(Collection<Product> product_collection) {
		this.product_collection = product_collection;
	}




	@Override
	public ValidacaoModel validacao() {
		// TODO implementar função de validação
		return super.validacao();
	}
	  
	  

}
