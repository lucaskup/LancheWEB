package com.lanche.boundary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DAO <T> {
	protected Connection con;  
	protected PreparedStatement comando;


	public abstract T searchByID(int id);
	public abstract boolean delete(T t);
	public abstract boolean persist(T t);
	protected boolean openConnection(){
		try {
			if(con == null || con.isClosed())
				con = ConFactory.conexao(ConFactory.MYSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return con != null;
	}

}
