package br.com.cuidebem.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(catalog = "cuidebemres", schema = "", name="emailimagem")
@XmlRootElement
public class Emailimagem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idemailimagem;
	
	private Integer idemailenviado;
	private String cid;
	private byte[] photo;
	public Integer getIdemailimagem() {
		return idemailimagem;
	}
	public void setIdemailimagem(Integer idemailimagem) {
		this.idemailimagem = idemailimagem;
	}
	public Integer getIdemailenviado() {
		return idemailenviado;
	}
	public void setIdemailenviado(Integer idemailenviado) {
		this.idemailenviado = idemailenviado;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idemailimagem == null) ? 0 : idemailimagem.hashCode());
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
		Emailimagem other = (Emailimagem) obj;
		if (idemailimagem == null) {
			if (other.idemailimagem != null)
				return false;
		} else if (!idemailimagem.equals(other.idemailimagem))
			return false;
		return true;
	}
	
	
}
