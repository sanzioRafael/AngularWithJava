package br.mpmt.mp.app.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.mpmt.mp.app.util.enumerado.Status;
import lombok.Data;

@Entity
@Table()
@Data
public class Chamado implements Serializable {

	private static final long serialVersionUID = -7169120359918277571L;

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(length = 256, nullable = false)
	private String assunto;

	@Column(length = 2048, nullable = false)
	private String mensagem;

	@Column(length = 32, nullable = false)
	private Status status;

}