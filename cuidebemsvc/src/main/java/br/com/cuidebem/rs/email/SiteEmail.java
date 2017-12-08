package br.com.cuidebem.rs.email;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import br.com.cuidebem.SendEmail;

@Path("sitemail")
public class SiteEmail {
	

	@EJB
	private SendEmail sendEmail;

	  @POST
	  @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	  public void sendemail(SiteEmailModel model){
		  String content = "Mensagem enviada do site.\n";
		  content = content.concat("Nome: "+model.getName());
		  content = content.concat("\n").concat("Email: ").concat(model.getEmail());
		  content = content.concat("\n").concat("Mensagem: ").concat(model.getMessage());
		  sendEmail.send("alecindrocastilho@gmail.com,cuidebemm@gmail.com",model.getSubject(),content,"text/plain;charset=UTF-8");
	  }
	
}
