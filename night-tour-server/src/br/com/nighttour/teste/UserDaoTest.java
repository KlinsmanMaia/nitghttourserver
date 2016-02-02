package br.com.nighttour.teste;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.nighttour.model.dao.PictureDao;
import br.com.nighttour.model.dao.UserDao;
import br.com.nighttour.model.entity.Picture;
import br.com.nighttour.model.entity.User;
import br.com.nighttour.util.JPAUtilUnitTest;

public class UserDaoTest {
	EntityManager em;
	UserDao userDao;

	
	public static final String DEFAULT_NAME = "Usuário teste ";
	
	@Before
	public void setUp(){
		em = JPAUtilUnitTest.getEntityManager();
		userDao = new UserDao(em);
		em.getTransaction().begin();
		userDao.deletAll();
		em.getTransaction().commit();
	}
	
	@After 
	public void finish(){
		em.close();
	}

	@Test
	public void testSaveUser(){
		User user = getDefaultNotPersitedUser(1);

		em.getTransaction().begin();
		userDao.persist(user);
		Assert.assertNotNull("User id should not be null", user.getId());
		
		em.getTransaction().commit();
	}
	

	
	private User getDefaultNotPersitedUser(int cod){
		Picture picture = UtilTest.getDefaultPicture();
		PictureDao pictureDao = new PictureDao(em);
		
		em.getTransaction().begin();
		pictureDao.persist(picture);
		em.getTransaction().commit();

		User user = new User();
		user.setName(DEFAULT_NAME + cod);
		user.setInterest("interesseTeste " + cod);
		user.setStatus("StatusTeste " + cod);
		user.setFacebookId("Id teste " + cod);
		user.setBirthDay(new Date());
		user.setPicture(picture);
		return user;
	}
	
	@Test
	public void testGetUser(){
		User user  = getDefaultNotPersitedUser(100);
		em.getTransaction().begin();
		userDao.persist(user);
		em.getTransaction().commit();
		
		Assert.assertNotNull(user.getId());
		User user2 = userDao.consultar(user.getId());
		assertNotNull("User should not be null", user2);
		Assert.assertEquals("Usuário teste 100", user2.getName());
	}
	
	@Test
	public void testListAll(){
		List<User> users;
		
		User user1 = getDefaultNotPersitedUser(100);
		User user2 = getDefaultNotPersitedUser(200);
		User user3 = getDefaultNotPersitedUser(300);
		User user4 = getDefaultNotPersitedUser(400);
	
		em.getTransaction().begin();
		userDao.persist(user1);
		userDao.persist(user2);
		userDao.persist(user3);
		userDao.persist(user4);
		em.getTransaction().commit();
		
		users = userDao.listAll("name");
		
		Assert.assertEquals(4, users.size());
	}
	
	
	@Test
	public void testUpdateUser(){
		List<User> users;
		
		User user1 = getDefaultNotPersitedUser(100);
		User user2 = getDefaultNotPersitedUser(200);
		
		em.getTransaction().begin();
		userDao.persist(user1);
		userDao.persist(user2);
		em.getTransaction().commit();
		users = userDao.listAll("name");
		Assert.assertEquals(2, users.size());
		Assert.assertEquals(DEFAULT_NAME + "100", users.get(0).getName());
		
		User user = users.get(0);
		user.setName(DEFAULT_NAME + "101");		
		
		em.getTransaction().begin();
		userDao.merge(user);
		em.getTransaction().commit();
		
		users = userDao.listAll("name");
		Assert.assertEquals(2, users.size());
		Assert.assertEquals(DEFAULT_NAME + "101", users.get(0).getName());
	}

}
