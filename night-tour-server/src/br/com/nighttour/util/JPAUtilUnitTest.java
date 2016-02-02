package br.com.nighttour.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtilUnitTest {
	private static EntityManagerFactory emf = new Persistence().createEntityManagerFactory("nighttour-test");
	
	public static EntityManager getEntityManager(){
		return emf.createEntityManager();
	}
}
