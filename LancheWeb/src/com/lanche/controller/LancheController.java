package com.lanche.controller;

import java.util.List;

import com.lanche.boundary.dao.LancheDAO;
import com.lanche.entity.Lanche;

public class LancheController {

	public List<Lanche> getAll(){
		LancheDAO dao = new LancheDAO();
		return dao.getAll();
	}
	public boolean createOrUpdateLanche(Lanche l){
		LancheDAO dao = new LancheDAO();
		
		try {
			return dao.persist(l);
		} catch (Exception e) {
			return false;
		}
		
	}
	public boolean delete(int id) {
		LancheDAO dao = new LancheDAO();
		dao.delete(id);
		
		return true;
	}

}
