package com.lanche.boundary.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.lanche.entity.Token;
import com.lanche.utils.ArquivosConfig;

public class TokenDAO extends DAO {
	public boolean persist(Token t) {
		if(openConnection()){
			boolean inseriu = true;
			try {

					insert(t);
				
			} catch (Exception e) {
				e.printStackTrace();
				inseriu = false;
				
			} finally	{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return inseriu;
		}
		return false;
}

	private void insert(Token t) throws SQLException  {
		// TODO Auto-generated method stub
		if(openConnection()){
			comando = con.prepareStatement(ArquivosConfig.tokenInsert,Statement.RETURN_GENERATED_KEYS);
			comando.setString(1, t.getToken());
			comando.setTimestamp(2, new java.sql.Timestamp(t.getDtCriacao().getTime()));
			comando.setTimestamp(3, new java.sql.Timestamp(t.getValidade().getTime()));
			comando.setInt(4, t.getUsuario().getId());
			
			if(comando.executeUpdate()>0){
				ResultSet r = comando.getGeneratedKeys();
				if(r.next())
					t.setId(r.getInt(1));
			}	
		}
	}

	public Token searchToken(int id, String strToken) {
		if (openConnection()) {
			Token token = null;
			try {
				
				comando = con.prepareStatement(ArquivosConfig.tokenSelect);
				comando.setString(1, strToken);
				comando.setInt(2, id);

				ResultSet r = comando.executeQuery();
				//token.idToken, token.token, token.dtCriacao, token.validade, token.fkUsuario
				if (r.next()){
					Date dtCriacao = null;
					Date dtValidade = null;
					Timestamp tsCriacao = r.getTimestamp(3);
					Timestamp tsValidade = r.getTimestamp(4);
					if(tsCriacao != null)
						dtCriacao = new Date(tsCriacao.getTime());
					if(tsValidade != null)
						dtValidade = new Date(tsValidade.getTime());
					UsuarioDAO d = new UsuarioDAO();
					
					
					token = new Token(r.getInt(1), r.getString(2),dtCriacao , dtValidade);
					token.setUsuario(d.searchByID(r.getInt(5)));
				}
					
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return token;
		}
		return null;
	}
}