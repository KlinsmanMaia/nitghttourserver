package br.com.nighttour.model.dao;

import javax.persistence.EntityManager;

import br.com.nighttour.model.entity.Event;

public class EventDao extends AbstractDao<Event>{

	public EventDao(EntityManager em) {
		super(em, Event.class);
	}

}
