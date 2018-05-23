package co.simplon.patrimoine.dao.jpa;

import javax.persistence.EntityManager;

import co.simplon.patrimoine.dao.UserDao;
import co.simplon.patrimoine.model.User;

public class UserDaoJpa extends AbstractDaoJpa<User> implements UserDao{

	public UserDaoJpa(EntityManager entityManager) {
		super(entityManager, User.class);
	}


	
	
}
