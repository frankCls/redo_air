package com.redoair.repositories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.redoair.domain.User;

@Stateless
public class UserRepository {
	@PersistenceContext(unitName = "MyPersistenceUnit")
	protected EntityManager em;

	public User saveUser(User newUser) {
		User user = findUserByUserName(newUser.getUserName());
	
		if (user != null) {
			System.out.println("old user: "+user.getFirstName());
			 em.merge(user);
		} else {
			System.out.println("new user: "+newUser.getFirstName());
			 em.persist(newUser);
		}
		return em.find(User.class, newUser.getId());
	}

	public User findUserByUserName(String userName) {
		
		TypedQuery<User> q = em.createQuery("select u from User u where u.userName = :userName", User.class);
		q.setParameter("userName", userName);
		if (q.getResultList().isEmpty()) {
			return null;
		}
		return q.getSingleResult();
	}

}
