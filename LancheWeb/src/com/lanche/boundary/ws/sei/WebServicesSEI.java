package com.lanche.boundary.ws.sei;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name="WebServicesService", portName="WebServicesPort", targetNamespace="lanche")
public interface WebServicesSEI {
    @WebMethod
	public String getToken(@WebParam(name="login")String login, @WebParam(name="senha")String senha);
    @WebMethod
	public int cadastraPedido(@WebParam(name="login")String login, @WebParam(name="token")String token, @WebParam(name="pedidoJson")String pedidoJson);

}