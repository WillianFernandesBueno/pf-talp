package br.ucb.jogo.HIB;

import java.util.List;

import org.hibernate.Criteria;
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
			return session.createCriteria(Classe.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		} finally {
			session.close();
		}
	}

	@Override
	public boolean excluir(Classe t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(t);
		transaction.commit();
		session.close();
		return false;
	}

	@Override
	public Classe findTById(Integer id) {
		Session session = HibernateUtil.getSession();
		try {
			Classe u = (Classe) session.get(Classe.class, id);
			return u;
		} finally {
			session.close();
		}
	}
}
