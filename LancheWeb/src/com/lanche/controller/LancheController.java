package com.lanche.controller;

import java.sql.SQLException;
import java.util.ArrayList;
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
	public void updateOpcionaisDoLanche(int idLanche, String idOpcionais) {
		String[] idOpcionaisAux = idOpcionais.split(",");
		ArrayList<Integer> idsOpcionais = new ArrayList<Integer>();
		for (int i = 0; i < idOpcionaisAux.length; i++) {
			try{
				idsOpcionais.add(Integer.parseInt(idOpcionaisAux[i]));
			}catch(NumberFormatException e){
				
			}
			
		}
		
		LancheDAO dao = new LancheDAO();
		try {
			dao.updateOpcionais(idLanche,idsOpcionais);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
