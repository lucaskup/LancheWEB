package com.lanche.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Lanche {
	private int id;
	private String descricao;
	private double preco;
	private Date dtCadastro;
	private Date dtModificacao;
	private boolean ativo;
	private ArrayList<Opcionais> opcionais;
	
	public Lanche() {
		opcionais = new ArrayList<Opcionais>();
	}
	public Lanche(int id, String descricao, double preco, Date dtCadastro,
			Date dtModificacao, boolean ativo) {
		this();
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
		this.dtCadastro = dtCadastro;
		this.dtModificacao = dtModificacao;
		this.ativo = ativo;	
	}
	public Lanche(int id) {
		this(id, null, 0, null, null, false);
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
	public ArrayList<Opcionais> getOpcionais() {
		return opcionais;
	}
	public void setOpcionais(ArrayList<Opcionais> opcionais) {
		this.opcionais = opcionais;
	}
	public boolean possuiOpcionais(){
		return opcionais.size()>0;
	}
	public String getCSVIdOpcionais(){
		String csv = new String();
	for (int i = 0; i < opcionais.size(); i++) {
		csv += opcionais.get(i).getId();
		if(i+1<opcionais.size())
			csv+=",";
	}
		
		
		return csv;
	}
	
	

}
