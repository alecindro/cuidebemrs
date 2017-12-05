package br.com.cuidebem.rs;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cuidebem.controller.EmailJpaController;
import br.com.cuidebem.model.Emailcontent;

@Path("/emailcontent")
public class EmailcontentREST {
	
	@EJB
	private EmailJpaController jpaController;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_HTML)
	public Response getContent(@PathParam(value = "id") Integer id) {
		if (id!= null) {
			Emailcontent emailcontent = jpaController.find(id);
			if(emailcontent!=null){
			byte[] content = emailcontent.getContent();
			if (content != null ) {
				return buildConten(content);
			}
			}
		}
	

		return Response.noContent().build();
	}


	
	private Response buildConten(byte[] content) {
		final InputStream bigInputStream = new ByteArrayInputStream(content);
		return Response.ok(bigInputStream).cacheControl(cacheControl()).build();
	}
	
	private CacheControl cacheControl() {
		CacheControl cacheControl = new CacheControl();
		cacheControl.setNoCache(false);
		cacheControl.setPrivate(false);
		cacheControl.setNoStore(false);
		return cacheControl;
	}
}
