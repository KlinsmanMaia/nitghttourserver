package br.com.nighttour.teste;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;

import br.com.nighttour.model.dao.EventDao;
import br.com.nighttour.model.dao.PictureDao;
import br.com.nighttour.model.dao.UserDao;
import br.com.nighttour.service.EventCategoryService;
import br.com.nighttour.service.EventService;
import br.com.nighttour.service.LocalizationService;
import br.com.nighttour.service.UserService;
import br.com.nighttour.util.JPAUtilUnitTest;

public class EventServiceTest {
	EntityManager em;
	EventDao eventDao;
	PictureDao pictureDao;
	LocalizationService localizationService;
	EventCategoryService eventCategoryService;
	EventService eventService;
	
	@Before
	public void setUp(){
		em = JPAUtilUnitTest.getEntityManager();
		eventDao = new EventDao(em);
		pictureDao = new PictureDao(em);
		eventService = new EventService(em); 
		
		em.getTransaction().begin();
		eventDao.deletAll();
		pictureDao.deletAll();
		em.getTransaction().commit();
	}
	
	@After 
	public void finish(){
		em.close();
	}
}
