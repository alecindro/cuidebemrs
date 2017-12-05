package br.com.cuidebem.model.email;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.cuidebem.model.Residencia;
import br.com.cuidebem.model.Responsavel;

@Entity
@Table(catalog = "cuidebemres", schema = "", name = "schedulemail")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "schedulemail.findByResponsavel", query = "select s from Schedulemail s where s.responsavel.idresponsavel = :idresponsavel and s.idpaciente = :idpaciente"),
		@NamedQuery(name = "schedulemail.findSchedule", query = "select s from Schedulemail s join fetch s.responsavel join fetch s.residencia where s.idschedulemail = :idschedulemail") })

public class Schedulemail implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer idschedulemail;
	@JoinColumn(name = "idresponsavel", referencedColumnName = "idresponsavel")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Responsavel responsavel;
	private Integer idpaciente;
	private String diasemana;
	private String hora;
	private boolean enabled;
	@JoinColumn(name = "idresidencia", referencedColumnName = "idresidencia")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Residencia residencia;
	@Transient
	private Integer[] dias;

	public Integer getIdschedulemail() {
		return idschedulemail;
	}

	public void setIdschedulemail(Integer idschedulemail) {
		this.idschedulemail = idschedulemail;
	}

	public Responsavel getResponsavel() {
		if (responsavel == null) {
			responsavel = new Responsavel();
		}
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public Integer getIdpaciente() {
		return idpaciente;
	}

	public void setIdpaciente(Integer idpaciente) {
		this.idpaciente = idpaciente;
	}

	public String getDiasemana() {
		return diasemana;
	}

	public void setDiasemana(String diasemana) {
		this.diasemana = diasemana;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public Residencia getResidencia() {
		if(residencia == null){
			residencia = new Residencia();
		}
		return residencia;
	}

	public void setResidencia(Residencia residencia) {
		this.residencia = residencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idschedulemail == null) ? 0 : idschedulemail.hashCode());
		return result;
	}

	public Integer[] getDias() {
		if (diasemana != null && !diasemana.isEmpty()) {
			String[] _dias = diasemana.split(",");
			int _diaslenght = _dias.length;
			dias = new Integer[_diaslenght];
			for (int i = 0; i < _diaslenght; i++) {
				dias[i] = Integer.valueOf(_dias[i]);
			}
		}
		return dias;
	}

	public void setDias(Integer[] dias) {
		this.dias = dias;
		if (dias != null && dias.length > 0) {
			diasemana = "";
			boolean first = true;
			for (Integer _dia : dias) {
				if (first) {
					diasemana = String.valueOf(_dia);
				} else {
					diasemana = diasemana.concat(",").concat(String.valueOf(_dia));
				}
				first = false;
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedulemail other = (Schedulemail) obj;
		if (idschedulemail == null) {
			if (other.idschedulemail != null)
				return false;
		} else if (!idschedulemail.equals(other.idschedulemail))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Schedulemail [idschedulemail=" + idschedulemail + ", idpaciente=" + idpaciente + ", diasemana="
				+ diasemana + ", hora=" + hora + ", enabled=" + enabled + "]";
	}



}
