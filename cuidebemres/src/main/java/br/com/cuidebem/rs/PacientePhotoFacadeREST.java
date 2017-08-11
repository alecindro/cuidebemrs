/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.rs;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.cuidebem.controller.PacientePhotoFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.PacientePhoto;

/**
 *
 * @author aleci
 */
@Stateless
@Path("br.com.cuidebem.model.pacientephoto")
public class PacientePhotoFacadeREST {

@EJB
private PacientePhotoFacade pacientePhotoFacade;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(PacientePhoto entity) {
        try {
			pacientePhotoFacade.create(entity);
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, PacientePhoto entity) {
        try {
			pacientePhotoFacade.edit(entity);
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
			pacientePhotoFacade.remove(pacientePhotoFacade.find(id));
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PacientePhoto find(@PathParam("id") Integer id) {
        return pacientePhotoFacade.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PacientePhoto> findAll() {
        return pacientePhotoFacade.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PacientePhoto> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return pacientePhotoFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(pacientePhotoFacade.count());
    }

    
}
