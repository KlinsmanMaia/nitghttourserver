package br.com.nighttour.teste;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.nighttour.model.dao.EventCategoryDao;
import br.com.nighttour.model.dao.EventDao;
import br.com.nighttour.model.entity.Event;
import br.com.nighttour.model.entity.EventCategory;
import br.com.nighttour.util.JPAUtilUnitTest;

public class EventDaoTest {
	EntityManager em;
	EventDao eventDao;

	
	public static final String DEFAULT_NAME = "Evento teste ";
	
	@Before
	public void setUp(){
		em = JPAUtilUnitTest.getEntityManager();
		eventDao = new EventDao(em);
		em.getTransaction().begin();
		eventDao.deletAll();
		em.getTransaction().commit();
	}
	
	@After 
	public void finish(){
		em.close();
	}

	@Test
	public void testSaveUser(){
		em.getTransaction().begin();
		
		Event event = getDefaultNotPersistedEvent(1);
		
		EventCategory category = new EventCategory();
		category.setDescription("Descricao categoria");
		event.setEventCategory(category);
		EventCategoryDao categoryDao = new EventCategoryDao(em);
		categoryDao.persist(category);
		eventDao.persist(event);
		Assert.assertNotNull("User id should not be null", event.getId());
		
		em.getTransaction().commit();
	}
	
	private Event getDefaultNotPersistedEvent(int cod){
		Event event = new Event();
		event.setName(DEFAULT_NAME + cod);
		event.setDescription("Description teste " + cod);
		return event;
	}
	
	@Test
	public void testGetUser(){
		Event event  = getDefaultNotPersistedEvent(100);
		em.getTransaction().begin();
		eventDao.persist(event);
		em.getTransaction().commit();
		
		Assert.assertNotNull(event.getId());
		Event event2 = eventDao.consultar(event.getId());
		assertNotNull("User should not be null", event2);
		Assert.assertEquals("Evento teste 100", event2.getName());
	}
	
	@Test
	public void testListAll(){
		List<Event> events;
		
		em.getTransaction().begin();
		eventDao.persist(getDefaultNotPersistedEvent(100));
		eventDao.persist(getDefaultNotPersistedEvent(200));
		eventDao.persist(getDefaultNotPersistedEvent(300));
		eventDao.persist(getDefaultNotPersistedEvent(400));
		em.getTransaction().commit();
		
		events = eventDao.listAll("name");
		
		Assert.assertEquals(4, events.size());
	}
	
	
	@Test
	public void testUpdateUser(){
		List<Event> events;
		
		em.getTransaction().begin();
		eventDao.persist(getDefaultNotPersistedEvent(100));
		eventDao.persist(getDefaultNotPersistedEvent(200));
		em.getTransaction().commit();
		events = eventDao.listAll("name");
		Assert.assertEquals(2, events.size());
		Assert.assertEquals(DEFAULT_NAME + "100", events.get(0).getName());
		
		Event event = events.get(0);
		event.setName(DEFAULT_NAME + "101");		
		
		em.getTransaction().begin();
		eventDao.merge(event);
		em.getTransaction().commit();
		
		events = eventDao.listAll("name");
		Assert.assertEquals(2, events.size());
		Assert.assertEquals(DEFAULT_NAME + "101", events.get(0).getName());
	}

}
