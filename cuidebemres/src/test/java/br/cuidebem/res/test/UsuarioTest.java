package br.cuidebem.res.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.cuidebem.controller.UsuarioFacade;
import br.com.cuidebem.model.Usuario;

public class UsuarioTest {

	private static EntityManagerFactory mEmf;
	private static EntityManager mEntityManager;
	private static final String mPersistenceUnit = "resPU";
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 mEmf = Persistence.createEntityManagerFactory(mPersistenceUnit);
		    mEntityManager = mEmf.createEntityManager();
	}

	@Test
	public void test() {
		Usuario usuario = createUsuario();
		try{
		mEntityManager.getTransaction().begin();
		UsuarioFacade uf = new UsuarioFacade();
		uf.setEntityManager(mEntityManager);
		usuario = uf.edit(usuario);
		
		}catch(Exception e){
			fail(e.getMessage());
		}
		assertTrue("Usuario salvo com sucesso: " + usuario.getIdusuario(), usuario.getIdusuario() != null);
			
		
		
	}
	
	@AfterClass
	public static void closeTestFixture() {
	    mEntityManager.close();
	    mEmf.close();
	}
	
	private Usuario createUsuario(){
		Usuario usuario = new Usuario();
		usuario.setDatanascimento(Calendar.getInstance().getTime());
		usuario.setEmail("alecindrocastilho@gmail.com");
		//usuario.setEnabled(true);
		usuario.setGenero(Boolean.TRUE);
		usuario.setNome("alecindro");
		usuario.setApelido("lele");
		return usuario;
	}

}
