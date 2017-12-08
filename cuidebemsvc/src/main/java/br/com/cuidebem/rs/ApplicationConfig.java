/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.rs;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author aleci
 */
@javax.ws.rs.ApplicationPath("rs")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(br.com.cuidebem.rs.CrossOriginFilter.class);
        resources.add(br.com.cuidebem.rs.EventoFacadeREST.class);
        resources.add(br.com.cuidebem.rs.PacienteFacadeREST.class);
        resources.add(br.com.cuidebem.rs.PacientePhotoFacadeREST.class);
        resources.add(br.com.cuidebem.rs.PatologiaPacienteFacadeREST.class);
        resources.add(br.com.cuidebem.rs.ResidenciaFacadeREST.class);
        resources.add(br.com.cuidebem.rs.ResidenciaTelefoneFacadeREST.class);
        resources.add(br.com.cuidebem.rs.ResponsavelFacadeREST.class);
        resources.add(br.com.cuidebem.rs.ResponsavelPacienteFacadeREST.class);
        resources.add(br.com.cuidebem.rs.ResponsavelPhotoFacadeREST.class);
        resources.add(br.com.cuidebem.rs.ResponsavelTelefoneFacadeREST.class);
        resources.add(br.com.cuidebem.rs.TelefoneFacadeREST.class);
        resources.add(br.com.cuidebem.rs.UsuarioFacadeREST.class);
        resources.add(br.com.cuidebem.rs.UsuarioPhotoFacadeREST.class);
        resources.add(br.com.cuidebem.rs.UsuarioResidenciaFacadeREST.class);
        resources.add(br.com.cuidebem.rs.UsuarioTelefoneFacadeREST.class);
        resources.add(br.com.cuidebem.rs.PhotoREST.class);
        resources.add(br.com.cuidebem.rs.EmailcontentREST.class);
        resources.add(br.com.cuidebem.rs.email.SiteEmail.class);
    }
    
}
