package br.mpmt.mp.app.util.enumerado;

import lombok.Getter;

public enum Status {
	
	NOVO ("01", "NOVO"),
	FECHADO ("02", "FECHADO");
	
	@Getter
	private String valor;
	
	@Getter
	private String descricao;
	
	Status(String valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}
	
}
