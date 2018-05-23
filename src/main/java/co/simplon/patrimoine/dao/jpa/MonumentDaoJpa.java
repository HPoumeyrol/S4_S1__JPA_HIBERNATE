package co.simplon.patrimoine.dao.jpa;

import javax.persistence.EntityManager;

import co.simplon.patrimoine.dao.MonumentDao;
import co.simplon.patrimoine.model.Monument;

public class MonumentDaoJpa extends AbstractDaoJpa<Monument> implements MonumentDao{

	public MonumentDaoJpa(EntityManager entityManager) {
		super(entityManager, Monument.class);
	}

	
	
}
