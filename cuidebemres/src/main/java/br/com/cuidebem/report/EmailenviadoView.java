package br.com.cuidebem.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.cuidebem.controller.EmailException;
import br.com.cuidebem.controller.EmailJpaController;
import br.com.cuidebem.model.Emailenviado;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("emailenviadoMB")
@RequestScoped
public class EmailenviadoView extends IndexView {

	@EJB
	private EmailJpaController jpaController;
	private int pageNumber = 1;
	private int resultLimit = 10;
	private LazyDataModel<Emailenviado> emails;
	private int count = -1;

	@PostConstruct
	private void init() {
		emails = new LazyDataModel<Emailenviado>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public List<Emailenviado> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				try {
					return jpaController.list(getIdresidencia(), pageSize, first);
				} catch (EmailException e) {
					JsfUtil.addErrorMessage(e.getMessage());
					return new ArrayList<Emailenviado>();
				}
			}

			@Override
			public int getRowCount() {
				if (count == -1)
					count = jpaController.count(getIdresidencia());
				return count;
			}

		};

	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getResultLimit() {
		return resultLimit;
	}

	public void setResultLimit(int resultLimit) {
		this.resultLimit = resultLimit;
	}

	public LazyDataModel<Emailenviado> getEmails() {
		return emails;
	}

	public void setEmails(LazyDataModel<Emailenviado> emails) {
		this.emails = emails;
	}

}
