package splett.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;

public interface Dao<T> {
	public void salvar(T obj);

	public T update(T obj);

	public void remover(T obj);

	public T findById(int id);
	
	public T findByIdInteger(Integer id);

	public T findById(long id);

	public List<T> listDesc();
	
	public List<T> listAsc();
	
	public List<T> findByNome(String nome);
	
	public void setEntityManagerFactory(EntityManagerFactory emf);
	
	public List<T> list(int first, int size);
	
	public int getRowCount();
}