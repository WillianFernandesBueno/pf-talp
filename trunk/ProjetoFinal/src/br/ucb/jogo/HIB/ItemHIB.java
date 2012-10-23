package br.ucb.jogo.HIB;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import br.ucb.jogo.bean.Item;
import br.ucb.jogo.interfaces.HIB;

@SuppressWarnings("unused")
public class ItemHIB implements HIB<Item> {

	@Override
	public boolean save(Item t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(t);
		transaction.commit();
		session.close();
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> list() {
		Session session = HibernateUtil.getSession();
		try {
			return session.createCriteria(Item.class).list();
		} finally {
			session.close();
		}
	}

	@Override
	public boolean excluir(Item t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(t);
		transaction.commit();
		session.close();
		return false;
	}

	@Override
	public Item findTById(long id) {
		Session session = HibernateUtil.getSession();
		try {
			Item u = (Item) session.get(Item.class, id);
			return u;
		} finally {
			session.close();
		}
	}

}