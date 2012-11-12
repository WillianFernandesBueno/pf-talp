package br.ucb.jogo.HIB;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.ucb.jogo.bean.PersonagenHasTreino;
import br.ucb.jogo.bean.Usuario;
import br.ucb.jogo.interfaces.HIB;

public class PersonagemHasTreinoHIB implements HIB<PersonagenHasTreino>{

	@Override
	public boolean save(PersonagenHasTreino t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(t);
		transaction.commit();
		session.close();
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonagenHasTreino> list() {
		Session session = HibernateUtil.getSession();
		try {
			return session.createCriteria(PersonagenHasTreino.class).list();
		} finally {
			session.close();
		}
	}

	@Override
	public boolean excluir(PersonagenHasTreino t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(t);
		transaction.commit();
		session.close();
		return false;
	}

	@Override
	public PersonagenHasTreino findTById(Integer id) {
		Session session = HibernateUtil.getSession();
		try {
			PersonagenHasTreino u = (PersonagenHasTreino) session.get(PersonagenHasTreino.class, id);
			return u;
		} finally {
			session.close();
		}
	}

	public PersonagenHasTreino findByMaxCadastro(int idPerson, int idTreino){
		
		
		Session session = HibernateUtil.getSession();
		Criteria criteria;
		try {
			criteria = session.createCriteria(PersonagenHasTreino.class);
			criteria.add(Restrictions.eq("pk.personagem.idPersonagens",idPerson));
			criteria.add(Restrictions.eq("pk.treino.idTreino",idTreino));
			@SuppressWarnings("unchecked")
			ArrayList<PersonagenHasTreino> a = (ArrayList<PersonagenHasTreino>) criteria.list();
			for (PersonagenHasTreino personagenHasTreino : a) {
				return personagenHasTreino;
			}
			 
		} finally {
			session.close();
		}
		return null;

	}
}
