package com.lanche.utils;

public final class ArquivosConfig {
	
	/*Hard Coded SQL*/
	public static final String dbUser = "lanche";
	public static final String dbPass = "123";
	public static final String connectionString = "jdbc:mysql://127.0.0.1:3306/LANCHE";
	
	public static final String opcionaisSearchByID = "SELECT o.idOpcionais, o.descricao, o.precoAdicional, o.permiteMaisQueUm, o.dtCadastro, o.dtModificacao FROM lanche.opcionais as o WHERE o.idOpcionais = ?";
	public static final String opcionaisSearchByLanche = "SELECT o.idOpcionais, o.descricao, o.precoAdicional, o.permiteMaisQueUm, o.dtCadastro, o.dtModificacao FROM lanche.lanchexopcionais x JOIN lanche.opcionais as o ON x.fkOpcionais = o.idOpcionais WHERE x.fkLanche= ?"; 
	public static final String opcionaisInsert = "INSERT INTO lanche.opcionais (descricao, precoAdicional, permiteMaisQueUm, dtCadastro, dtModificacao) VALUES (?, ?, ?, ?, ?)";
	public static final String opcionaisUpdate = "UPDATE lanche.opcionais SET descricao = ?, precoAdicional = ?, permiteMaisQueUm = ?, dtCadastro = ?, dtModificacao = ? WHERE idOpcionais = ?";
	public static final String opcionaisDelete = "DELETE FROM lanche.opcionais WHERE idOpcionais = ?";
	
	
	public static final String lancheSearchByID = "SELECT lanche.idLanche, lanche.descricao, lanche.preco, lanche.dtCadastro, lanche.dtModificacao, lanche.ativo FROM lanche.lanche WHERE lanche.idLanche = ?";
	public static final String lancheSearchAll = "SELECT lanche.idLanche, lanche.descricao, lanche.preco, lanche.dtCadastro, lanche.dtModificacao, lanche.ativo FROM lanche.lanche";
	public static final String lancheInsert = "INSERT INTO lanche.lanche (descricao, preco, dtCadastro, dtModificacao, ativo) VALUES (?, ?, ?, ?, ?)";
	public static final String lancheUpdate = "UPDATE lanche.lanche SET descricao = ?, preco = ?, dtCadastro = ?, dtModificacao = ?, ativo = ? WHERE idLanche = ?";
	public static final String lancheDelete = "DELETE FROM lanche.lanche WHERE idLanche = ?";
	
	public static final String pedidoSearchByID = "SELECT p.idPedido, p.dtCriacao, p.status, u.idUsuario, u.login, u.nome, u.sobrenome, u.dataNascimento, u.turma, u.funcao, u.password, u.ativo FROM lanche.pedido p JOIN lanche.usuario u ON p.fkUsuario = u.idUsuario WHERE p.idPedido = ?";
	public static final String pedidoInsert = "INSERT INTO lanche.pedido (fkUsuario, dtCriacao, status) VALUES (?, ?, ?)";
	public static final String pedidoUpdate = "UPDATE lanche.pedido SET fkUsuario = ?, dtCriacao = ?, status = ? WHERE idPedido = ?";
	public static final String pedidoDelete = "DELETE FROM lanche.pedido WHERE idPedido = ?";
	
	public static final String itemPedidoSearchByPedido = "SELECT i.idItemPedido, i.numItem, i.quantidade, l.idLanche, l.descricao, l.preco, l.dtCadastro, l.dtModificacao, l.ativo FROM lanche.itempedido i JOIN lanche.lanche l ON i.fkLanche = l.idLanche WHERE i.fkPedido = ?";
	
	public static final String opcionaisItemPedidoSearchByItem = "SELECT o.idOpcionais, o.descricao, o.precoAdicional, o.permiteMaisQueUm, o.dtCadastro, o.dtModificacao, oi.quantidade FROM lanche.opcionaisitempedido oi JOIN lanche.opcionais o ON oi.fkOpcionais = o.idOpcionais WHERE oi.fkItemPedido = ?";
	public static final String usuarioSearchByLogin = "SELECT u.idUsuario, u.login, u.nome, u.sobrenome, u.dataNascimento, u.turma, u.funcao, u.password, u.ativo FROM lanche.usuario u WHERE u.login = ?";
	

}
