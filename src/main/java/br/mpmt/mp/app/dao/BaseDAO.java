package br.mpmt.mp.app.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lombok.Getter;
import lombok.Setter;

@Dependent
public class BaseDAO<T> {
	
	@PersistenceContext
	protected EntityManager entityManager;

	@Getter @Setter
	protected Class<T> objClass = null;

	public BaseDAO() {
		
	}

	public BaseDAO(Class<T> objClass) {
		setObjClass(objClass);
	}
	
	public void persist(final T obj) throws Exception {
		entityManager.persist(obj);
	}

	public void merge(final T obj) throws Exception {
		entityManager.merge(obj);
	}

	public void refresh(final T obj) throws Exception {
		entityManager.refresh(obj);
	}

	public void remove(final T obj) throws Exception {
		entityManager.remove(obj);
	}

	public void flush() throws Exception {
		entityManager.flush();
	}

	@SuppressWarnings("unchecked")
	public T findByID(Long id) {
		T obj = ((T) entityManager.find(objClass.getClass(), id));
		return obj;
	}

	@SuppressWarnings("unchecked")
	public T findByField(String[] fieldOnTable, Object[] expression) throws Exception {
		List<T> lista = new ArrayList<T>();
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT x FROM " + objClass.getName() + " x ");
		sb.append("WHERE 1 = 1 ");

		for (int i = 0; i < fieldOnTable.length; i++) {
			sb.append(" AND " + fieldOnTable[i] + " = :field" + i);
		}

		Query query = entityManager.createQuery(sb.toString());

		for (int i = 0; i < fieldOnTable.length; i++) {
			query.setParameter("field" + i, expression[i]);
		}

		lista = query.getResultList();

		if (lista.isEmpty()) {
			return null;
		}

		return lista.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<T> listAll() throws Exception {
		List<T> lista = new ArrayList<T>();
		Query query = entityManager.createQuery("SELECT x FROM " + objClass.getName() + " x");
		lista = query.getResultList();
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<T> listAll(String orderBy) throws Exception {
		List<T> lista = new ArrayList<T>();
		Query query = entityManager.createQuery("SELECT x FROM " + objClass.getName() + " x ORDER BY x." + orderBy);
		lista = query.getResultList();
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<T> listByField(String[] attributes, Object[] values, String... orderers) throws Exception {
		List<T> lista = new ArrayList<T>();
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT x FROM " + objClass.getName() + " x ");
		sb.append("WHERE 1 = 1 ");

		for (int i = 0; i < attributes.length; i++) {
			sb.append(" AND " + attributes[i] + " = :field" + i);
		}

		for (int i = 0; i < orderers.length; i++) {
			if (i == 0) {
				sb.append(" ORDER BY " + orderers[i]);
			} else {
				sb.append("," + orderers[i]);
			}
		}

		Query query = entityManager.createQuery(sb.toString());

		for (int i = 0; i < attributes.length; i++) {
			query.setParameter("field" + i, values[i]);
		}

		lista = query.getResultList();

		return lista;
	}

}
