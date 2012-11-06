package br.ucb.jogo.interfaces;

import java.util.List;

public interface HIB<T> {
	
	public boolean save(T t);
	public List<T> list();
	public boolean excluir(T t);
	public T findTById(Integer id);

}
