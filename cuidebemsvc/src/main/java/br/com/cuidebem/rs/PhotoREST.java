package br.com.cuidebem.rs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;

import br.com.cuidebem.controller.PacientePhotoFacade;
import br.com.cuidebem.controller.ResponsavelPhotoFacade;
import br.com.cuidebem.controller.UsuarioPhotoFacade;
import br.com.cuidebem.model.PacientePhoto;
import br.com.cuidebem.model.ResponsavelPhoto;
import br.com.cuidebem.model.UsuarioPhoto;

@Path("/images")
public class PhotoREST {

	@EJB
	private UsuarioPhotoFacade usuarioPhotoFacade;
	@EJB
	private ResponsavelPhotoFacade responsavelPhotoFacade;
	@EJB
	private PacientePhotoFacade pacientePhotoFacade;

	private final static String NOPHOTO_IMAGE = "no_photo.png";
	
	@GET
	@Path("/usuario")
	@Produces("image/*")
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
	@Produces("image/*")
	public Response getNoImageResponsavel() {
		return getNoImage();
	}
	
	
	@GET
	@Path("/paciente")
	@Produces("image/*")
	public Response getNoImagePaciente() {
		return getNoImage();
	}

	@GET
	@Path("/usuario/{usuarioId}")
	@Produces("image/*")
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
	@Produces("image/*")
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
	@Produces("image/*")
	public Response getImagePaciente(@PathParam(value = "pacienteId") Integer pacienteId) {
		if (pacienteId != null) {
			PacientePhoto pacPhoto = pacientePhotoFacade.find(pacienteId);
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
