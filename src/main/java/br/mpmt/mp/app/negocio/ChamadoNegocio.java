package br.mpmt.mp.app.negocio;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.mpmt.mp.app.dao.ChamadoDAO;
import br.mpmt.mp.app.entidade.Chamado;

@RequestScoped
public class ChamadoNegocio {
	
	@Inject
	private ChamadoDAO chamadoDAO;
	
	public List<Chamado> listarTodos() throws Exception {
		try {
			return chamadoDAO.listAll("id");
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
		}
		
		return Collections.emptyList();
	}
	
	public Chamado findById(Long id) {
		return chamadoDAO.chamadoPorId(id);
	}
	
	public void incluir(Chamado obj) throws Exception {
		chamadoDAO.persist(obj);
	}
	
	public void atualizar(Chamado obj) throws Exception {
		chamadoDAO.merge(obj);
	}
	
	public void deletar(Chamado obj) throws Exception {
		chamadoDAO.remove(obj);
	}
	
	public void deletarPorId(Long id) {
		chamadoDAO.deletarPorId(id);
	}
	
}
