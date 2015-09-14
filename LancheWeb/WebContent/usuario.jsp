<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, com.lanche.controller.UsuarioController, java.text.DecimalFormat, java.text.DecimalFormatSymbols , com.lanche.entity.*" %>
<%@page import="java.text.SimpleDateFormat"%>
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
  <link rel="import" href="bower_components/paper-dropdown-menu/paper-dropdown-menu.html">

  <link rel="import" href="bower_components/paper-item/paper-item.html">
  
  <link rel="import" href="css/style.html">
</head>

<body style="width: 100%" onload="animacaoEntrada()">

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
  </paper-header-panel>
  
  <paper-header-panel main mode="standard">
	<paper-toolbar>
    <paper-icon-button icon="menu" paper-drawer-toggle></paper-icon-button>
    <span class="title">Lanches WEB</span>
  </paper-toolbar>

	
	<paper-material elevation="2" class="paper-material-usuario">
		<h3 style="padding-left: 10px; padding-top: 10px">Usuários</h3>
		<table id="tabela">

		  <thead>
		  <tr>
		    <th>ID</th>
		    <th>Login</th> 
		    <th>Nome</th>
		    <th>Sobrenome</th> 
		    <th>Data de Nascimento</th>
		    <th>Turma</th>
		    <th>Função</th>
		    <th>Ativo</th>
		    <th></th>
		  </tr>
		  </thead>
		  <tbody>
		  
		  <%
		  UsuarioController c = new UsuarioController();
		  List<Usuario> l = c.getAll();
		  String dtNascimento;
		  
		  
		  for(Usuario usuario: l){
			  
			  if(usuario.getDataNascimento() != null)
				  dtNascimento = new SimpleDateFormat("dd/MM/yyyy").format(usuario.getDataNascimento());
			  else
				  dtNascimento = " ";
			  
			  		  %>
		<tr>
			<td class="td_numeric"><%=usuario.getId()%></td>
		    <td class="td_alpha"><%=usuario.getLogin()%></td> 
		    <td class="td_alpha"><%=usuario.getNome()%></td> 
		    <td class="td_alpha"><%=usuario.getSobrenome()%></td> 
		    <td class="td_date"><%=dtNascimento %></td>
		    <td class="td_alpha"><%=usuario.getTurma()%></td>
		    <td class="td_funcao"><%=usuario.getFuncao().toString()%></td>
		    <%if(usuario.isAtivo()){%>
		    	<td class="td_logic"><paper-checkbox checked disabled></paper-checkbox></td>	
		    <%}else{ %>
		    <td class="td_logic"><paper-checkbox disabled></paper-checkbox></td>
		    <%} %>
		    
		    <td class="td_icons"><paper-icon-button onclick="dialogoEdicao('<%=usuario.getId()%>')" icon="create"></paper-icon-button><paper-icon-button onclick="dialogoExclusao('<%=usuario.getId()%>')" icon="delete"></paper-icon-button></td>
		</tr>
			  
		  <%  
		  }
		  %>
		  
		  </tbody>
		</table> 
	</paper-material>
  </paper-header-panel>
</paper-drawer-panel>


  


<paper-fab icon="add" title="Criar Novo Usuário" tabindex="0" class="red" onclick="dialogoCriar()"></paper-fab>


    <paper-dialog id="excluir" modal with-backdrop entry-animation="scale-up-animation" exit-animation="fade-out-animation" with-backdrop>
      <h2>Confirma Exclusão</h2>
      <p>O processo de exclusão de um opcional é irreversível, confirma exclusão?</p>
		<div class="buttons">
        <paper-button class="dialogo" dialog-dismiss>Não</paper-button>
        <paper-button class="dialogo" dialog-confirm autofocus onclick="excluir()">Sim</paper-button>
		</div>
    </paper-dialog>

 
    <paper-dialog modal class="dialogo-aluno" id="editar" with-backdrop entry-animation="scale-up-animation" exit-animation="fade-out-animation" with-backdrop>
      <h2>Editar Usuário</h2>
      		<form is="iron-form" id="formEditar" method="post" action="/LancheWeb/UsuarioServlet">
			<paper-input label="id" id="de_id" disabled></paper-input>
			<input type="hidden" value="0" id="deh_id" name="id">			
			<paper-input-container always-float-label>
				<label>Login</label>
				<input is="iron-input" id="de_login"  name="login" required>
				<paper-input-error>Favor informar o login!</paper-input-error>
			</paper-input-container>
			<paper-input-container always-float-label>
				<label>Nome</label>
				<input is="iron-input" id="de_nome" name="nome" required>
				<paper-input-error>Favor informar o Nome!</paper-input-error>
			</paper-input-container>
			
			<paper-input-container always-float-label>
				<label>Sobrenome</label>
				<input is="iron-input" id="de_sobrenome"  name="sobrenome" required>
				<paper-input-error>Favor informar o Sobrenome!</paper-input-error>
			</paper-input-container>
			
			<paper-input-container always-float-label>
				<label>Data de Nascimento</label>
				<input is="iron-input" id="de_dtnascimento" name="dtnascimento" required pattern="^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$">
				<paper-input-error>Favor informar a Data de Nascimento!</paper-input-error>
			</paper-input-container>
			
			<paper-input-container always-float-label>
				<label>Turma</label>
				<input is="iron-input" id="de_turma" name="turma" required>
				<paper-input-error>Favor informar a Turma!</paper-input-error>
			</paper-input-container>
			<paper-input-container always-float-label>
				<label>Função</label>
				<input is="iron-input" id="de_funcao" name="funcao" required>
				<paper-input-error>Favor informar a Função!</paper-input-error>
			</paper-input-container>
			
			<br>
			<paper-checkbox id="de_ativo" name="ativo" class="green">Ativo</paper-checkbox>
			<input type="hidden" id="deh_ativo" name="ativo">
			<input type="hidden" value="PUT" name="METHOD">

		</form>
		<div class="buttons">
        <paper-button class="dialogo" dialog-dismiss>Cancelar</paper-button>
        <paper-button class="dialogo" dialog-confirm autofocus onclick="editar()">Confirmar</paper-button>
		</div>
    </paper-dialog>

 

    <paper-dialog modal class="dialogo-aluno" id="criar" with-backdrop entry-animation="scale-up-animation" exit-animation="fade-out-animation" with-backdrop>
      <h2>Criar Usuário</h2>
      		<form is="iron-form" id="formCriar" method="post" action="/LancheWeb/UsuarioServlet">
			<input type="hidden" value="0" id="dch_id" name="id">			
			<paper-input-container >
				<label>Login</label>
				<input is="iron-input" id="dc_login"  name="login" required>
				<paper-input-error>Favor informar o login!</paper-input-error>
			</paper-input-container>
			<paper-input-container >
				<label>Senha</label>
				<input is="iron-input" type="password" id="dc_password" name="password" required>
				<paper-input-error>Favor informar a Senha!</paper-input-error>
			</paper-input-container>
			<paper-input-container>
				<label>Nome</label>
				<input is="iron-input" id="dc_nome" name="nome" required>
				<paper-input-error>Favor informar o Nome!</paper-input-error>
			</paper-input-container>
			
			<paper-input-container>
				<label>Sobrenome</label>
				<input is="iron-input" id="dc_sobrenome"  name="sobrenome" required>
				<paper-input-error>Favor informar o Sobrenome!</paper-input-error>
			</paper-input-container>
			
			<paper-input-container always-float-label>
				<label>Data de Nascimento</label>
				<input is="iron-input" id="dc_dtnascimento" name="dtnascimento" required pattern="^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$">
				<paper-input-error>Favor informar a Data de Nascimento (DD/MM/YYYY)!</paper-input-error>
			</paper-input-container>
			
			<paper-input-container>
				<label>Turma</label>
				<input is="iron-input" id="dc_turma" name="turma" required>
				<paper-input-error>Favor informar a Turma!</paper-input-error>
			</paper-input-container>
			<paper-input-container always-float-label>
				<label>Função</label>
				<input is="iron-input" id="dc_funcao" name="funcao" required>
				<paper-input-error>Favor informar a Função!</paper-input-error>
			</paper-input-container>
			
			<br>
			<paper-checkbox id="dc_ativo" name="ativo" class="green">Ativo</paper-checkbox>
			<input type="hidden" id="dch_ativo" name="ativo">
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
		document.getElementById('dch_id').value = 0;
		document.getElementById('dc_login').value = '';
		document.getElementById('dc_nome').value = '';
		document.getElementById('dc_sobrenome').value = '';
		
		document.getElementById('dc_dtnascimento').value = 'DD/MM/YYYY';
		document.getElementById('dc_turma').value = '';
		document.getElementById('dc_funcao').value = 'ALUNO';
		
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
					document.getElementById('de_login').value = row.cells[1].innerHTML;
					document.getElementById('de_nome').value = row.cells[2].innerHTML;
					document.getElementById('de_sobrenome').value = row.cells[3].innerHTML
					
					document.getElementById('de_dtnascimento').value = row.cells[4].innerHTML;
					document.getElementById('de_turma').value = row.cells[5].innerHTML;
					document.getElementById('de_funcao').value = row.cells[6].innerHTML;
					
					document.getElementById('de_ativo').checked = row.cells[7].firstChild.checked;
				}
			}
 		
 	      if (dialog) {
 	    	 dialog.open();
 	      }
 	}
 	
 	function criar(){
 		document.getElementById('dch_id').value = 0;
		document.getElementById('dc_login').validate();
		document.getElementById('dc_nome').validate();
		document.getElementById('dc_sobrenome').validate();
		document.getElementById('dc_password').validate();
		
		document.getElementById('dc_dtnascimento').validate();
		document.getElementById('dc_turma').validate();
		document.getElementById('dc_funcao').validate();
		

    	if(document.getElementById('dc_ativo').checked){
    		document.getElementById('dch_ativo').value="1";
    	}else{
    		document.getElementById('dch_ativo').value="0";
    	}
    	
    	if(!document.getElementById('dc_login').invalid && !document.getElementById('dc_nome').invalid 
    			&& !document.getElementById('dc_sobrenome').invalid && !document.getElementById('dc_dtnascimento').invalid
    			&& !document.getElementById('dc_turma').invalid && !document.getElementById('dc_funcao').invalid
    			&& !document.getElementById('dc_password').invalid){
    		document.getElementById('formCriar').submit();	
    	}
 	}

 	function excluir(){
 		var dialog = document.getElementById('excluir');
 		if(!dialog.closingReason.canceled){
			
        	var tabela = document.getElementById('tabela');
 			for (var i = 0, row; row = tabela.rows[i]; i++) {
 				if(row.cells[0].innerHTML==idExcluir){
 					 tabela.deleteRow(i);
 				}
 			}
        }
 	}
 	
 	function editar(){
 		document.getElementById('deh_id').value = document.getElementById('de_id').value;
		document.getElementById('de_login').validate();
		document.getElementById('de_nome').validate();
		document.getElementById('de_sobrenome').validate();
		
		document.getElementById('de_dtnascimento').validate();
		document.getElementById('de_turma').validate();
		document.getElementById('de_funcao').validate();
		

    	if(document.getElementById('de_ativo').checked){
    		document.getElementById('deh_ativo').value="1";
    	}else{
    		document.getElementById('deh_ativo').value="0";
    	}
    	
    	if(!document.getElementById('de_login').invalid && !document.getElementById('de_nome').invalid 
    			&& !document.getElementById('de_sobrenome').invalid && !document.getElementById('de_dtnascimento').invalid
    			&& !document.getElementById('de_turma').invalid && !document.getElementById('de_funcao').invalid){
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