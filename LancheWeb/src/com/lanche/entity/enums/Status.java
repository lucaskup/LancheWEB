package com.lanche.entity.enums;

public enum Status {
	
	CADASTRADO(0), FAZENDO(1), PRONTO(2), RETIRADO(3), CANCELADO(4);
	
	private final int status;

	Status(int valorStatus) {
		status = valorStatus;
	}

	public int getValor() {
		return status;
	}
	public static Status getStatus (int status)
	{
		switch (status) {
		case 0:
			return CADASTRADO;
		case 1:
			return FAZENDO;
		case 2:
			return PRONTO;
		case 3:
			return RETIRADO;
		case 4:
			return CANCELADO;
		default:
			return RETIRADO;
		}

	}
}
