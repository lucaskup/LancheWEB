package com.lanche.entity.enums;

public enum Funcao {
	ALUNO(1), PROFESSOR(2), ATENDENTE(3);
	
	private final int funcao;

	Funcao(int valorFuncao) {
		funcao = valorFuncao;
	}

	public int getValor() {
		return funcao;
	}
	
	public static Funcao getFuncao (int funcao)
	{
		switch (funcao) {
		case 1:
			return ALUNO;
		case 2:
			return PROFESSOR;
		default:
			return ATENDENTE;
		}

	}
}
