package com.lanche.entity;

import java.util.Date;

public class Token {
	private int id;
	private String token;
	private Date dtCriacao;
	private Date validade;
	private Usuario usuario;
	//a validade é de 15 minutos
	public static final long TEMPO_RENOVACAO_AUTOMATICA = 900000;
	
	public Token(){
		
	}
	
	public Token(int id, String token, Date dtCriacao, Date validade) {
		super();
		this.id = id;
		this.token = token;
		this.dtCriacao = dtCriacao;
		this.validade = validade;

	}

	public Token(String token, Date dtCriacao, Usuario usuario) {
		super();
		this.token = token;
		this.dtCriacao = dtCriacao;
		this.usuario = usuario;
		setValidade();
	}
	
	private void setValidade() {

		if(this.validade == null)
		  validade = new Date(dtCriacao.getTime()+TEMPO_RENOVACAO_AUTOMATICA);
		else
			validade = new Date(validade.getTime()+TEMPO_RENOVACAO_AUTOMATICA);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getDtCriacao() {
		return dtCriacao;
	}
	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
