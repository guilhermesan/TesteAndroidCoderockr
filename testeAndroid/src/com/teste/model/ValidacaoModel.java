package com.teste.model;

import java.util.ArrayList;

import android.util.Pair;

public class ValidacaoModel extends Validacao {
	/**
	 * Este Atributo é composto por uma lista de pares
	 * sendo o primeiro campo do par o nome do campo e
	 * o seguindo campo do par as críticas de validação
	 */
	private ArrayList<Pair<String,String>> campos;
	
	

	public ValidacaoModel(Boolean erro, String mensagem,ArrayList<Pair<String,String>> campos) {
		super(erro, mensagem,null);
		this.campos = campos;
		// TODO Auto-generated constructor stub
	}
	
	public void setCampos(Boolean erro, String mensagem,ArrayList<Pair<String,String>> campos) {
		setErro(erro);
		setMensagem(mensagem);
		setCampos(campos);
		// TODO Auto-generated constructor stub
	}

	public ValidacaoModel() {
		campos = new ArrayList<Pair<String,String>>();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Pair<String, String>> getCampos() {
		return campos;
	}

	public void setCampos(ArrayList<Pair<String, String>> campos) {
		if (campos != null)
			this.campos = campos;
	}
	
	public void addCampo(String campo,String result){
		campos.add(new Pair<String,String>(campo,result));
	}

	@Override
	public String getMensagem() {
		// TODO Auto-generated method stub
		String result = this.classeValidacao+"\n"+this.mensagem+"\n";
		if (campos != null){
			for (int i =0;i<campos.size();i++){
				result += campos.get(i).first +" "+campos.get(i).second+"\n";
			}
		}
		return result;
	}
	
	

}
