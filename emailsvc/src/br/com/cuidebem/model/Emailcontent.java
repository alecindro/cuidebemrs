package br.com.cuidebem.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(catalog = "cuidebemres", schema = "", name="emailcontent")
@XmlRootElement
public class Emailcontent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer idemailenviado;
	private byte[] content;
	public Integer getIdemailenviado() {
		return idemailenviado;
	}
	public void setIdemailenviado(Integer idemailenviado) {
		this.idemailenviado = idemailenviado;
	}
	
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
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
		Emailcontent other = (Emailcontent) obj;
		if (idemailenviado == null) {
			if (other.idemailenviado != null)
				return false;
		} else if (!idemailenviado.equals(other.idemailenviado))
			return false;
		return true;
	}

	
	
	

}
