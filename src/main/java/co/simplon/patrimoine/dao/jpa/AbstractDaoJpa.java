package co.simplon.patrimoine.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

public abstract class AbstractDaoJpa<T> {

	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	private Class<T> objectClass;

	
	
	
	
	public AbstractDaoJpa(EntityManager entityManager, Class<T> objectClass) {
		this.entityManager = entityManager;
		this.objectClass = objectClass;
	}
	
	
	

	public T create(T obj) {
		entityManager.persist(obj);
		return obj;
	}

	public T getById(Long id) {
		return entityManager.find(objectClass, id);
	}

	public T update(T obj) {
		T result;
		result = entityManager.merge(obj);
		return result;
	}

	public void deleteById(Long id) {
		T obj = getById(id);
		entityManager.remove(obj);

	}

	
	public String GetClassName() {
		return objectClass.getName();
	}
	
	public List<T> findAll(int first, int size) {
		return entityManager.createNamedQuery(objectClass.getSimpleName() + ".findAll", objectClass).setFirstResult(first).setMaxResults(size)
				.getResultList();
	}
	
	public List<String> findAllNames(int first, int size) {
		return entityManager.createNamedQuery(objectClass.getSimpleName() + ".findAllNames", String.class).setFirstResult(first).setMaxResults(size)
				.getResultList();
	}
}
