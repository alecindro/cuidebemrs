package br.com.cuidebem.view.evento;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean
@RequestScoped
@FacesValidator("passValidator")
public class PassValidation implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		EventoView eventoView = (EventoView) context.getApplication().evaluateExpressionGet(context, "#{eventomb}", EventoView.class);
		eventoView.getSubGrupoEvento().add((String)value);
	}

	
}
