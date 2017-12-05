package br.com.cuidebem.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@NamedQueries({
	@NamedQuery(name="emailenviado.all", query="select e from Emailenviado e where e.idresidencia = :idresidencia order by e.dataenvio desc"),
	@NamedQuery(name="emailenviado.count", query="select count(e) from Emailenviado e where e.idresidencia = :idresidencia")
})

@NamedNativeQueries({
	@NamedNativeQuery(name="emailenviado.bydate", query="select e.* from emailenviado where date(datarelatorio) >= date(?1) and idresidencia = ?2",resultClass=Emailenviado.class )
	
})
@Table(catalog = "cuidebemres", schema = "", name="emailenviado")
@XmlRootElement
public class Emailenviado implements Serializable {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    private Integer idemailenviado;
	private Integer idresidencia;  
	private String to_email;
	  private String subject;
	  private String content_type;
	  private String message;
	  private Date dataenvio;
	  private Date datarelatorio;
	  private boolean error;
	  private boolean automatic;
	  
	public Integer getIdemailenviado() {
		return idemailenviado;
	}
	public void setIdemailenviado(Integer idemailenviado) {
		this.idemailenviado = idemailenviado;
	}
	public String getTo_email() {
		return to_email;
	}
	public void setTo_email(String to_email) {
		this.to_email = to_email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public Date getDataenvio() {
		return dataenvio;
	}
	public void setDataenvio(Date dataenvio) {
		this.dataenvio = dataenvio;
	}
	
	
	public Integer getIdresidencia() {
		return idresidencia;
	}
	public void setIdresidencia(Integer idresidencia) {
		this.idresidencia = idresidencia;
	}
	
	public boolean isAutomatic() {
		return automatic;
	}
	public void setAutomatic(boolean automatic) {
		this.automatic = automatic;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idemailenviado == null) ? 0 : idemailenviado.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emailenviado other = (Emailenviado) obj;
		if (idemailenviado == null) {
			if (other.idemailenviado != null)
				return false;
		} else if (!idemailenviado.equals(other.idemailenviado))
			return false;
		return true;
	}
	
	
	public Date getDatarelatorio() {
		return datarelatorio;
	}
	public void setDatarelatorio(Date datarelatorio) {
		this.datarelatorio = datarelatorio;
	}
	@Override
	public String toString() {
		return "Emailenviado [idemailenviado=" + idemailenviado + ", to_email=" + to_email + ", subject=" + subject
				+ ", content_type=" + content_type + ", error=" + error + ", dataenvio=" + dataenvio + "]" + ", automatic="+automatic;
	}
	  
	  
	
}
