<%@page import="com.lanche.entity.enums.Status"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,com.lanche.controller.PedidoController, com.lanche.controller.PedidoController, java.text.DecimalFormat, java.text.DecimalFormatSymbols , com.lanche.entity.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, initial-scale=1, user-scalable=yes">
	
<title>Lanche WEB</title>


  <script src="bower_components/webcomponentsjs/webcomponents-lite.js"></script>

  <link rel="import" href="bower_components/iron-icon/iron-icon.html">
  <link rel="import" href="bower_components/iron-icons/iron-icons.html">
  <link rel="import" href="bower_components/iron-icons/communication-icons.html">
  <link rel="import" href="bower_components/iron-icons/hardware-icons.html">
  
  <link rel="import" href="bower_components/paper-checkbox/paper-checkbox.html">
  <link rel="import" href="bower_components/paper-toolbar/paper-toolbar.html">
  <link rel="import" href="bower_components/paper-icon-button/paper-icon-button.html">
  
  <link rel="import" href="bower_components/paper-item/paper-icon-item.html">
  <link rel="import" href="bower_components/paper-item/paper-item.html">
  <link rel="import" href="bower_components/paper-item/paper-item-body.html">
  <link rel="import" href="bower_components/paper-styles/paper-styles.html">
  <link rel="import" href="bower_components/paper-material/paper-material.html">
  <link rel="import" href="bower_components/paper-fab/paper-fab.html">
  
  <link rel="import" href="bower_components/paper-dialog/paper-dialog.html">
  <link rel="import" href="bower_components/paper-dialog-scrollable/paper-dialog-scrollable.html">
  <link rel="import" href="bower_components/neon-animation/neon-animations.html">
  <link rel="import" href="bower_components/paper-button/paper-button.html">
  <link rel="import" href="bower_components/paper-card/paper-card.html">


  <link rel="import" href="bower_components/paper-input/paper-input-error.html">
  <link rel="import" href="bower_components/paper-input/paper-input.html">
  <link rel="import" href="bower_components/paper-header-panel/paper-header-panel.html">
  <link rel="import" href="bower_components/paper-drawer-panel/paper-drawer-panel.html">
  <link rel="import" href="bower_components/paper-menu/paper-menu.html">
  <link rel="import" href="bower_components/paper-toggle-button.html">
  <link rel="import" href="bower_components/paper-tooltip/paper-tooltip.html">
  <link rel="import" href="css/style.html">

</head>

<body onload="animacaoEntrada()">

<paper-drawer-panel id="paperDrawerPanel" force-narrow="true">
  <paper-header-panel drawer mode="waterfall">
   
	<paper-toolbar>
		<paper-icon-button icon="menu" paper-drawer-toggle></paper-icon-button>
    	<span class="title">Menu</span>
  	</paper-toolbar>
   
    <div>
    
    <paper-menu class="list">
    <a href="/LancheWeb/pedido.jsp">
          <paper-item>Pedidos</paper-item>
    </a>
    <a href="/LancheWeb/lanche.jsp">
          <paper-item>Lanches</paper-item>
    </a>
    <a href="/LancheWeb/opcional.jsp">
          <paper-item>Opcionais</paper-item>
    </a>
    <a href="/LancheWeb/usuario.jsp">
          <paper-item>Usuários</paper-item>
    </a>

	</paper-menu>
	</div>
	<%
		   PedidoController c = new PedidoController();
		   List<Pedido> l = null;
		   Cookie cookie = null;
  		   Cookie[] cookies = null;
  		   boolean exibirApenasHoje = true;
  		   boolean exibirPronto = true;
  		   boolean exibirFazendo = true;
  	       boolean exibirCadastrado = true;
  		   // Get an array of Cookies associated with this domain
  		   cookies = request.getCookies();
  		   if( cookies != null ){
  		      for (int i = 0; i < cookies.length; i++){
  		    	cookie = cookies[i];
  		    	if(cookie.getName().equals("conf_apenas_hoje") && cookie.getValue( ).equals("0")){
  		    		exibirApenasHoje = false;
  		    	}
  		    	if(cookie.getName().equals("conf_visualiza_pronto") && cookie.getValue( ).equals("0")){
  		    		exibirPronto = false;
  		    	}
  		    	if(cookie.getName().equals("conf_visualiza_fazendo") && cookie.getValue( ).equals("0")){
  		    		exibirFazendo = false;
  		    	}
  		    	if(cookie.getName().equals("conf_visualiza_cadastrado") && cookie.getValue( ).equals("0")){
  		    		exibirCadastrado = false;
  		    	}
  		      }
  		  }
  		 l = c.getAll(exibirApenasHoje,exibirCadastrado,exibirFazendo,exibirPronto);
  		   
  		   %>
  </paper-header-panel>
  
  <paper-header-panel main mode="standard">
  <paper-toolbar>
    <paper-icon-button icon="menu" paper-drawer-toggle></paper-icon-button>
    <span class="title">Lanches WEB</span>
	<paper-icon-button icon="icons:filter-list" onclick="abreMenuVisualiza()"></paper-icon-button>
  </paper-toolbar>

  <div class="cards_centered">

  		  <%

  		  

		  String dtCriacao;
		  String dtModificacao;
		  
		  for(Pedido pedido: l){
			  
			  if(pedido.getDtCriacao() != null)
			  	  dtCriacao = new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(pedido.getDtCriacao());
			  else
				  dtCriacao = " ";
			  
			  DecimalFormatSymbols custom = new DecimalFormatSymbols();
			  custom.setDecimalSeparator('.');
			  
			  DecimalFormat df = new DecimalFormat("#0.00");
			  df.setDecimalFormatSymbols(custom);
			  			  
		  %>
		  
	<paper-card class="primeiroEstagio" elevation=2>
      <div class="card-content <%=pedido.getStatus() == Status.PRONTO ? "titulo_card_pronto" : pedido.getStatus() == Status.FAZENDO ? "titulo_card_fazendo" : "titulo_card_cadastrado"%>">
      	<iron-icon id="icone<%=pedido.getId()%>" icon="<%=pedido.getUsuario() == null ? "hardware:laptop" :"hardware:phone-android"%>" style="margin-right:7px;"></iron-icon>Pedido <%=pedido.getNumPedido()%> (<%=pedido.getStatus().toString()%>)
      </div>
      
      <div class="card-content">      
       		<%for(ItemPedido item:pedido.getItens()){%>
        	<div class="card_item">
        		<%=item.getQuantidade()%> - <%=item.getLanche().getDescricao()%> - <%=df.format(item.getTotal())%> 
        	</div>
        	<%for(OpcionaisDoItem op:item.getOpcionais()){%>
        	<div class="card_item_opcional">
        		<%=op.getOpcional().getDescricao() %>
        	</div><%}%><%}%>
        	<div class="card_total" style="text-align: right;">Total R$ <%=df.format(pedido.getTotal())%>
        	</div>
      </div>
      <div class="card-actions">
        <paper-button onclick="passarParaFazendo('<%=pedido.getId()%>')">Passar para Fazendo</paper-button>
        <paper-button onclick="passarParaPronto('<%=pedido.getId()%>')">Passar para Pronto</paper-button>
      </div>
    </paper-card>
    <paper-tooltip for="icone<%=pedido.getId()%>"><%="Cadastro por "+pedido.getNomeUsuarioCriacao()%></paper-tooltip>
			  
		  <%  
		  }
		  %>
</div>
</paper-header-panel>
</paper-drawer-panel>

<paper-fab icon="add" title="Criar Novo Pedido" tabindex="0" class="red" onclick="criarPedido()"></paper-fab>

 	<form method="post" id="formPronto" action="/LancheWeb/PedidoServlet">
 		<input type="hidden" value="PRONTO" name="METHOD">
 		<input type="hidden" value="0" name="id" id="fp_id">
 	</form>

 	 <form method="post" id="formFazendo" action="/LancheWeb/PedidoServlet">
 		<input type="hidden" value="FAZENDO" name="METHOD">
 		<input type="hidden" value="0" name="id" id="ff_id">
 	</form>
 	
 	 <form method="post" id="formCriarPed" action="/LancheWeb/PedidoServlet">
 		<input type="hidden" value="CRIARPED" name="METHOD">
 		<input type="hidden" value="0" name="id" id="doh_id">
 	</form>

	<paper-dialog modal id="dialogo-pedido-conf"  with-backdrop entry-animation="scale-up-animation" exit-animation="fade-out-animation" >
	    <h2>Ver Pedidos</h2>
		<paper-checkbox id="conf_visualiza_pronto" <%=exibirPronto ? "checked":" "%> class="green_v">Pronto</paper-checkbox><br>
		<paper-checkbox id="conf_visualiza_fazendo" <%=exibirFazendo ? "checked":" "%> class="orange_v">Fazendo</paper-checkbox><br>
		<paper-checkbox id="conf_visualiza_cadastrado" <%=exibirCadastrado ? "checked":" "%> class="red_v">Cadastrado</paper-checkbox><br>
		<paper-checkbox id="conf_apenas_hoje" <%=exibirApenasHoje ? "checked":" "%>>Apenas pedidos feitos hoje</paper-checkbox>
		<div class="buttons">
		      <paper-button class="dialogo" dialog-dismiss>Cancelar</paper-button>
		      <paper-button class="dialogo" dialog-confirm autofocus onclick="salvaCookiesRecarregaPagina()">Confirmar</paper-button>
		</div>
	</paper-dialog>


	
 <script>
	function setCookie(cname, cvalue, exdays) {
	    var d = new Date();
	    d.setTime(d.getTime() + (exdays*24*60*60*1000));
	    var expires = "expires="+d.toUTCString();
	    document.cookie = cname + "=" + cvalue + "; " + expires;
	}
	function salvaCookiesRecarregaPagina(){	
		 if(document.getElementById('conf_visualiza_pronto').checked){
			 setCookie("conf_visualiza_pronto",1,7);
		 }else{
			 setCookie("conf_visualiza_pronto",0,7);
		 }
		 if(document.getElementById('conf_visualiza_fazendo').checked){
			 setCookie("conf_visualiza_fazendo",1,7);
		 }else{
			 setCookie("conf_visualiza_fazendo",0,7);
		 }
		 if(document.getElementById('conf_visualiza_cadastrado').checked){
			 setCookie("conf_visualiza_cadastrado",1,7);
		 }else{
			 setCookie("conf_visualiza_cadastrado",0,7);
		 }
		 if(document.getElementById('conf_apenas_hoje').checked){
			 setCookie("conf_apenas_hoje",1,7);
		 }else{
			 setCookie("conf_apenas_hoje",0,7);
		 }
		 
		 location.reload();
	}
		
	

	var idExcluir = 0;
	var idEditar = 0;
	function passarParaPronto(id){
		document.getElementById('fp_id').value=id;
 		document.getElementById('formPronto').submit();	
	}
	function passarParaFazendo(id){
		document.getElementById('ff_id').value=id;
 		document.getElementById('formFazendo').submit();	
	}
 	function criarPedido(){
 		document.getElementById('formCriarPed').submit();
 	}
 	function abreMenuVisualiza(){
 		var dialog = document.getElementById('dialogo-pedido-conf');
		if (dialog) {
			dialog.open();
		}
 	}


	function animacaoEntrada(){
		var d = document.getElementsByClassName("primeiroEstagio");
		
		while (d.length > 0) {
			d[0].classList.toggle("segundoEstagio");
			d[0].classList.toggle("primeiroEstagio");
		}		
	}

  </script>
</body>
</html>