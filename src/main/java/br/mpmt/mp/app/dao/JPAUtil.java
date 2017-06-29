package br.mpmt.mp.app.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	@Produces
	@ApplicationScoped
	public EntityManagerFactory criarFactory() {
		return Persistence.createEntityManagerFactory("AngularJava");
	}

	@Produces
	@RequestScoped
	public EntityManager criarEntityManager(EntityManagerFactory factory) {
		return factory.createEntityManager();
	}

	public void finaliza(@Disposes EntityManager em) {
		em.close();
	}
}
