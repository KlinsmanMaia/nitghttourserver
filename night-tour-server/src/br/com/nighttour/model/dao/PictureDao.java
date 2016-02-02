package br.com.nighttour.model.dao;

import javax.persistence.EntityManager;

import br.com.nighttour.model.entity.Picture;

public class PictureDao extends AbstractDao<Picture>{

	public PictureDao(EntityManager em) {
		super(em, Picture.class);
	}

}
