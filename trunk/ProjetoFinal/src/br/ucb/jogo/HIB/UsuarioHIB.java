package br.ucb.jogo.HIB;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import br.ucb.jogo.bean.Usuario;
import br.ucb.jogo.interfaces.HIB;

@SuppressWarnings("unused")
public class UsuarioHIB implements HIB<Usuario> {

	@Override
	public boolean save(Usuario t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(t);
		transaction.commit();
		session.close();
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> list() {
		Session session = HibernateUtil.getSession();
		try {
			return session.createCriteria(Usuario.class).list();
		} finally {
			session.close();
		}
	}

	@Override
	public boolean excluir(Usuario t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(t);
		transaction.commit();
		session.close();
		return false;
	}

	@Override
	public Usuario findTById(long id) {
		Session session = HibernateUtil.getSession();
		try {
			Usuario u = (Usuario) session.get(Usuario.class, id);
			return u;
		} finally {
			session.close();
		}
	}

}



