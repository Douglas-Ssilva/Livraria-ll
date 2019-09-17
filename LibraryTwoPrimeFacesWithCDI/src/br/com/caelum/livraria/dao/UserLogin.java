package br.com.caelum.livraria.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.caelum.livraria.modelo.User;

public class UserLogin implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public boolean login(User user) {
		User user2= null;
		Query query = manager.createQuery("select u from User u where u.email= :email and u.senha= :senha");
		query.setParameter("email", user.getEmail());
		query.setParameter("senha", user.getSenha());
		
		try {
			user2 = (User) query.getSingleResult();
		}catch(NoResultException e) {
			return false;
		}
		return user2 != null;
	}

}
