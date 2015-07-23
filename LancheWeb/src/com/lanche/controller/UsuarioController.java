package com.lanche.controller;

import java.util.List;

import com.lanche.boundary.dao.UsuarioDAO;
import com.lanche.entity.Usuario;

public class UsuarioController {
	public List<Usuario> getAll(){
		UsuarioDAO dao = new UsuarioDAO();
		return dao.getAll();
	}

	public boolean delete (int id){
		UsuarioDAO dao = new UsuarioDAO();	
		return dao.delete(id);	}

	public void createOrUpdateLanche(Usuario usu) {
		UsuarioDAO dao = new UsuarioDAO();
		dao.persist(usu);	
	}
	
}
