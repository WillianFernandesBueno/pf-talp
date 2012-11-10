package br.ucb.jogo.HIB;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.ucb.jogo.bean.Desafio;
import br.ucb.jogo.interfaces.HIB;

public class DesafioHIB implements HIB<Desafio>{

	@Override
	public boolean save(Desafio t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(t);
		transaction.commit();
		session.close();
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Desafio> list() {
		Session session = HibernateUtil.getSession();
		try {
			return session.createCriteria(Desafio.class).list();
		} finally {
			session.close();
		}
	}

	@Override
	public boolean excluir(Desafio t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(t);
		transaction.commit();
		session.close();
		return false;
	}

	@Override
	public Desafio findTById(Integer id) {
		Session session = HibernateUtil.getSession();
		try {
			Desafio u = (Desafio) session.get(Desafio.class, id);
			return u;
		} finally {
			session.close();
		}
	}

	
}
