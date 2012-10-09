package br.ucb.jogo.HIB;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static ServiceRegistry serviceRegistry;
	private static final SessionFactory sessionFactory;
	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new
					ServiceRegistryBuilder().applySettings(configuration.getProperties()).
					buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Falha para criar o objeto sessionFactory" + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	public static Session getSession() {
		return sessionFactory.openSession();
	}
}