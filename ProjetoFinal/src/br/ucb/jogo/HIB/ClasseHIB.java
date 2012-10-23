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

	@Override
	public List<Classe> list() {
		// TODO Auto-generated method stub
		return null;
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
