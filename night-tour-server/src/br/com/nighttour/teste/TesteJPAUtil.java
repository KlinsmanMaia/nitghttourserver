package br.com.nighttour.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.nighttour.model.dao.UserDao;
import br.com.nighttour.model.entity.User;
import br.com.nighttour.util.JPAUtil;

public class TesteJPAUtil {

	public void saveUser(){
		EntityManager em = JPAUtil.getEntityManager();
		UserDao userDao = new UserDao(em);
		
		User user = new User();
		user.setName("HUehuehuehue");
		user.setInterest("HUehuehuehue");
		
		em.getTransaction().begin();
		
		userDao.persist(user);
		
		em.getTransaction().commit();
		em.close();
	}
	
	
	public User getUser(Long id){
		User user;
		EntityManager em = JPAUtil.getEntityManager();
		UserDao userDao = new UserDao(em);

		user = userDao.consultar(id);

		System.out.println(user.getName());
		em.close();
		return user;
	}
	
	public List<User> listAll(){
		List<User> users;
		EntityManager em = JPAUtil.getEntityManager();
		UserDao userDao = new UserDao(em);
		
		em.getTransaction().begin();
		
		users = userDao.listAll("name");
		em.getTransaction().commit();
		em.close();
		return users;
	}
	
	public void testeListAll(){
		List <User> users = new TesteJPAUtil().listAll();
		for (User user : users) {
			System.out.println(user.getName());
		}
	}
	
	public void testeGetUsuario(){
		User user = getUser(1L);
		System.out.println(user.getName());
	}
	
	public static void main(String[] args) {
		System.out.println("Start");
		new TesteJPAUtil().getUser(1L);
		System.out.println("End");
	}
}
