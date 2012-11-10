package br.ucb.jogo.HIB;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.ucb.jogo.bean.Resultado;
import br.ucb.jogo.interfaces.HIB;

public class ResultadoHIB implements HIB<Resultado>{

	@Override
	public boolean save(Resultado t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(t);
		transaction.commit();
		session.close();
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resultado> list() {
		Session session = HibernateUtil.getSession();
		try {
			return session.createCriteria(Resultado.class).list();
		} finally {
			session.close();
		}
	}

	@Override
	public boolean excluir(Resultado t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(t);
		transaction.commit();
		session.close();
		return false;
	}

	@Override
	public Resultado findTById(Integer id) {
		Session session = HibernateUtil.getSession();
		try {
			Resultado u = (Resultado) session.get(Resultado.class, id);
			return u;
		} finally {
			session.close();
		}
	}

	
}
