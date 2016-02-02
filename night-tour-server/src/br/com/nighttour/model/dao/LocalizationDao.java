package br.com.nighttour.model.dao;

import javax.persistence.EntityManager;

import br.com.nighttour.model.entity.Localization;

public class LocalizationDao extends AbstractDao<Localization>{

	public LocalizationDao(EntityManager em) {
		super(em, Localization.class);
	}

}
