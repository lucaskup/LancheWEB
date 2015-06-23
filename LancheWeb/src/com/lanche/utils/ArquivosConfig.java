package com.lanche.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class ArquivosConfig {
	private static ArquivosConfig arquivos;
	private static final String propertiesFileName = "db.properties";
	/*Hard Coded names of Properties*/
	public static final String dbUser = "db_user";
	public static final String dbPass = "db_pass";
	public static final String connectionString = "connection_string";
	
	public static final String opcionaisSearchByID = "opcionais_search_by_id";
	public static final String opcionaisSearchByLanche = "opcionais_search_by_lanche";
	public static final String opcionaisInsert = "opcionais_insert";
	public static final String opcionaisUpdate = "opcionais_update";
	public static final String opcionaisDelete = "opcionais_delete";
	
	
	public static final String lancheSearchByID = "lanche_search_by_id";
	public static final String lancheSearchAll = "lanche_search_all";
	public static final String lancheInsert = "lanche_insert";
	public static final String lancheUpdate = "lanche_update";
	public static final String lancheDelete = "lanche_delete";
	
	public static final String pedidoSearchByID = "pedido_search_by_id";
	public static final String pedidoInsert = "pedido_insert";
	public static final String pedidoUpdate = "pedido_update";
	public static final String pedidoDelete = "pedido_delete";
	
	public static final String itemPedidoSearchByPedido = "itempedido_search_by_pedido";
	
	public static final String opcionaisItemPedidoSearchByItem = "opcionaisitempedido_search_by_item";
	public static final String usuarioSearchByLogin = "usuario_search_by_login";
	
	

	
	private Properties propertiesFile;
	
	public String getProperty(String property){
		return propertiesFile.getProperty(property).trim();
	}
	
	private ArquivosConfig(){
		
		
		Properties prop = new Properties();
		try {

			prop.load(new FileInputStream(propertiesFileName));
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static ArquivosConfig getInstance(){
		if(arquivos == null){
			arquivos =  new ArquivosConfig();
		}
		return arquivos;
	}

}
