package br.com.nighttour.restful.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.nighttour.model.dto.UsuarioDTO;
import br.com.nighttour.service.UserService;

@Path("/users")
public class UserResource {

	UserService userService = new UserService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON+ "; charset=UTF-8")
	public List<UsuarioDTO> getAllUsuariosDTO(){
		return userService.getUsuariosDTOList("id");
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+ "; charset=UTF-8")
	public Long saveUser(UsuarioDTO usuarioDTO){
		return userService.saveUser(usuarioDTO);
	}

}
