package com.teste.model;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import android.text.Html;
import android.util.Pair;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;


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
	  
	  @DatabaseField
	  private String imagePath;
	  
	  @DatabaseField(foreign=true)
	  private Marca marca;
	
	  
	

	public Marca getMarca() {
		return marca;
	}




	public void setMarca(Marca marca) {
		this.marca = marca;
	}




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




	public Boolean getFeatured() {
		return featured;
	}




	public void setFeatured(Boolean featured) {
		this.featured = featured;
	}




	public Double getPrice() {
		return price;
	}




	public void setPrice(Double price) {
		this.price = price;
	}




	public int getStatus() {
		return status;
	}




	public void setStatus(int status) {
		this.status = status;
	}




	public String getSnapshot() {
		return snapshot;
	}




	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}




	public Product getProduct() {
		return product;
	}




	public void setProduct(Product product) {
		this.product = product;
	}




	public String getImagePath() {
		return imagePath;
	}




	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}




	@Override
	public ValidacaoModel validacao() {
		// TODO implementar função de validação
		return super.validacao();
	}
	  
	  

}
