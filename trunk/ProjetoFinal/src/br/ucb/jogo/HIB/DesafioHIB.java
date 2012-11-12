package br.ucb.jogo.HIB;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.ucb.jogo.bean.Desafio;
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
	
	public Desafio findByDesafio(int idDesafiado, int idDesafiante){
		
		Session session = HibernateUtil.getSession();
		Criteria criteria;
		try {
			criteria = session.createCriteria(Desafio.class);

			criteria.add(Restrictions.eq("idDesafiado",idDesafiado));
			criteria.add(Restrictions.eq("idDesafiante",idDesafiante));
			
			Integer id = (Integer)criteria.setProjection(Projections.projectionList().add(Projections.max("idDesafios"))).list().get(0);
			
			if(id != null){
				Desafio des = findTById(id);
				if(des != null)
					return des;
			}
					
			
			
		} finally {
			session.close();
		}
		return null;
	}
	
	public Desafio findByDesafio(int idDesafiado){
		
		Session session = HibernateUtil.getSession();
		Criteria criteria;
		try {
			criteria = session.createCriteria(Desafio.class);

			criteria.add(Restrictions.eq("idDesafiado",idDesafiado));
			criteria.add(Restrictions.eq("dueloAtivo",true));
			@SuppressWarnings("unchecked")
			ArrayList<Desafio> a = (ArrayList<Desafio>) criteria.list();
			for (Desafio desafio : a) {
				return findTById(desafio.getIdDesafios());
			}
			 
		} finally {
			session.close();
		}
		return null;

	}
	
}
