package br.com.cuidebem.rs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cuidebem.controller.PacientePhotoFacade;
import br.com.cuidebem.controller.ResponsavelPhotoFacade;
import br.com.cuidebem.controller.UsuarioPhotoFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.PacientePhoto;
import br.com.cuidebem.model.ResponsavelPhoto;
import br.com.cuidebem.model.UsuarioPhoto;
import br.com.cuidebem.model.view.PhotoDescricao;
import br.com.cuidebem.translate.Bundle;

@Path("/images")
public class PhotoREST {

	@EJB
	private UsuarioPhotoFacade usuarioPhotoFacade;
	@EJB
	private ResponsavelPhotoFacade responsavelPhotoFacade;
	@EJB
	private PacientePhotoFacade pacientePhotoFacade;

	private final static String NOPHOTO_IMAGE = "no_photo.png";

	@POST
	@Path("/pacientedia")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void create(PhotoDescricao photo) {
		try {
			pacientePhotoFacade.photoDay(photo);

		} catch (ControllerException e) {
			throw new InternalServerErrorException(e.getMessage());
		}

	}
	
	@GET
	@Path("/pacientedia/{idpacientephoto}")
	@Produces("text/plain")
	public Response getPacientePhoto(@PathParam(value = "idpacientephoto") Integer idpacientephoto){
		PacientePhoto pacientePhoto = pacientePhotoFacade.find(idpacientephoto);
		if(pacientePhoto == null){
			throw new InternalServerErrorException(Bundle.getValue("error.photonotfound"));
		}
		return buildPhoto(pacientePhoto.getPhoto());
	}

	@GET
	@Path("/usuario")
	@Produces("text/plain")
	public Response getNoImage() {
		try {
			return buildPhoto(noPhoto());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.noContent().build();
	}

	@GET
	@Path("/responsavel")
	@Produces("text/plain")
	public Response getNoImageResponsavel() {
		return getNoImage();
	}

	@GET
	@Path("/paciente")
	@Produces("text/plain")
	public Response getNoImagePaciente() {
		return getNoImage();
	}

	@GET
	@Path("/usuario/{usuarioId}")
	@Produces("text/plain")
	public Response getImageUsuario(@PathParam(value = "usuarioId") Integer usuarioId) {
		if (usuarioId != null) {
			UsuarioPhoto usuarioPhoto = usuarioPhotoFacade.find(usuarioId);
			if (usuarioPhoto != null && usuarioPhoto.getPhoto() != null) {
				return buildPhoto(usuarioPhoto.getPhoto());
			}
		}
		try {
			return buildPhoto(noPhoto());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.noContent().build();
	}

	@GET
	@Path("/responsavel/{idresponsavel}")
	@Produces("text/plain")
	public Response getImageResponsavel(@PathParam(value = "idresponsavel") Integer idresponsavel) {

		if (idresponsavel != null) {
			ResponsavelPhoto respPhoto = responsavelPhotoFacade.find(idresponsavel);
			if (respPhoto != null && respPhoto.getPhoto() != null) {
				return buildPhoto(respPhoto.getPhoto());
			}
		}
		try {
			return buildPhoto(noPhoto());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.noContent().build();
	}

	@GET
	@Path("/paciente/{pacienteId}")
	@Produces("text/plain")
	public Response getImagePaciente(@PathParam(value = "pacienteId") Integer pacienteId) {
		if (pacienteId != null) {
			PacientePhoto pacPhoto = pacientePhotoFacade.findByPaciente(pacienteId);
			if (pacPhoto != null && pacPhoto.getPhoto() != null) {
				return buildPhoto(pacPhoto.getPhoto());
			}
		}
		try {
			return buildPhoto(noPhoto());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.noContent().build();
	}

	private final InputStream noPhoto() throws IOException {
		URL url = getClass().getResource("/" + NOPHOTO_IMAGE);
		URLConnection uc = url.openConnection();
		return uc.getInputStream();
	}

	private Response buildPhoto(InputStream bigInputStream) {
		return Response.ok(bigInputStream).cacheControl(cacheControl()).build();
	}

	private Response buildPhoto(byte[] photo) {
		final InputStream bigInputStream = new ByteArrayInputStream(photo);
		return Response.ok(bigInputStream).cacheControl(cacheControl()).build();
	}

	private CacheControl cacheControl() {
		CacheControl cacheControl = new CacheControl();
		cacheControl.setNoCache(true);
		cacheControl.setPrivate(false);
		cacheControl.setNoStore(true);
		return cacheControl;
	}

}
