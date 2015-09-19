<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,com.lanche.controller.OpcionaisController, com.lanche.controller.LancheController, java.text.DecimalFormat, java.text.DecimalFormatSymbols , com.lanche.entity.*" %>
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
  <link rel="import" href="bower_components/paper-dialog-scrollable/paper-dialog-scrollable.html">
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

	<paper-material elevation="2" class="paper-material-lanche">
		<h3 style="padding-left: 10px; padding-top: 10px">Lanches</h3>
		<table id="tabela">

		  <thead>
		  <tr>
		    <th>ID</th>
		    <th>Descrição</th> 
		    <th>Preço</th>
		    <th>Data de Cadastro</th> 
		    <th>Data de Modificação</th>
		    <th>Ativo</th>
		    <th></th>
		  </tr>
		  </thead>
		  <tbody>
		  <%
		  LancheController c = new LancheController();
		  List<Lanche> l = c.getAll();
		  String dtCriacao;
		  String dtModificacao;
		  
		  for(Lanche lanche: l){
			  
			  if(lanche.getDtCadastro() != null)
			  	  dtCriacao = new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(lanche.getDtCadastro());
			  else
				  dtCriacao = " ";
			  if(lanche.getDtModificacao() != null)
			  	  dtModificacao = new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(lanche.getDtModificacao());
			  else
				  dtModificacao = " ";
			  DecimalFormatSymbols custom = new DecimalFormatSymbols();
			  custom.setDecimalSeparator('.');
			  
			  DecimalFormat df = new DecimalFormat("#0.00");
			  df.setDecimalFormatSymbols(custom);
		  %>
			  <tr>
			    <td class="td_numeric"><%=lanche.getId()%></td>
			    <td class="td_alpha"><%=lanche.getDescricao()%></td> 
			    <td class="td_numeric"><%=df.format(lanche.getPreco())%></td>
			    <td class="td_datetime"><%=dtCriacao%></td>
			    <td class="td_datetime"><%=dtModificacao%></td> 

			    <td class="td_logic"><%if(lanche.isAtivo()){
			    	%><iron-icon icon="check"></iron-icon></td><%
			    }else{
			    	 %><iron-icon icon="close"></iron-icon></td><%
			    }
			    %> 
			    <td class="td_icons_lanche"><paper-icon-button id='iconeopcionais<%=lanche.getId()%>' onclick="dialogoOpcionais('<%=lanche.getId()%>')" icon="<%=lanche.possuiOpcionais() ? "info" : "info-outline"%>"></paper-icon-button><paper-icon-button id='iconeeditar<%=lanche.getId()%>'  onclick="dialogoEdicao('<%=lanche.getId()%>')" icon="create"></paper-icon-button><paper-icon-button id='iconeexcluir<%=lanche.getId()%>'  onclick="dialogoExclusao('<%=lanche.getId()%>')" icon="delete"></paper-icon-button></td>
			    <td class="td_hidden"><%=lanche.getCSVIdOpcionais()%></td>
			  </tr>
			  
			  <paper-tooltip for="iconeopcionais<%=lanche.getId()%>">Este lanche <%=lanche.possuiOpcionais() ? "possui" : "não possui"%> opcionais</paper-tooltip>
			  <paper-tooltip for="iconeeditar<%=lanche.getId()%>">Editar Lanche</paper-tooltip>
			  <paper-tooltip for="iconeexcluir<%=lanche.getId()%>">Excluir Lanche</paper-tooltip>
		  <%  
		  }
		  %>

		  </tbody>
		</table> 
	</paper-material>
	
</paper-header-panel>
</paper-drawer-panel>

<paper-fab icon="add" title="Criar Novo Lanche" tabindex="0" class="red" onclick="dialogoCriar()"></paper-fab>


	<paper-dialog id="excluir" modal  with-backdrop entry-animation="scale-up-animation" exit-animation="fade-out-animation" with-backdrop>
		<h2>Confirma Exclusão</h2>
		<p>O processo de exclusão de um lanche é irreversível, confirma exclusão?</p>
	<div class="buttons">
		<paper-button class="dialogo" dialog-dismiss>Não</paper-button>
		<paper-button class="dialogo" dialog-confirm autofocus onclick="excluirLanche()">Sim</paper-button>
	</div>
	</paper-dialog>

	<paper-dialog modal class="dialogo-lanche" id="editar"  with-backdrop entry-animation="scale-up-animation" exit-animation="fade-out-animation" >
	    <h2>Editar Lanche</h2>
	    <div>
		    <form is="iron-form" id="formEditar" method="post" action="/LancheWeb/LancheServlet">
				<paper-input label="id" id="de_id" name="paper-id" disabled></paper-input>
				<input type="hidden" value="0" id="deh_id" name="id">	
				<paper-input-container always-float-label>
					<label>Descrição</label>
					<input is="iron-input" id="de_descricao"  name="descricao" required pattern="^(?!\s*$).+">
					<paper-input-error>Favor informar a Descrição!</paper-input-error>
				</paper-input-container>
				<paper-input-container always-float-label>
					<label>Preço</label>
					<input is="iron-input" id="de_preco" name="preco" required auto-validate pattern="[0-9]+(\.[0-9][0-9]?)?" allowed-pattern="[.0-9]" prevent-invalid-input>
					<paper-input-error>Favor informar o Preço!</paper-input-error>
				</paper-input-container>
				<br>
				<paper-checkbox id="de_ativo" name="paper-ativo" class="green">Ativo</paper-checkbox>
				<input type="hidden" id="deh_ativo" name="ativo">
				<input type="hidden" value="PUT" name="METHOD">
			</form>
		</div>
	<div class="buttons">
	      <paper-button class="dialogo" dialog-dismiss>Cancelar</paper-button>
	      <paper-button class="dialogo" dialog-confirm autofocus onclick="editarLanche(event)">Confirmar</paper-button>
	</div>
	</paper-dialog>
 
	<paper-dialog modal class="dialogo-lanche" id="criar" with-backdrop entry-animation="scale-up-animation" exit-animation="fade-out-animation" with-backdrop>
	<h2>Criar Lanche</h2>
	<form is="iron-form" id="formCriar" method="post" action="/LancheWeb/LancheServlet">
		<paper-input label="id" id="dc_id" disabled></paper-input>	
		
		<paper-input-container>
			<label>Descrição</label>
			<input is="iron-input" id="dc_descricao"  name="descricao" required pattern="^(?!\s*$).+">
			<paper-input-error>Favor informar a Descrição!</paper-input-error>
		</paper-input-container>
		<paper-input-container>
			<label>Preço</label>
			<input is="iron-input" id="dc_preco" name="preco" value="0.00" required auto-validate pattern="[0-9]+(\.[0-9][0-9]?)?" allowed-pattern="[.0-9]" prevent-invalid-input>
			<paper-input-error>Favor informar o Preço!</paper-input-error>
		</paper-input-container>
		<br>
		<paper-checkbox id="dc_ativo" name="paper-ativo" class="green">Ativo</paper-checkbox>
		<input type="hidden" id="dch_ativo" name="ativo">
		<input type="hidden" value="0" name="id">
		<input type="hidden" value="PUT" name="METHOD">
	</form>
	<div class="buttons">
	      <paper-button class="dialogo" dialog-dismiss>Cancelar</paper-button>
	      <paper-button class="dialogo" autofocus onclick="criarLanche(event)">Confirmar</paper-button>
	</div>
	</paper-dialog>
 	<form method="post" id="formExcluir" action="/LancheWeb/LancheServlet">
 		<input type="hidden" value="DELETE" name="METHOD">
 		<input type="hidden" value="0" name="id" id="dxh_id">
 	</form>
 	 <form method="post" id="formOpcionais" action="/LancheWeb/LancheServlet">
 		<input type="hidden" value="OPCIONAIS" name="METHOD">
 		<input type="hidden" value="0" name="id" id="doh_id">
 		<input type="hidden" value="0" name="idOpcionais" id="doh_opcionais">
 	</form>

	<paper-dialog modal class="dialogo-lanche-op" id="opcionais"  with-backdrop entry-animation="scale-up-animation" exit-animation="fade-out-animation" >
	    <h2>Opcionais</h2>
	    <paper-dialog-scrollable>
	    <div>
	    
	    <table id="tabela_op">

		  <thead>
		  <tr>
		    <th>Selecionado</th>
		    <th>Descrição</th> 
		    <th>Preço</th>
		  </tr>
		  </thead>
		  <tbody>
		   <%
		  OpcionaisController co = new OpcionaisController();
		  List<Opcionais> lo = co.getAll();
		  lo.sort(null);
		  
		  for(Opcionais opcional: lo){
			  
			 
			  DecimalFormatSymbols custom = new DecimalFormatSymbols();
			  custom.setDecimalSeparator('.');
			  
			  DecimalFormat df = new DecimalFormat("#0.00");
			  df.setDecimalFormatSymbols(custom);
		  %>		  
		  <tr>
		  	<td class="td_hidden"><%=opcional.getId()%></td>
		    <td class="td_logic"><paper-checkbox></paper-checkbox></td>
		    <td class="td_alpha"><%=opcional.getDescricao()%></td> 
		    <td class="td_numeric"><%=df.format(opcional.getPrecoAdicional())%></td>
		  </tr>
		  <%  
		  }
		  %>
		  </tbody>
		</table> 
	    
		    
		</div>
		</paper-dialog-scrollable>
	<div class="buttons">
	      <paper-button class="dialogo" dialog-dismiss>Cancelar</paper-button>
	      <paper-button class="dialogo" dialog-confirm autofocus onclick="editarOpcionaisLanche()">Confirmar</paper-button>
	</div>
	</paper-dialog>
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
	 	document.getElementById('dc_descricao').value = "";
	 	document.getElementById('dc_preco').value = "0.00";
	 	document.getElementById('dc_ativo').checked = true;
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
					document.getElementById('de_ativo').checked = row.cells[5].innerHTML.indexOf("check")>-1;
				}
			}
 		
 	      if (dialog) {
 	    	 dialog.open();
 	      }
 	}
 	
 	function dialogoOpcionais(id){
 		idEditar = id;
 		var dialog = document.getElementById('opcionais');
    	var tabela = document.getElementById('tabela');
    	var tabelaDeOpcionais = document.getElementById('tabela_op');
    	
    	//Limpa todos os checkbox pois eles podem estar checados da outra vez
		for (var k = 0, rowOpcional; rowOpcional = tabelaDeOpcionais.rows[k]; k++) {
			rowOpcional.cells[1].firstChild.checked = false;
		}
    	
		for (var i = 0, row; row = tabela.rows[i]; i++) {
			if(row.cells[0].innerHTML==id){
				var opcionais = row.cells[7].innerHTML.split(",");
				//para cada opcional varre toda a tabela
				for (var j = 0; j < opcionais.length; j++) {
					for (var k = 0, rowOpcional; rowOpcional = tabelaDeOpcionais.rows[k]; k++) {
						if(rowOpcional.cells[0].innerHTML==opcionais[j]){
							rowOpcional.cells[1].firstChild.checked = true;
						}
					}
				}
			}
		}
 		
		if (dialog) {
		dialog.open();
		}
 	}
 	
 	function criarLanche(event){
 		
    	document.getElementById('dc_descricao').validate();
    	document.getElementById('dc_preco').validate();
    	if(document.getElementById('dc_ativo').checked){
    		document.getElementById('dch_ativo').value="1";
    	}else{
    		document.getElementById('dch_ativo').value="0";
    	}
    	
    	if(!document.getElementById('dc_descricao').invalid && !document.getElementById('dc_preco').invalid ){
    		document.getElementById('formCriar').submit();	
    	}
 		
 		
 	}
 	function editarLanche(event){
 		
    	document.getElementById('de_descricao').validate();
    	document.getElementById('de_preco').validate();
    	document.getElementById('deh_id').value = document.getElementById('de_id').value;
    	if(document.getElementById('de_ativo').checked){
    		document.getElementById('deh_ativo').value="1";
    	}else{
    		document.getElementById('deh_ativo').value="0";
    	}
    	
    	if(!document.getElementById('de_descricao').invalid && !document.getElementById('de_preco').invalid ){
    		document.getElementById('formEditar').submit();	
    	}
 	}

 	function excluirLanche(){
 		var dialog = document.getElementById('excluir');
 		if(!dialog.closingReason.canceled){
 			document.getElementById('dxh_id').value=idExcluir;
 			document.getElementById('formExcluir').submit();	
        }
 	}
 	
 	function editarOpcionaisLanche(){
 		var tabelaDeOpcionais = document.getElementById('tabela_op');
 		var dialog = document.getElementById('opcionais');
 		var opcionais;
 		
		for (var k = 0, rowOpcional; rowOpcional = tabelaDeOpcionais.rows[k]; k++) {
			if(rowOpcional.cells[1].firstChild.checked){
				if(opcionais){
					opcionais += "," + rowOpcional.cells[0].innerHTML;
				}else{
					opcionais = rowOpcional.cells[0].innerHTML;
				}
			} 
		}
 		
 		if(!dialog.closingReason.canceled){
 			document.getElementById('doh_id').value=idEditar;
 			document.getElementById('doh_opcionais').value=opcionais;
 			document.getElementById('formOpcionais').submit();	
        }
 	}

  </script>
</body>
</html>