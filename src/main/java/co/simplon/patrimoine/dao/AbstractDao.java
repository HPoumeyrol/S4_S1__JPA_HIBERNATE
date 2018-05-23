package co.simplon.patrimoine.dao;

import java.util.List;

public abstract interface AbstractDao<T> {
	
	T create(T obj);
	
	T getById(Long id);
	
	T update(T obj);
	
	void deleteById(Long id);
	
	List<T> findAll(int first, int size);
	
	List<String> findAllNames(int first, int size);
	

}
