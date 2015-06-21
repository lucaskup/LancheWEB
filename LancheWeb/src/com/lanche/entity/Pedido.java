package com.lanche.entity;

import java.util.ArrayList;
import java.util.Date;

import com.lanche.entity.enums.Status;

public class Pedido {

	private int id;
	private Date dtCriacao;
	private Date dtModificacao;
	private Status status;
	private Usuario usuario;
	private ArrayList<ItemPedido> itens;
	
	
	public Pedido() {
	}

	public Pedido(int id, Date dtCriacao, Status status,Usuario usurio) {
		super();
		this.id = id;
		this.dtCriacao = dtCriacao;
		this.status = status;
		this.usuario = usurio;
	}
	public Pedido(int id, Date dtCriacao, Date dtModificacao, Status status,Usuario usurio) {
		super();
		this.id = id;
		this.dtCriacao = dtCriacao;
		this.dtModificacao = dtModificacao;
		this.status = status;
		this.usuario = usurio;
	}


/**
 * 
 * @return Retorna o valor de todos os itens e seus opcionais somados
 */
	public double getTotal(){
		double total = 0;
		if(itens != null){
			for (ItemPedido itemPedido : itens) {
				total += itemPedido.getTotal();
			}
		}
		return total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ArrayList<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(ArrayList<ItemPedido> itens) {
		this.itens = itens;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDtModificacao() {
		return dtModificacao;
	}

	public void setDtModificacao(Date dtModificacao) {
		this.dtModificacao = dtModificacao;
	}


}
