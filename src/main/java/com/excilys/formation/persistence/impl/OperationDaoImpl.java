package com.excilys.formation.persistence.impl;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.excilys.formation.model.Operation;
import com.excilys.formation.persistence.OperationDao;
import com.excilys.formation.util.HibernateUtil;

@Repository
public class OperationDaoImpl implements OperationDao {

	//@Inject
//	private Provider<EntityManager> em;
	
//	EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
//    EntityManager em = emf.createEntityManager();

	@Override
	public Operation save(Operation o) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
		session.save(o);
	    
		session.getTransaction().commit();
		
		return o;
	}
	
}
