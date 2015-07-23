package com.lanche.entity;

import java.util.Date;

public class Lanche {
	private int id;
	private String descricao;
	private double preco;
	private Date dtCadastro;
	private Date dtModificacao;
	private boolean ativo;
	

	public Lanche(int id, String descricao, double preco, Date dtCadastro,
			Date dtModificacao, boolean ativo) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
		this.dtCadastro = dtCadastro;
		this.dtModificacao = dtModificacao;
		this.ativo = ativo;
	}
	public Lanche(int id) {
		this.id = id;
	}
	public Lanche(int id, String descricao, double preco, boolean ativo) {
		this(id, descricao, preco, null, null, ativo);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public Date getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	public Date getDtModificacao() {
		return dtModificacao;
	}
	public void setDtModificacao(Date dtModificacao) {
		this.dtModificacao = dtModificacao;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	

}
