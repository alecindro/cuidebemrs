package br.com.cuidebem.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.cuidebem.model.Emailcontent;
import br.com.cuidebem.model.Emailenviado;

@Stateless
public class EmailJpaController {

	@PersistenceContext(unitName = "cuidebemmailPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public void create(Emailenviado emailEnviado, String content) throws EmailException {
		try {
			emailEnviado = getEntityManager().merge(emailEnviado);
			saveContent(emailEnviado.getIdemailenviado(), content);
		} catch (Exception e) {
			throw new EmailException(e.getMessage(), e);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Emailenviado> list(Integer idresidencia, int resultLimit, int row) throws EmailException {
		try {
			EntityManager em = getEntityManager();
			Query query = em.createNamedQuery("emailenviado.all");
			query.setParameter("idresidencia", idresidencia);
			if (resultLimit > 0)
				query.setFirstResult(row);
			query.setMaxResults(resultLimit);

			return query.getResultList();
		} catch (Exception e) {
			throw new EmailException(e.getMessage(), e);
		}
	}

	public List<Emailenviado> find(Date data, Integer idresidencia, int resultLimit, int pageNumber) throws EmailException {
		try {
			EntityManager em = getEntityManager();
			Query query = em.createNamedQuery("emailenviado.bydate");
			if (resultLimit > 0) {
				query.setFirstResult((pageNumber - 1) * resultLimit);
				query.setMaxResults(resultLimit);
			}
			query.setParameter(1, data);
			query.setParameter(2, idresidencia);
			return query.getResultList();
		} catch (Exception e) {
			throw new EmailException(e.getMessage(), e);
		}
	}

	private void saveContent(Integer id, String content) throws EmailException {
		try {
			Emailcontent ec = new Emailcontent();
			ec.setIdemailenviado(id);
			ec.setContent(content.getBytes("UTF-8"));
			getEntityManager().persist(ec);
		}catch(UnsupportedEncodingException e){
			throw new EmailException(e.getMessage(), e);
		} catch (Exception e) {
			throw new EmailException(e.getMessage(), e);
		}
	}
	
	public Emailcontent find(Integer id){
		return getEntityManager().find(Emailcontent.class, id);
	}
	
	public int count(Integer idresidencia){
		EntityManager em = getEntityManager();
		Query query = em.createNamedQuery("emailenviado.count");
		query.setParameter("idresidencia", idresidencia);
		return ((Long) query.getSingleResult()).intValue();
	}
}
