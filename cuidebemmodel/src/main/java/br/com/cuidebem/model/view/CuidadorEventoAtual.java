package br.com.cuidebem.model.view;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Cacheable
@SqlResultSetMapping(name = "eventoCuidadoratual", entities = @EntityResult(entityClass = CuidadorEventoAtual.class))
@NamedNativeQuery(name = "CuidadorEventoAtual.findAllByResidencia", query = "  select p.idusuario as idcuidador, p.apelido as apelido,  count(s.idusuario) as qtdade, (count(s.idusuario) != 0) "
		+ "as enabled  from usuario p left join (select e.grupoevento, e.idpaciente, e.idusuario, e.dataevento "
		+ "from evento e where date(e.dataevento) = date(now())) "
		+ " s on p.idusuario = s.idusuario "
		+ "inner join usuario_residencia ur on ur.idusuario = p.idusuario "
		+ "where ur.idresidencia = ?1 and p.enabled =1 and p.tipousuario = 'cuidador' "
		+ "group by p.idusuario", resultSetMapping = "eventoCuidadoratual")
@XmlRootElement
public class CuidadorEventoAtual implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer idcuidador;
	private Integer qtdade;
	private String apelido;
	private Boolean enabled;

	public Integer getIdcuidador() {
		return idcuidador;
	}

	public void setIdcuidador(Integer idcuidador) {
		this.idcuidador = idcuidador;
	}

	public Integer getQtdade() {
		return qtdade;
	}

	public void setQtdade(Integer qtdade) {
		this.qtdade = qtdade;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idcuidador == null) ? 0 : idcuidador.hashCode());
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
		CuidadorEventoAtual other = (CuidadorEventoAtual) obj;
		if (idcuidador == null) {
			if (other.idcuidador != null)
				return false;
		} else if (!idcuidador.equals(other.idcuidador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CuidadorEventoAtual [idcuidador=" + idcuidador + ", qtdade=" + qtdade + ", apelido=" + apelido
				+ ", enabled=" + enabled + "]";
	}

}
