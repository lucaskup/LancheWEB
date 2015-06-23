package com.lanche.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.lanche.boundary.dao.UsuarioDAO;
import com.lanche.entity.Usuario;
import com.lanche.utils.PasswordHash;

public class LoginController {
	
	public boolean login(String login, String password){
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usu = dao.searchByLogin(login);
		//Se não encontrou usuário retorna falso no login
		if(usu==null)
			return false;
		try {
			//Retorna true se os hashs bateram
			return PasswordHash.validatePassword(password, usu.getPassword());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			return false;
		}
	}

}
