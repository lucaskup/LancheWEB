package com.lanche.entity;

import java.util.Date;

public class Opcionais implements Comparable<Opcionais> {
	private int id;
	private String descricao;
	private double precoAdicional;
	private boolean permiteMaisQueUm;
	private Date dtCadastro;
	private Date dtModificacao;
	public Opcionais(){
		
	}
	
	public Opcionais(int id, String descricao, double precoAdicional,
			boolean permiteMaisQueUm, Date dtCadastro, Date dtModificacao) {
		this.id = id;
		this.descricao = descricao;
		this.precoAdicional = precoAdicional;
		this.permiteMaisQueUm = permiteMaisQueUm;
		this.dtCadastro = dtCadastro;
		this.dtModificacao = dtModificacao;
	}
	public Opcionais(int id, String descricao, double precoAdicional,
			boolean permiteMaisQueUm) {
		this.id = id;
		this.descricao = descricao;
		this.precoAdicional = precoAdicional;
		this.permiteMaisQueUm = permiteMaisQueUm;
	}
	public Opcionais(int id) {
		this.id = id;
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
	public double getPrecoAdicional() {
		return precoAdicional;
	}
	public void setPrecoAdicional(double precoAdicional) {
		this.precoAdicional = precoAdicional;
	}
	public boolean permiteMaisQueUm() {
		return permiteMaisQueUm;
	}
	public void setPermiteMaisQueUm(boolean permiteMaisQueUm) {
		this.permiteMaisQueUm = permiteMaisQueUm;
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
	@Override
	public int compareTo(Opcionais o) {
		return this.getDescricao().compareToIgnoreCase(o.getDescricao());
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return this==null;
		else if(obj instanceof Opcionais)
			return ((Opcionais) obj).getId() == this.getId();
		else
			return super.equals(obj);
	}
	

}
