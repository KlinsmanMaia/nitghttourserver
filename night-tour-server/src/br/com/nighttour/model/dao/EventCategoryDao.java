package br.com.nighttour.model.dao;

import javax.persistence.EntityManager;

import br.com.nighttour.model.entity.EventCategory;

public class EventCategoryDao extends AbstractDao<EventCategory>{

	public EventCategoryDao(EntityManager em) {
		super(em, EventCategory.class);
	}
}
