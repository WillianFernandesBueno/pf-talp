package br.ucb.jogo.HIB;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import br.ucb.jogo.bean.Personagem;
import br.ucb.jogo.bean.Personagem;
import br.ucb.jogo.interfaces.HIB;

@SuppressWarnings("unused")
public class PersonagemHIB implements HIB<Personagem> {

	@Override
	public boolean save(Personagem t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(t);
		transaction.commit();
		session.close();
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Personagem> list() {
		Session session = HibernateUtil.getSession();
		try {
			return session.createCriteria(Personagem.class).list();
		} finally {
			session.close();
		}
	}

	@Override
	public boolean excluir(Personagem t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(t);
		transaction.commit();
		session.close();
		return false;
	}

	@Override
	public Personagem findTById(long id) {
		Session session = HibernateUtil.getSession();
		try {
			Personagem u = (Personagem) session.get(Personagem.class, id);
			return u;
		} finally {
			session.close();
		}
	}

}



