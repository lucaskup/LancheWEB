<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List, com.lanche.controller.OpcionaisController, java.text.DecimalFormat, java.text.DecimalFormatSymbols , com.lanche.entity.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

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
  <link rel="import" href="bower_components/paper-tooltip/paper-tooltip.html">
  <link rel="import" href="css/style.html">

</head>

<body onload="animacaoEntrada()" >
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


	<paper-material elevation="2" class="paper-material-opcional">
		<h3 style="padding-left: 10px; padding-top: 10px">Opcionais</h3>
		<table id="tabela">

		  <thead>
		  <tr>
		    <th>ID</th>
		    <th>Descrição</th> 
		    <th>Preço</th>
		    <th>Data de Cadastro</th> 
		    <th>Data de Modificação</th>
		    <th>Permite mais de um</th>
		    <th></th>
		  </tr>
		  </thead>
		  <tbody>
		   <%
		  OpcionaisController c = new OpcionaisController();
		  List<Opcionais> l = c.getAll();
		  String dtCriacao;
		  String dtModificacao;
		  
		  for(Opcionais opcional: l){
			  
			  if(opcional.getDtCadastro() != null)
			  	  dtCriacao = new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(opcional.getDtCadastro());
			  else
				  dtCriacao = " ";
			  if(opcional.getDtModificacao() != null)
			  	  dtModificacao = new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(opcional.getDtModificacao());
			  else
				  dtModificacao = " ";
			  DecimalFormatSymbols custom = new DecimalFormatSymbols();
			  custom.setDecimalSeparator('.');
			  
			  DecimalFormat df = new DecimalFormat("#0.00");
			  df.setDecimalFormatSymbols(custom);
		  %>		  
		  <tr>
		    <td class="td_numeric"><%=opcional.getId()%></td>
		    <td class="td_alpha"><%=opcional.getDescricao()%></td> 
		    <td class="td_numeric"><%=df.format(opcional.getPrecoAdicional())%></td>
		    <td class="td_datetime"><%=dtCriacao%></td>
		    <td class="td_datetime"><%=dtModificacao%></td> 
		    <%if(opcional.permiteMaisQueUm()){
			    	%><td class="td_logic"><paper-checkbox checked disabled></paper-checkbox></td><%
			    }else{
			    	 %><td class="td_logic"><paper-checkbox disabled></paper-checkbox></td><%
			    }
			    %> 
		    <td class="td_icons"><paper-icon-button id="iconeeditar<%=opcional.getId()%>" onclick="dialogoEdicao('<%=opcional.getId()%>')" icon="create"></paper-icon-button><paper-icon-button id="iconeexcluir<%=opcional.getId()%>" onclick="dialogoExclusao('<%=opcional.getId()%>')" icon="delete"></paper-icon-button></td>
		  </tr>
		  <paper-tooltip for="iconeeditar<%=opcional.getId()%>">Editar Opcional</paper-tooltip>
		  <paper-tooltip for="iconeexcluir<%=opcional.getId()%>">Excluir Opcional</paper-tooltip>
		  <%  
		  }
		  %>
		  </tbody>
		</table> 
	</paper-material>
  </paper-header-panel>
</paper-drawer-panel>

<paper-fab icon="add" title="Criar Novo Opcional" tabindex="0" class="red" onclick="dialogoCriar()"></paper-fab>


    <paper-dialog id="excluir" modal with-backdrop entry-animation="scale-up-animation" exit-animation="fade-out-animation" with-backdrop>
      <h2>Confirma Exclusão</h2>
      <p>O processo de exclusão de um opcional é irreversível, confirma exclusão?</p>
		<div class="buttons">
        <paper-button class="dialogo" dialog-dismiss>Não</paper-button>
        <paper-button class="dialogo" dialog-confirm autofocus onclick="excluir()">Sim</paper-button>
		</div>
    </paper-dialog>

 
    <paper-dialog class="dialogo-lanche" id="editar" with-backdrop entry-animation="scale-up-animation" exit-animation="fade-out-animation" modal>
      <h2>Editar Opcional</h2>
      <div>
      		<form is="iron-form" id="formEditar" method="post" action="/LancheWeb/OpcionaisServlet">
				<paper-input label="id" id="de_id" name="paper-id" disabled></paper-input>	
				<input type="hidden" value="0" id="deh_id" name="id">
				<paper-input-container always-float-label>
					<label>Descrição</label>
					<input is="iron-input" id="de_descricao"  name="descricao" required auto-validate pattern="^(?!\s*$).+">
					<paper-input-error>Favor informar a Descrição!</paper-input-error>
				</paper-input-container>
				<paper-input-container always-float-label>
					<label>Preço</label>
					<input is="iron-input" id="de_preco" name="preco" required auto-validate pattern="[0-9]+(\.[0-9][0-9]?)?" allowed-pattern="[.0-9]" prevent-invalid-input>
					<paper-input-error>Favor informar o Preço!</paper-input-error>
				</paper-input-container>
				<br>
				<paper-checkbox id="de_permitemais" name="permitemais" class="green">Permite mais de um</paper-checkbox>
				<input type="hidden" id="deh_permitemais" name="permitemais">
				<input type="hidden" value="PUT" name="METHOD">
			</form>
		</div>
		<div class="buttons">
        	<paper-button class="dialogo" dialog-dismiss>Cancelar</paper-button>
        	<paper-button class="dialogo" autofocus onclick="editar()">Confirmar</paper-button>
		</div>
    </paper-dialog>

 

    <paper-dialog class="dialogo-lanche" id="criar" with-backdrop entry-animation="scale-up-animation" exit-animation="fade-out-animation" with-backdrop modal>
      <h2>Criar Opcional</h2>
      		<form is="iron-form" id="formCriar" method="post" action="/LancheWeb/OpcionaisServlet">
			<paper-input label="id" id="dc_id" disabled></paper-input>
			<input type="hidden" value="0" id="dch_id" name="id">	
			<paper-input-container>
				<label>Descrição</label>
				<input is="iron-input" id="dc_descricao"  name="descricao" required pattern="^(?!\s*$).+">
				<paper-input-error>Favor informar a Descrição!</paper-input-error>
			</paper-input-container>
			<paper-input-container always-float-label>
				<label>Preço</label>
				<input is="iron-input" id="dc_preco" name="preco" value="0.00" required auto-validate pattern="[0-9]+(\.[0-9][0-9]?)?" allowed-pattern="[.0-9]" prevent-invalid-input>
				<paper-input-error>Favor informar o Preço!</paper-input-error>
			</paper-input-container>
			<br>
			<paper-checkbox id="dc_permitemais" name="permitemais" class="green">Permite mais de um</paper-checkbox>
			<input type="hidden" id="dch_permitemais" name="permitemais">
			<input type="hidden" value="PUT" name="METHOD">
		</form>
		<div class="buttons">
        <paper-button class="dialogo" dialog-dismiss>Cancelar</paper-button>
        <paper-button class="dialogo" autofocus onclick="criar()">Confirmar</paper-button>
		</div>
    </paper-dialog>
 	<form method="post" id="formExcluir" action="/LancheWeb/OpcionaisServlet">
 		<input type="hidden" value="DELETE" name="METHOD">
 		<input type="hidden" value="0" name="id" id="dxh_id">
 	</form>

 <script>
	var idExcluir = 0;
	var idEditar = 0;
 	
 	function dialogoExclusao(id){
 		idExcluir = id;
 		 var dialog = document.getElementById('excluir');
 	      if (dialog) {
 	    	 dialog.open();
 	      }
 	}
 	function dialogoCriar(){
	 	document.getElementById('dc_descricao').value = "teste";
	 	document.getElementById('dc_descricao').validate();
	 	document.getElementById('dc_descricao').value = "";
	 	
	 	document.getElementById('dc_preco').value = "0.00";
	 	document.getElementById('dc_preco').validate();

	 	document.getElementById('dc_permitemais').checked = false;
 		 var dialog = document.getElementById('criar');
 	      if (dialog) {
 	    	 dialog.open();
 	      }
 	}
 	function dialogoEdicao(id){
 		idEditar = id;
 		var dialog = document.getElementById('editar');
    	var tabela = document.getElementById('tabela');
			for (var i = 0, row; row = tabela.rows[i]; i++) {
				if(row.cells[0].innerHTML==id){
					document.getElementById('de_id').value = id;
					document.getElementById('de_descricao').value = row.cells[1].innerHTML;
					document.getElementById('de_preco').value = row.cells[2].innerHTML;
					document.getElementById('de_permitemais').checked = row.cells[5].firstChild.checked;
					
				}
			}
			document.getElementById('de_descricao').validate();
			document.getElementById('de_preco').validate()
 	      if (dialog) {
 	    	 dialog.open();
 	      }
 	}
 	function criar(){
    	document.getElementById('dc_descricao').validate();
    	document.getElementById('dc_preco').validate();
    	if(document.getElementById('dc_permitemais').checked){
    		document.getElementById('dch_permitemais').value="1";
    	}else{
    		document.getElementById('dch_permitemais').value="0";
    	}
    	
    	if(!document.getElementById('dc_descricao').invalid && !document.getElementById('dc_preco').invalid ){
    		document.getElementById('formCriar').submit();	
    	}
 	}
 	function editar(){
 		document.getElementById('deh_id').value = document.getElementById('de_id').value;
    	document.getElementById('de_descricao').validate();
    	document.getElementById('de_preco').validate();

    	if(document.getElementById('de_permitemais').checked){
    		document.getElementById('deh_permitemais').value="1";
    	}else{
    		document.getElementById('deh_permitemais').value="0";
    	}
    	
    	if(!document.getElementById('de_descricao').invalid && !document.getElementById('de_preco').invalid ){
    		document.getElementById('formEditar').submit();	
    	}
 	}

 	function excluir(){
 		var dialog = document.getElementById('excluir');
 		if(!dialog.closingReason.canceled){
 			document.getElementById('dxh_id').value=idExcluir;
 			document.getElementById('formExcluir').submit();	
        }
 	}
	

  </script>
</body>
</html>