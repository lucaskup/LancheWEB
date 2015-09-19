package com.lanche.boundary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lanche.boundary.dao.DAO;
import com.lanche.entity.Configuracoes;
import com.lanche.utils.ArquivosConfig;

public class DaoConfiguracoes extends DAO {
	
	public Configuracoes buscaConfiguracoes(){
		if (openConnection()) {
			Configuracoes config = null;
			try {				
				comando = con.prepareStatement(ArquivosConfig.configuracoesSelect);
				ResultSet r = comando.executeQuery();
				
				if (r.next()){
					config = getConfiguracaoFromResultSet(r);
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
			return config;

		}
		return null;
	}

	public boolean alterarUltimoNumPedido(int numPedido){
		
		if(openConnection()){
			try {
				comando = con.prepareStatement(ArquivosConfig.configuracoesAlteraNumPedido);
				comando.setInt(1, numPedido);
				comando.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	private Configuracoes getConfiguracaoFromResultSet(ResultSet r) throws SQLException {
		return new Configuracoes(r.getInt(1), r.getInt(2));
	}

	


}
