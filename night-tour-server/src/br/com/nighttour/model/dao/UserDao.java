package br.com.nighttour.model.dao;

import javax.persistence.EntityManager;

import br.com.nighttour.model.entity.User;

public class UserDao extends AbstractDao<User>{

	public UserDao(EntityManager em) {
		super(em, User.class);
	}

	
}
