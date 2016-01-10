package com.lanche.boundary.ws;

import javax.jws.WebService;

import com.lanche.boundary.ws.sei.WebServicesSEI;
import com.lanche.controller.LoginController;
import com.lanche.controller.PedidoController;

@WebService(endpointInterface="com.lanche.boundary.ws.sei.WebServicesSEI")
public class WebServices implements WebServicesSEI  {
	
	/* (non-Javadoc)
	 * @see com.lanche.boundary.ws.WebServicesSEI#getToken(java.lang.String, java.lang.String)
	 */
	@Override
	public String getToken(String login, String senha){
		LoginController c = new LoginController();
		return c.getToken(login, senha);
	}
	
	/* (non-Javadoc)
	 * @see com.lanche.boundary.ws.WebServicesSEI#cadastraPedido(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public int cadastraPedido(String login, String token, String pedidoJson){
		PedidoController c = new PedidoController();
		
		
		return c.criarPedidoJson(login,token,pedidoJson);
	}

}
