package br.com.nighttour.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.nighttour.model.dao.EventDao;
import br.com.nighttour.model.dto.EventoDTO;
import br.com.nighttour.model.entity.Event;
import br.com.nighttour.shared.ConstantsCodes;
import br.com.nighttour.util.JPAUtil;

public class EventService {
	EntityManager em;
	EventDao eventDao;
	PictureService pictureService;;
	EventCategoryService eventCategoryService;
	LocalizationService localizationService;
	
	public EventService() {
		this.em = JPAUtil.getEntityManager();
		eventDao = new EventDao(em);
		pictureService = new PictureService(em);
		eventCategoryService = new EventCategoryService(em);
		localizationService = new LocalizationService(em);
	}
	
	public EventService(EntityManager em) {
		this.em = em;
		eventDao = new EventDao(em);
		pictureService = new PictureService(em);
		eventCategoryService = new EventCategoryService(em);
		localizationService = new LocalizationService(em);
	}
	
	public Long saveEvent(EventoDTO eventoDTO) {
		try{
			Event event = getEventFromEventoDTO(eventoDTO);
			em.getTransaction().begin();
			pictureService.persitPicture(event.getCover());
			eventCategoryService.persitEventCategory(event.getEventCategory());
			localizationService.persitLocalization(event.getPlace());
			em.getTransaction().commit();
			return event.getId();
		} catch (Exception ex){
			ex.printStackTrace();
			return ConstantsCodes.FAIL;
		}
	}
	
	public List<EventoDTO> getAllEventosDTO(String campoOrderBy) {
	
		List<EventoDTO> eventoDTOList = new ArrayList<EventoDTO>();
		try{
			List<Event> eventList = eventDao.listAll(campoOrderBy);
			
			for (Event event : eventList) {
				eventoDTOList.add(getEventoDTOFromEvent(event));
				
			}
		} catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
		return eventoDTOList;
	}

	private Event getEventFromEventoDTO(EventoDTO eventoDTO) {
		Event event = new Event();
		event.setDescription(eventoDTO.getDescricao());
		event.setName(eventoDTO.getNome());
		event.setCover(pictureService.getPictureFromFotoDTO(eventoDTO.getCapa()));
		event.setEventCategory(eventCategoryService.getEventCategoryById(eventoDTO.getGenero()));
		event.setPlace(localizationService.getLocalizationFromLocalDTO(eventoDTO.getLocal()));
		return event;
	}
	
	private EventoDTO getEventoDTOFromEvent(Event event) {
		EventoDTO eventoDTO = new EventoDTO();
		eventoDTO.setId(event.getId());
		eventoDTO.setNome(event.getName());
		eventoDTO.setDescricao(event.getDescription());
		eventoDTO.setGenero(event.getEventCategory().getId());
		eventoDTO.setCapa(pictureService.getFotoDTOFromPicture(event.getCover()));
		eventoDTO.setLocal(localizationService.getLocalDTOFromLocalization(event.getPlace()));
		return eventoDTO;
	}
}
