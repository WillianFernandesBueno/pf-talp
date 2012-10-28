package br.ucb.jogo.HIB;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.ucb.jogo.bean.Classe;
import br.ucb.jogo.interfaces.HIB;

public class ClasseHIB implements HIB<Classe>{

	@Override
	public boolean save(Classe t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(t);
		transaction.commit();
		session.close();
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Classe> list() {
		Session session = HibernateUtil.getSession();
		try {
			System.out.println("Entrou no listar!");
			
			List<Classe> classes = session.createCriteria(Classe.class).list();
			System.out.println("Antes do retorno!!!!");
			return classes;
			
			//return session.createCriteria(Classe.class).list();
		} finally {
			session.close();
		}
	}

	@Override
	public boolean excluir(Classe t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Classe findTById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
