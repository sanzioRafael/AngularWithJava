package br.mpmt.mp.app.dao;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

import br.mpmt.mp.app.entidade.Chamado;

@RequestScoped
public class ChamadoDAO extends BaseDAO<Chamado> {
	
	public ChamadoDAO() {
		super(Chamado.class);
	}
	
	public int deletarPorId(Long id) {
		String sql = "DELETE Chamado WHERE id = :id";
		Query query = entityManager.createQuery(sql);
		
		query.setParameter("id", id);
		
		return query.executeUpdate();
	}
	
	public Chamado chamadoPorId(Long id) {
		String sql = "FROM Chamado WHERE id = :id";
		Query query = entityManager.createQuery(sql);
		
		query.setParameter("id", id);
		
		return (Chamado) query.getSingleResult();
	}
	
}
