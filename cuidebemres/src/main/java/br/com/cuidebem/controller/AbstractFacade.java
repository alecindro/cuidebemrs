/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.controller;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.translate.Bundle;

/**
 *
 * @author aleci
 */
@Local
public abstract class AbstractFacade<T> {

	@PersistenceContext(unitName = "cuidebemresPU")
	private EntityManager em;

	private Class<T> entityClass;

	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected EntityManager getEntityManager() {
		return em;
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public void create(T entity) throws ControllerException {
		try{
		getEntityManager().persist(entity);
		}catch(Exception e){
			throw new ControllerException(e.getMessage(), e);
		}
	}

	public T edit(T entity) throws ControllerException {
		try{
		return getEntityManager().merge(entity);
		}catch(Exception e){
			if(e.getCause().getClass().equals(org.hibernate.exception.ConstraintViolationException.class))
			{
				throw new ControllerException(Bundle.getValue("constraint_violation"),e);
			}
			throw new ControllerException(Bundle.getValue("error.save"), e);
		}
	}

	public void remove(T entity) throws ControllerException {
		try{
		getEntityManager().remove(getEntityManager().merge(entity));
		}catch(Exception e){
			throw new ControllerException(e.getMessage(),e);
		}
	}

	public T find(Object id) {
		return getEntityManager().find(entityClass, id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll() {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findRange(int[] range) {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0] + 1);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int count() {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findWithNamedQuery(String namedQueryName, QueryParameter parameters, int resultLimit) throws ControllerException {
		try{
		EntityManager em = getEntityManager();
		Set<Entry<String, Object>> rawParameters = parameters.getParameters().entrySet();
		Query query = em.createNamedQuery(namedQueryName);

		if (resultLimit > 0)
			query.setMaxResults(resultLimit);
		for (Entry entry : rawParameters) {
			query.setParameter((String) entry.getKey(), entry.getValue());
		}
		return query.getResultList();
		}catch(Exception e){
			throw new ControllerException(e.getMessage(), e);
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<T> findWithNamedQuery(String namedQueryName, int resultLimit) throws ControllerException {
		try{
		EntityManager em = getEntityManager();
		Query query = em.createNamedQuery(namedQueryName);
		if (resultLimit > 0)
			query.setMaxResults(resultLimit);
		return query.getResultList();
		}catch(Exception e){
			throw new ControllerException(e.getMessage(), e);
		}

	}

	public void executeNativeQuery(String namedQuery, Object... parameters) {
		javax.persistence.Query query = getEntityManager().createNamedQuery(namedQuery);
		if (parameters != null) {
			int i = 1;
			for (Object parameter : parameters) {
				query.setParameter(i, parameter);
				i = i + 1;
			}
		}

		query.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	public List<T> findByNativeQuery(String namedQuery, Object... parameters)  throws ControllerException {
		try{
		javax.persistence.Query query = getEntityManager().createNamedQuery(namedQuery);
		if (parameters != null) {
			int i = 1;
			for (Object parameter : parameters) {
				query.setParameter(i, parameter);
				i = i + 1;
			}
		}

		return query.getResultList();
		} catch(Exception e){
			throw new ControllerException(e.getMessage(), e);
		}
	}

}
