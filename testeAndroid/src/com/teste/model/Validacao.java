package com.teste.model;

public class Validacao {
	
	public Boolean erro;
	public String mensagem;
	public Exception exception;
	public String classeValidacao;
	
	public Validacao(Boolean erro, String mensagem, Exception exception,String classeValidacao) {
		super();
		this.erro = erro;
		this.mensagem = mensagem;
		this.exception = exception;
		this.classeValidacao = classeValidacao;
	}

	public Validacao(Boolean erro, String mensagem, Exception exception) {
		super();
		this.erro = erro;
		this.mensagem = mensagem;
		this.exception = exception;
		
	}
	public Validacao() {
		
	}

	public Boolean getErro() {
		return erro;
	}

	public void setErro(Boolean erro) {
		this.erro = erro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getClasseValidacao() {
		return classeValidacao;
	}

	public void setClasseValidacao(String classeValidacao) {
		this.classeValidacao = classeValidacao;
	}
	
	
	

}
