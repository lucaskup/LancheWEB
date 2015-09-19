<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.lanche.controller.ConfiguracoesController, com.lanche.entity.*" %>

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
  <link rel="import" href="bower_components/neon-animation/neon-animations.html">
  <link rel="import" href="bower_components/paper-button/paper-button.html">


  <link rel="import" href="bower_components/paper-toggle-button/paper-toggle-button.html">
  

  <link rel="import" href="bower_components/paper-input/paper-input-error.html">
  <link rel="import" href="bower_components/paper-input/paper-input.html">
    <link rel="import" href="bower_components/paper-header-panel/paper-header-panel.html">
  <link rel="import" href="bower_components/paper-drawer-panel/paper-drawer-panel.html">
  <link rel="import" href="bower_components/paper-menu/paper-menu.html">
  <link rel="import" href="css/style.html">



</head>

<body>

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
	<a href="/LancheWeb/configuracoes.jsp">
          <paper-item>Configurações</paper-item>
    </a>
	</paper-menu>
	</div>
  </paper-header-panel>
  
  <paper-header-panel main mode="standard">
	<paper-toolbar>
    <paper-icon-button icon="menu" paper-drawer-toggle></paper-icon-button>
    <span class="title">Lanches WEB</span>
  </paper-toolbar>

		<%ConfiguracoesController c = new ConfiguracoesController();
		Configuracoes configuracao = c.buscaConfiguracoes();
		
		%>
  
		<paper-material elevation="2" class="paper-material-login">
		
			<h3 style="padding-left: 10px; padding-top: 10px">Configurações</h3>
			<div>
				<form is="iron-form" id="formConfig" method="post" action="/LancheWeb/ConfiguracoesServlet" style="padding-left: 30px;padding-right: 30px">
					<input type="hidden" name="METHOD" value="ZERARNUMPEDIDO">
					<paper-input-container>
						<label>Ultimo ID pedido</label>
						<input is="iron-input" type="text" id="id_pedido" name="id_pedido" readonly="readonly" value="<%=configuracao.getUltimoIDPedido()%>">
					</paper-input-container>
					<paper-input-container >
						<label>Próximo número pedido</label>
						<input is="iron-input" id="num_pedido" name="num_pedido" readonly="readonly" value="<%=configuracao.getUltimoNumeroPedido()%>">
					</paper-input-container>
					<br>
				</form>
		</div>
		<div class="buttons" style="position: relative;left: 170px;bottom: 15px"><paper-button raised  onclick="clickHandler(event)">Zerar número do Pedido</paper-button>
		</div>
		</paper-material>


  <script>

    function clickHandler(event) {
    	document.getElementById('formConfig').submit();	
        }
  </script>

</body>
</html>