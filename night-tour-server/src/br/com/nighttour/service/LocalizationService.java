package br.com.nighttour.service;

import javax.persistence.EntityManager;

import br.com.nighttour.model.dao.LocalizationDao;
import br.com.nighttour.model.dto.LocalDTO;
import br.com.nighttour.model.entity.Localization;
import br.com.nighttour.shared.ConstantsCodes;

public class LocalizationService {

	EntityManager em;
	LocalizationDao localizationDao;
	
	public LocalizationService(EntityManager em) {
		this.em = em;
		this.localizationDao = new LocalizationDao(em);
	}
	
	public Long persitLocalization(Localization localization){
		try{
			em.getTransaction().begin();
			localizationDao.persist(localization);
			em.getTransaction().commit();
			return localization.getId();
		}catch (Exception ex){
			ex.printStackTrace();
			return ConstantsCodes.FAIL;
		}
	}

	public Localization getLocalizationFromLocalDTO(LocalDTO localDTO) {
		Localization localization = new Localization();
		localization.setLatitude(localDTO.getLatitude());
		localization.setLongitude(localDTO.getLongitude());
		localization.setZoom(localDTO.getZoom());
		return localization;
	}
	
	public LocalDTO getLocalDTOFromLocalization(Localization localization) {
		LocalDTO localDTO = new LocalDTO();
		localDTO.setId(localization.getId());
		localDTO.setLatitude(localization.getLatitude());
		localDTO.setLongitude(localization.getLongitude());
		localDTO.setZoom(localization.getZoom());
		return localDTO;
	}
}
