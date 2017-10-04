package br.com.cuidebem.rs.email;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.cuidebem.controller.UsersFacade;
import br.com.cuidebem.controller.exception.ControllerException;

@Path("/desbloqueio/{email}/{app}")
public class Desbloqueio {

	@EJB
	private UsersFacade usersFacade;

	@GET
	@Produces("text/html")
	public String aceite(@PathParam("email") String email,@PathParam("app") String app) {
		try {
			usersFacade.confirmaAceite(email);
			return PagesHTML.desbloqueio(app);
		} catch (ControllerException e) {
			return PagesHTML.return_oops_desbloqueio.replace(PagesHTML.message, e.getMessage());
		} 
	}

}
