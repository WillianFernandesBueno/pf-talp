package br.ucb.jogo.HIB;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import br.ucb.jogo.bean.Personagem;
import br.ucb.jogo.bean.Personagem;
import br.ucb.jogo.bean.Usuario;
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
	public Personagem findTById(Integer id) {
		Session session = HibernateUtil.getSession();
		try {
			Personagem u = (Personagem) session.get(Personagem.class, id);
			return u;
		} finally {
			session.close();
		}
	}

	public Personagem findTByIdUser(Integer idUsuarios) {
		Session session = HibernateUtil.getSession();
		Criteria criteria;
		try {
			criteria = session.createCriteria(Personagem.class);
			criteria.add(Restrictions.eq("Usuarios_idUsuarios",idUsuarios));			
			@SuppressWarnings("unchecked")
			ArrayList<Personagem> p = (ArrayList<Personagem>) criteria.list();
			for (Personagem personagem : p) {
				return personagem;
			}
		} finally {
			session.close();
		}
		return null;
	}

//	
//	public Usuario findTByLogin(String login) {
//		Session session = HibernateUtil.getSession();
//		Criteria criteria;
//		try {
//			criteria = session.createCriteria(Usuario.class);
//			criteria.add(Restrictions.eq("login",login));			
//			@SuppressWarnings("unchecked")
//			ArrayList<Usuario> a = (ArrayList<Usuario>) criteria.list();
//			for (Usuario usuario : a) {
//				return usuario;
//			}
//			 
//		} finally {
//			session.close();
//		}
//		return null;
//	}
	
}



