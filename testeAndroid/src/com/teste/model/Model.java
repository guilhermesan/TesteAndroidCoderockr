package com.teste.model;

import android.text.Html;





public abstract class Model  {

	protected ValidacaoModel valida;
	
	public ValidacaoModel validacao() {
		// TODO Auto-generated method stub
		valida = new ValidacaoModel();
		valida.setClasseValidacao(this.getClass().getSimpleName());
		valida.setErro(false);
		return valida;
	}

	
	
	public Double formatDouble(Double value){
		
		return Double.valueOf(String.format("%5.4f",value).replace(",", "."));
		
	}
	
	

}
