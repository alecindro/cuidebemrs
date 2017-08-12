package br.com.cuidebem.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.cuidebem.view.agenda.AgendaDefView;

@ManagedBean
@RequestScoped
@FacesValidator("passValidator")
public class PassValidation implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		AgendaDefView agendaDefView = (AgendaDefView) context.getCurrentInstance().getApplication().evaluateExpressionGet(context, "#{agendadefmb}", AgendaDefView.class);
		agendaDefView.getSubGrupoEvento().add((String)value);
	}

	
}
