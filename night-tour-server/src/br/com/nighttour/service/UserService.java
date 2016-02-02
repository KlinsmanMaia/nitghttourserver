package br.com.nighttour.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.nighttour.model.dao.UserDao;
import br.com.nighttour.model.dto.UsuarioDTO;
import br.com.nighttour.model.entity.User;
import br.com.nighttour.shared.ConstantsCodes;
import br.com.nighttour.util.DateUtil;
import br.com.nighttour.util.JPAUtil;

public class UserService {

	private EntityManager em;
	private UserDao userDao;
	private PictureService pictureService;
	
	public UserService(){
		em = JPAUtil.getEntityManager();
		userDao = new UserDao(em);
		pictureService = new PictureService(em);
	}
	
	public UserService(EntityManager em){
		this.em = em;
		userDao = new UserDao(em);
		pictureService = new PictureService(em);
	}
	
	public Long saveUser(UsuarioDTO usuarioDTO){
		try{
			User user = getUserFromUsuarioDTO(usuarioDTO);
			if(user.getPicture() != null)
				pictureService.persitPicture(user.getPicture());

			em.getTransaction().begin();
			userDao.persist(user);
			em.getTransaction().commit();
			
			return user.getId();
		}catch (Exception ex){
			ex.printStackTrace();
			return ConstantsCodes.FAIL;
		}
	}
	
	public List<UsuarioDTO> getUsuariosDTOList(String orderBy){
		List<UsuarioDTO> usuarioDTOList = new ArrayList<UsuarioDTO>();
		List<User> userList = getUsersListAll(orderBy);
		
		for (User user : userList) {
			usuarioDTOList.add(getUsusarioDTOFromUser(user));
		}
		
		return usuarioDTOList;
	}
	
	private List<User> getUsersListAll(String orderBy) {
		try{
			return userDao.listAll(orderBy);
		}catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
	}	
	
	private UsuarioDTO getUsusarioDTOFromUser(User user){
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(user.getId());
		usuarioDTO.setNome(user.getName());
		usuarioDTO.setAniversario(DateUtil.getDateAsString(user.getBirthDay(), DateUtil.DatePattern.DDMMAA.getPattern()));
		usuarioDTO.setFacebookId(user.getFacebookId());
		usuarioDTO.setInteresse(user.getInterest());
		usuarioDTO.setStatus(user.getStatus());
		if(user.getPicture() != null)
			usuarioDTO.setImagemPerfil(pictureService.getFotoDTOFromPicture(user.getPicture()));
		return usuarioDTO;
		
	}
	private User getUserFromUsuarioDTO(UsuarioDTO usuarioDTO){
		User user = new User();
		user.setFacebookId(usuarioDTO.getFacebookId());
		user.setName(usuarioDTO.getNome());
		user.setBirthDay(DateUtil.parseDate(usuarioDTO.getAniversario(), DateUtil.DatePattern.DDMMAA.getPattern()));
		user.setInterest(usuarioDTO.getInteresse());
		user.setStatus(usuarioDTO.getStatus());
		user.setPicture(pictureService.getPictureFromFotoDTO(usuarioDTO.getImagemPerfil()));
		return user;
	}
	
	
}
