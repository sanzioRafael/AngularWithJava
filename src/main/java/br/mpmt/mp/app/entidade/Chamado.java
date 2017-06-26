package br.mpmt.mp.app.entidade;

import java.io.Serializable;

import br.mpmt.mp.app.util.enumerado.Status;
import lombok.Data;

@Data
public class Chamado implements Serializable {
	
	private static final long serialVersionUID = -7169120359918277571L;
	
	private Long id;
	
	private String assunto;
	
	private String mensagem;
	
	private Status status;
	
}