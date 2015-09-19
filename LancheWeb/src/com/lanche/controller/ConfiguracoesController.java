package com.lanche.controller;

import com.lanche.boundary.dao.DaoConfiguracoes;
import com.lanche.entity.Configuracoes;

public class ConfiguracoesController {
	public Configuracoes buscaConfiguracoes(){
		DaoConfiguracoes dao = new DaoConfiguracoes();
		return dao.buscaConfiguracoes();
	}
	
	public boolean zerarUltimoNumPedido(){
		DaoConfiguracoes dao = new DaoConfiguracoes();
		return dao.alterarUltimoNumPedido(1);
	}

}
