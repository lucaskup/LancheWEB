package com.lanche.boundary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DAO {
	protected Connection con;  
	protected PreparedStatement comando;


	
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
