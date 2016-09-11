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

	public User findUser(Long id){
		return em.find(User.class, id);
	}
	
	public User saveUser(User newUser) {
		em.persist(newUser);
		return newUser;
		
	}

	public User findUserByUserName(String userName) {

		TypedQuery<User> q = em.createQuery("select u from User u where u.userName = :userName", User.class);
		q.setParameter("userName", userName);
		if (q.getResultList().isEmpty()) {
			return null;
		}
		return q.getSingleResult();
	}

	public User findUserByEmail(String email) {

		TypedQuery<User> q = em.createQuery("select u from User u where u.email = :email", User.class);
		q.setParameter("email", email);
		if (q.getResultList().isEmpty()) {
			return null;
		}
		return q.getSingleResult();
	}
	
	public User updateUser(User user){
		return em.merge(user);
	}

}
