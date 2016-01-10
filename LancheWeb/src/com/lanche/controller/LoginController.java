package com.lanche.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import com.lanche.boundary.dao.TokenDAO;
import com.lanche.boundary.dao.UsuarioDAO;
import com.lanche.entity.Token;
import com.lanche.entity.Usuario;
import com.lanche.utils.PasswordHash;
import com.lanche.utils.TokenGen;

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
	
	public String getToken(String login, String password){
		if(!login(login, password))
			return " ";
		UsuarioDAO dao = new UsuarioDAO();
		
		TokenGen t = new TokenGen();
		TokenDAO d  = new TokenDAO();
		Token token = new Token(t.nextToken(), new Date(), dao.searchByLogin(login)); 
		d.persist(token);
		
		
		return token.getToken();
	}
	
	public static void main(String[] args) {
		LoginController c = new LoginController();
		System.out.println(c.getToken("lucas.kup", "13lyne"));
		
		
	}

}
