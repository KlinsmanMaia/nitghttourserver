package br.com.nighttour.teste;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.nighttour.model.dao.PictureDao;
import br.com.nighttour.model.dao.UserDao;
import br.com.nighttour.model.dto.UsuarioDTO;
import br.com.nighttour.model.entity.Picture;
import br.com.nighttour.model.entity.User;
import br.com.nighttour.service.UserService;
import br.com.nighttour.shared.ConstantsCodes;
import br.com.nighttour.util.DateUtil;
import br.com.nighttour.util.JPAUtilUnitTest;

public class UserServiceTest {
	EntityManager em;
	UserDao userDao;
	PictureDao pictureDao;
	UserService service;
	
	public static final String DEFAULT_NAME = "Usuário teste ";
	public static final String DEFAULT_FACEBOOK_ID = "1000";
	public static final String DEFAULT_STATUS = "Status teste ";
	public static final String DEFAULT_INTEREST = "Interesse teste ";
	public static final Date DEFAULT_DATE = new Date();
 	public static final Picture DEFAULT_PICTURE = UtilTest.getDefaultPicture();

 	@Before
	public void setUp(){
		em = JPAUtilUnitTest.getEntityManager();
		userDao = new UserDao(em);
		pictureDao = new PictureDao(em);
		service = new UserService(em); 
		
		em.getTransaction().begin();
		userDao.deletAll();
		pictureDao.deletAll();
		em.getTransaction().commit();
	}
	
	@After 
	public void finish(){
		em.close();
	}
	
	private UsuarioDTO getUsuarioDTO(String cod){
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNome(DEFAULT_NAME + cod);
		usuarioDTO.setFacebookId(DEFAULT_FACEBOOK_ID);
		usuarioDTO.setAniversario(DateUtil.getDateAsString(DEFAULT_DATE, DateUtil.DatePattern.DDMMAA.getPattern()));
		usuarioDTO.setInteresse(DEFAULT_INTEREST);
		usuarioDTO.setStatus(DEFAULT_STATUS);
		usuarioDTO.setImagemPerfil(UtilTest.getFotoDTOFromPicture(DEFAULT_PICTURE));
		return usuarioDTO;
	}

	@Test
	public void testeSaveUser(){
		String codName = "1";
		UsuarioDTO usuarioDTO = getUsuarioDTO(codName);
		Long returnedCode = service.saveUser(usuarioDTO);
		Assert.assertNotSame(ConstantsCodes.FAIL, returnedCode);
		User user = userDao.consultar(returnedCode);
		assertNotNull("User should not be null", user);
		Assert.assertEquals(DEFAULT_NAME + codName, user.getName());
	}
	
	@Test
	public void testeGetAllUsers(){
		String codName1 = "1";
		String codName2 = "2";
		String codName3 = "3";
		
		UsuarioDTO usuarioDTO = getUsuarioDTO(codName1);
		Long returnedCode = service.saveUser(usuarioDTO);
		Assert.assertNotSame(ConstantsCodes.FAIL, returnedCode);

		usuarioDTO = getUsuarioDTO(codName2);
		returnedCode = service.saveUser(usuarioDTO);
		Assert.assertNotSame(ConstantsCodes.FAIL, returnedCode);
		
		usuarioDTO = getUsuarioDTO(codName3);
		returnedCode = service.saveUser(usuarioDTO);
		Assert.assertNotSame(ConstantsCodes.FAIL, returnedCode);
		
		List<UsuarioDTO> usuarioDTOList = service.getUsuariosDTOList("id");
		
		Assert.assertEquals(3, usuarioDTOList.size());
		Assert.assertEquals(DEFAULT_NAME + codName1, usuarioDTOList.get(0).getNome());
	}
}
