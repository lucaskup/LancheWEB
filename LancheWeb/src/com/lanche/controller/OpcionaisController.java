package com.lanche.controller;

import java.util.List;

import com.lanche.boundary.dao.OpcionaisDAO;
import com.lanche.entity.Opcionais;

public class OpcionaisController {
	
	public List<Opcionais> getAll(){
		OpcionaisDAO dao = new OpcionaisDAO();
		return dao.getAll();
		
	}

	public boolean delete(int id) {
		OpcionaisDAO dao = new OpcionaisDAO();
		return dao.delete(id);
	}

	public boolean createOrUpdateLanche(Opcionais opcional) {
		OpcionaisDAO dao = new OpcionaisDAO();
		return dao.persist(opcional);
	}

}
