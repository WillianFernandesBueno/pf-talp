package br.ucb.jogo.HIB;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.ucb.jogo.bean.Autorizacao;
import br.ucb.jogo.interfaces.HIB;

public class LoginHib implements HIB<Autorizacao>, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public boolean save(Autorizacao t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(t);
		transaction.commit();
		session.close();
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Autorizacao> list() {
		Session session = HibernateUtil.getSession();
		try {
			return session.createCriteria(Autorizacao.class).list();
		} finally {
			session.close();
		}
	}

	@Override
	public boolean excluir(Autorizacao t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(t);
		transaction.commit();
		session.close();
		return false;
	}

	@Override
	public Autorizacao findTById(long id) {
		Session session = HibernateUtil.getSession();
		try {
			Autorizacao u = (Autorizacao) session.get(Autorizacao.class, id);
			return u;
		} finally {
			session.close();
		}
	}
}
