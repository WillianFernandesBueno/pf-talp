package br.ucb.jogo.HIB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import br.ucb.jogo.bean.Autorizacao;
import br.ucb.jogo.bean.Usuario;
import br.ucb.jogo.interfaces.HIB;

@SuppressWarnings("unused")
public class UsuarioHIB implements HIB<Usuario>, Serializable {

	private static final long serialVersionUID = 1L;

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
	public Usuario findTById(Integer id) {
		Session session = HibernateUtil.getSession();
		try {
			Usuario u = (Usuario) session.get(Usuario.class, id);
			return u;
		} finally {
			session.close();
		}
	}

	public Usuario findTByLogin(String login) {
		Session session = HibernateUtil.getSession();
		Criteria criteria;
		try {
			criteria = session.createCriteria(Usuario.class);
			criteria.add(Restrictions.eq("login",login));			
			@SuppressWarnings("unchecked")
			ArrayList<Usuario> a = (ArrayList<Usuario>) criteria.list();
			for (Usuario usuario : a) {
				return usuario;
			}
			 
		} finally {
			session.close();
		}
		return null;
	}

}



