package br.com.nighttour.service;

import javax.persistence.EntityManager;

import br.com.nighttour.model.dao.PictureDao;
import br.com.nighttour.model.dto.FotoDTO;
import br.com.nighttour.model.entity.Picture;
import br.com.nighttour.shared.ConstantsCodes;

public class PictureService {

	EntityManager em;
	PictureDao pictureDao;	
	public PictureService(EntityManager em) {
		this.em = em;
		pictureDao = new PictureDao(em);
	}
	
	public Picture getPictureFromFotoDTO(FotoDTO fotoDTO){
		if(fotoDTO == null)
			return null;
	
		Picture picture = new Picture();
		picture.setPicture(fotoDTO.getImagem());
		picture.setTimestamp(fotoDTO.getTimestamp());
		return picture;
	}
	
	public FotoDTO getFotoDTOFromPicture(Picture picture){
		FotoDTO fotoDTO = new FotoDTO();
		fotoDTO.setId(picture.getId());
		fotoDTO.setImagem(picture.getPicture());
		fotoDTO.setTimestamp(picture.getTimestamp());
		
		return fotoDTO;
	}
	
	
	public Long persitPicture(Picture picture){
		try{
			em.getTransaction().begin();
			pictureDao.persist(picture);
			em.getTransaction().commit();
			return picture.getId();
		}catch (Exception ex){
			ex.printStackTrace();
			return ConstantsCodes.FAIL;
		}
	}
	
}
