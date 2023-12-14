package ime.flixing.dao;

import java.util.List;


public interface GenericDao<T> {

	List<T>getAll();
	T getById(Long id);
	T getByIdEagger(Long id);
	T save(T entity);
	T update(Long id, T entity);
	void delete(Long id);
	List<T>getByName(String name);
	
}
