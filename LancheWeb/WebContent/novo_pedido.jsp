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
   <link rel="import" href="bower_components/paper-card/paper-card.html">
  
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
  
  <paper-header-panel main mode="waterfall">
  <paper-toolbar>
    <paper-icon-button icon="menu" paper-drawer-toggle></paper-icon-button>
    <span class="title">Lanches WEB</span>
  </paper-toolbar>
  <div class="cards_centered_pedido" >

	<paper-card heading="Lanches" elevation=2 class="pedido-left">

	       		<table id="tabela">
			  	<thead>
				  <tr>
				    <th>Descrição</th> 
				    <th>Preço</th>
				    <th></th>
				  </tr>
				  </thead>
				  <tbody>
				  <%
				  LancheController c = new LancheController();
				  List<Lanche> l = c.getAtivos();
		
				  for(Lanche lanche: l){
					  
					  DecimalFormatSymbols custom = new DecimalFormatSymbols();
					  custom.setDecimalSeparator('.');
					  
					  DecimalFormat df = new DecimalFormat("#0.00");
					  df.setDecimalFormatSymbols(custom);
				  %>
					  <tr>
					  	<td class="td_hidden"><%=lanche.getId()%></td>
					    <td class="td_alpha"><%=lanche.getDescricao()%></td> 
					    <td class="td_numeric"><%=df.format(lanche.getPreco())%></td>
					    <td class="td_logic"><paper-icon-button onclick="adicionarLanche('<%=lanche.getId()%>')" icon="add-circle-outline"></paper-icon-button></td>
					    <td class="td_hidden"><%=lanche.getCSVIdOpcionais()%></td>
					  </tr>
					  
				  <%  
				  }
				  %>
				  </tbody>
				</table> 

	</paper-card>

	<paper-card heading="Pedido" elevation=2 class="pedido-center">

	       		<table id="tabela_ped">
			  	<thead>
				  <tr>
				    <th>Descrição</th> 
				    <th>Preço</th>
				    <th></th>
				  </tr>
				  </thead>
				  <tfoot>
				    <tr>
				      <td style="font-weight: bold;">Total</td>
				      <td style="font-weight: bold;" class="td_numeric">R$ 0,00</td>
				      <td></td>
				    </tr>
				  </tfoot>
				  <tbody>

				  </tbody>
				</table> 
				<div style="width: 100%;margin: 0px;padding: 0px;border-bottom:  1px solid #e0e0e0;">
				      <span style="margin-left: 10px;margin-right: 10px;font-weight: bold;">Observação</span><input id="obspedido" type="text" style="padding: 4px;margin-right: 10px;margin-top: 6px;margin-bottom: 6px" maxlength="50"></input><br>
				</div>
<paper-button  onclick="criarPedido()" class="dialogo">Criar Pedido</paper-button>


	</paper-card>

</div>	

</paper-header-panel>
</paper-drawer-panel>

 	 <form method="post" id="formCriarPedido" action="/LancheWeb/PedidoServlet">
 		<input type="hidden" value="CRIAR" name="METHOD">
 		<input type="hidden" value="0" name="pedido" id="dcp_pedido">
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
	var idItemPedido = 0;
 
 function atualizaTotalPedido(){
	var tabelaPedidoFooter = document.getElementById("tabela_ped").getElementsByTagName('tfoot')[0]; 
	var tabela = document.getElementById('tabela_ped').getElementsByTagName('tbody')[0];
	var total = 0.00;
	
	for (var k = 0, rowItemPed; rowItemPed = tabela.rows[k]; k++) {
		total += parseFloat(rowItemPed.cells[1].innerHTML);
	}
	tabelaPedidoFooter.rows[0].cells[1].innerHTML = "R$ " + total.toFixed(2);
 }
	
 function adicionarLanche(id){
		var tabelaPedido = document.getElementById("tabela_ped").getElementsByTagName('tbody')[0];
		
		var descricao = ' ';
		var preco = ' ';
		var opcionais = ' ';
		idItemPedido++;
		
		
	   	var tabela = document.getElementById('tabela');
		for (var i = 0, row; row = tabela.rows[i]; i++) {
			if(row.cells[0].innerHTML==id){
				descricao = row.cells[1].innerHTML;
				preco = row.cells[2].innerHTML;
				opcionais = row.cells[4].innerHTML;
			}
		}
		if(opcionais){
			dialogoOpcionais(opcionais);
		}
		
		
		// Create an empty <tr> element and add it to the 1st position of the tabelaPedido:
		var row = tabelaPedido.insertRow(tabelaPedido.rows.length);
		
		// Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);
		var cell4 = row.insertCell(3);
		var cell5 = row.insertCell(4);
		var cell6 = row.insertCell(5);
		
		// Add some text to the new cells:
		cell1.innerHTML = descricao;
		cell1.className="td_alpha";
		
		cell2.innerHTML = preco;
		cell2.className="td_numeric";
			
		var stringTemp = "<paper-icon-button onclick=\"excluiItemPedido('";
		stringTemp = stringTemp.concat(idItemPedido,"')\" icon=\"delete\"></paper-icon-button>");
		cell3.innerHTML = stringTemp;
		cell3.className="td_logic";
		
		cell4.innerHTML = id; //id
		cell4.className="td_hidden";

		cell5.innerHTML = ' '; //opcionais
		cell5.className="td_hidden";
		
		cell6.innerHTML = idItemPedido; 
		cell6.className="td_hidden";
		
		atualizaTotalPedido();
	 }
 	function excluiItemPedido(id){
 		var tabelaItemPedido = document.getElementById('tabela_ped');	
		for (var k = 1, rowOpcional; rowOpcional = tabelaItemPedido.rows[k]; k++) {
			if(rowOpcional.cells[5].innerHTML == id){
				tabelaItemPedido.deleteRow(k);
			} 
		}
 		atualizaTotalPedido();
 	}

 	

 	
 	function dialogoOpcionais(opcionaisCSV){
 		var opcionais = opcionaisCSV.split(",");

 		var dialog = document.getElementById('opcionais');

    	var tabelaDeOpcionais = document.getElementById('tabela_op');
    	
    	//Limpa todos os checkbox pois eles podem estar checados da outra vez
		for (var k = 0, rowOpcional; rowOpcional = tabelaDeOpcionais.rows[k]; k++) {
			rowOpcional.cells[1].firstChild.checked = false;
			rowOpcional.style.display="none";;
		}
    					
		//para cada opcional varre toda a tabela
		
		for (var k = 0, rowOpcional; rowOpcional = tabelaDeOpcionais.rows[k]; k++) {
			for (var j = 0; j < opcionais.length; j++) {
				if(rowOpcional.cells[0].innerHTML == opcionais[j]){
					rowOpcional.style.display="";
				}
			}
		}

		if (dialog) {
		dialog.open();
		}
 	}
 	
 	//Utilizado
 	function editarOpcionaisLanche(){
 		var tabelaDeOpcionais = document.getElementById('tabela_op');
 		var dialog = document.getElementById('opcionais');
 		var opcionais;
 		var valorAdicionar = 0;
 		
		for (var k = 0, rowOpcional; rowOpcional = tabelaDeOpcionais.rows[k]; k++) {
			if(rowOpcional.cells[1].firstChild.checked){
				valorAdicionar += parseFloat(rowOpcional.cells[3].innerHTML);
				if(opcionais){
					opcionais += "," + rowOpcional.cells[0].innerHTML;
				}else{
					opcionais = rowOpcional.cells[0].innerHTML;
				}
			} 
		}
		
 		if(!dialog.closingReason.canceled){
 			var tabela = document.getElementById('tabela_ped').getElementsByTagName('tbody')[0];
 			var row = tabela.rows[tabela.rows.length-1]; 
 			row.cells[4].innerHTML = opcionais;
 			row.cells[1].innerHTML = (parseFloat(row.cells[1].innerHTML) + valorAdicionar).toFixed(2);
        }
 		atualizaTotalPedido();
 	}
 	
 	function criarPedido(){
 		var itemPedido = {id:0, quantidade:0, opcionais:" ", observacao:" "};
 		
 		var tabela = document.getElementById('tabela_ped').getElementsByTagName('tbody')[0];
 		var pedido = {observacao:" ",itens:[]};
 		var jsonPedido = "";
 		pedido.observacao =  document.getElementById('obspedido').value;
		for (var k = 0, rowItemPed; rowItemPed = tabela.rows[k]; k++) {
			itemPedido = new Object();
				itemPedido.id = parseInt(rowItemPed.cells[3].innerHTML);
				itemPedido.quantidade = 1;
				itemPedido.opcionais = rowItemPed.cells[4].innerHTML;
				pedido.itens.push(itemPedido);
		}
		jsonPedido = JSON.stringify(pedido);
		alert(jsonPedido);
		document.getElementById('dcp_pedido').value=jsonPedido;
		if(jsonPedido){
			document.getElementById('formCriarPedido').submit();	
		}
		
 	}

  </script>
</body>
</html>