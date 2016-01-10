package com.lanche.boundary.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.lanche.utils.ArquivosConfig;

public class ConFactory {

	public static final int MYSQL = 0;
	private static final String MySQLDriver = "com.mysql.jdbc.Driver";

	public static Connection conexao(int banco) {
		Connection c = null;
		try {
			switch (banco) {
			case MYSQL:
				Class.forName(MySQLDriver);
				break;
			default:
				System.out.println("Driver do banco não definido para: "+banco);
				throw new ClassNotFoundException();
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		
		
		String connString = ArquivosConfig.connectionString;
		String dbUser = ArquivosConfig.dbUser;
		String dbPass = ArquivosConfig.dbPass;
		
		try {
			c = DriverManager.getConnection(connString, dbUser, dbPass);
			c.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return c;
	}

	public static void main(String[] args) {

			ConFactory.conexao(MYSQL);
			System.out.println("Lucas");

	}

}
