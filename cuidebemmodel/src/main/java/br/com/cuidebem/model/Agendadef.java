package br.com.cuidebem.model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(catalog = "cuidebemres", schema = "",name="agendadef")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agendadef.findAll", query = "SELECT a FROM Agendadef a")
    , @NamedQuery(name = "Agendadef.findByIdagendadef", query = "SELECT a FROM Agendadef a WHERE a.idagendadef = :idagendadef")
    , @NamedQuery(name = "Agendadef.findByDatainicio", query = "SELECT a FROM Agendadef a WHERE a.datainicio = :datainicio")
    , @NamedQuery(name = "Agendadef.findByDatafim", query = "SELECT a FROM Agendadef a WHERE a.datafim = :datafim")
    , @NamedQuery(name = "Agendadef.findByDataRegistro", query = "SELECT a FROM Agendadef a WHERE a.dataRegistro = :dataRegistro")
    , @NamedQuery(name = "Agendadef.findByHorario", query = "SELECT a FROM Agendadef a WHERE a.horario = :horario")
    , @NamedQuery(name = "Agendadef.findByRepetirHoras", query = "SELECT a FROM Agendadef a WHERE a.repetirHoras = :repetirHoras")
    , @NamedQuery(name = "Agendadef.findByDiasemana", query = "SELECT a FROM Agendadef a WHERE a.diasemana = :diasemana")
    , @NamedQuery(name = "Agendadef.findByGrupoevento", query = "SELECT a FROM Agendadef a WHERE a.grupoevento = :grupoevento")
    , @NamedQuery(name = "Agendadef.findBySubgrupoevento", query = "SELECT a FROM Agendadef a WHERE a.subgrupoevento = :subgrupoevento")
    , @NamedQuery(name = "Agendadef.findByObservacao", query = "SELECT a FROM Agendadef a WHERE a.observacao = :observacao")
    , @NamedQuery(name = "Agendadef.findByDiaspersonalizado", query = "SELECT a FROM Agendadef a WHERE a.diaspersonalizado = :diaspersonalizado")
    ,@NamedQuery(name = "Agendadef.findEnabledByPaciente", query = "SELECT a FROM Agendadef a WHERE a.enabled = true and a.idpaciente = :idpaciente order by a.horario asc")})


public class Agendadef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idagendadef;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date datainicio;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date datafim;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String horario;
    private Integer repetirHoras;
    @Size(max = 255)
    private String diasemana;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String grupoevento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String subgrupoevento;
    @Size(max = 255)
    private String observacao;
    @Basic(optional = false)
    @NotNull
    private boolean diaspersonalizado;
    private Integer idpaciente;
    @javax.persistence.Transient
    private Integer[] dias;
    @javax.persistence.Transient
    private String namedays;
    @Basic(optional = false)
    @NotNull
    private boolean enabled = true;
    
    
	

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Integer getRepetirHoras() {
		return repetirHoras;
	}

	public void setRepetirHoras(Integer repetirHoras) {
		this.repetirHoras = repetirHoras;
	}
	
	

	public Integer getIdagendadef() {
		return idagendadef;
	}

	public void setIdagendadef(Integer idagendadef) {
		this.idagendadef = idagendadef;
	}

	public Date getDatainicio() {
		return datainicio;
	}

	public void setDatainicio(Date datainicio) {
		this.datainicio = datainicio;
	}

	public Date getDatafim() {
		return datafim;
	}

	public void setDatafim(Date datafim) {
		this.datafim = datafim;
	}

	public String getDiasemana() {
		return diasemana;
	}

	public void setDiasemana(String diasemana) {
		this.diasemana = diasemana;
		
	}

	public String getGrupoevento() {
		return grupoevento;
	}

	public void setGrupoevento(String grupoevento) {
		this.grupoevento = grupoevento;
	}

	public String getSubgrupoevento() {
		return subgrupoevento;
	}

	public void setSubgrupoevento(String subgrupoevento) {
		this.subgrupoevento = subgrupoevento;
	}

	
	public Integer[] getDias() {
		if (diasemana != null && !diasemana.isEmpty()) {
			String[] _dias = diasemana.split(",");
			int _diaslenght = _dias.length;
			dias = new Integer[_diaslenght];
			for(int i = 0 ;i<_diaslenght;i++){
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
			for(Integer _dia : dias){
				if(first){
					diasemana = String.valueOf(_dia);
				}else{
				diasemana = diasemana.concat(",").concat(String.valueOf(_dia));
				}
				first = false;
			}
		}
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getIdpaciente() {
		return idpaciente;
	}

	public void setIdpaciente(Integer idpaciente) {
		this.idpaciente = idpaciente;
	}

	public boolean isDiaspersonalizado() {
		return diaspersonalizado;
	}

	public void setDiaspersonalizado(boolean diaspersonalizado) {
		this.diaspersonalizado = diaspersonalizado;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	
	

	public String getNamedays() {
		if(diasemana != null && !diasemana.isEmpty()){
			Integer[] _days = getDias();
			boolean first = true;
			namedays = "";
			for(Integer _day : _days){
				String name = DayOfWeek.of(_day).getDisplayName(TextStyle.FULL, Locale.getDefault());
				if(first){
					namedays = name;
				}else{
					namedays = namedays.concat(", ").concat(name);
				}
				first = false;
			}
		}
		return namedays;
	}

	public void setNamedays(String namedays) {
		this.namedays = namedays;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idagendadef == null) ? 0 : idagendadef.hashCode());
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
		Agendadef other = (Agendadef) obj;
		if (idagendadef == null) {
			if (other.idagendadef != null)
				return false;
		} else if (!idagendadef.equals(other.idagendadef))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Agendadef [idagendadef=" + idagendadef + ", datainicio=" + datainicio + ", datafim=" + datafim
				+ ", dataRegistro=" + dataRegistro + ", horario=" + horario + ", repetirHoras=" + repetirHoras
				+ ", diasemana=" + diasemana + ", grupoevento=" + grupoevento + ", subgrupoevento=" + subgrupoevento
				+ ", observacao=" + observacao + ", diaspersonalizado=" + diaspersonalizado + ", idpaciente="
				+ idpaciente + ", dias=" + Arrays.toString(dias) + "]";
	}
	
	

}