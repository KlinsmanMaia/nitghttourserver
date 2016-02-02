package br.com.nighttour.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.nighttour.model.entity.AbstractEntity;
import br.com.nighttour.util.JPAUtil;

public abstract class AbstractDao<T extends AbstractEntity> {

	private EntityManager em = JPAUtil.getEntityManager();
	private Class<T> classe;
	
	public AbstractDao(EntityManager em, Class<T> classe) {
		this.classe = classe;
		this.em = em;
	}

	public void persist(T entity){
		this.em.persist(entity);
	}
	
	public void merge(T entity){
		this.em.merge(entity);
	}
	
	public void remove(T entity){
		this.em.remove(entity);
	}
	
	public T consultar(Long id){
		return em.find(classe, id);
	}
	
	public List<T> listAll(String campoOrderBy){
		String jpql = "Select p from " + classe.getName() + " p order by p." + campoOrderBy;
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}
	
	public int deletAll(){
		String jpql = "delete from " + classe.getName() + " p ";
		Query query = em.createQuery(jpql);
		return query.executeUpdate();
	}
}
