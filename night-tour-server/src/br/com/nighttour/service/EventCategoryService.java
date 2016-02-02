package br.com.nighttour.service;

import javax.persistence.EntityManager;

import br.com.nighttour.model.dao.EventCategoryDao;
import br.com.nighttour.model.entity.EventCategory;
import br.com.nighttour.shared.ConstantsCodes;

public class EventCategoryService {

	EntityManager em;
	EventCategoryDao eventCategoryDao;
	
	public EventCategoryService(EntityManager em) {
		this.em = em; 
		eventCategoryDao = new EventCategoryDao(em);
	}
	
	public EventCategory getEventCategoryById(Long id){
		return eventCategoryDao.consultar(id);
	}
	
	public Long persitEventCategory(EventCategory eventCategory){
		try{
			em.getTransaction().begin();
			eventCategoryDao.persist(eventCategory);
			em.getTransaction().commit();
			return eventCategory.getId();
		}catch (Exception ex){
			ex.printStackTrace();
			return ConstantsCodes.FAIL;
		}
	}

}
