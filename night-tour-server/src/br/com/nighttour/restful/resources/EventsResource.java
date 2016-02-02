package br.com.nighttour.restful.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.nighttour.model.dto.EventoDTO;
import br.com.nighttour.service.EventService;

@Path("/events")
public class EventsResource {

	EventService eventService = new EventService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON+ "; charset=UTF-8")
	public List<EventoDTO> getListAllEvents() {
		return eventService.getAllEventosDTO("id");
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+ "; charset=UTF-8")
	public Long saveEvent(EventoDTO eventoDTO){
		return eventService.saveEvent(eventoDTO);
	}
}
