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
@Table(catalog = "cuidebemres", schema = "", name="memorando")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Memorando.findByDataRegistro", query = "SELECT m FROM Memorando m where m.dataregistro = :dataregistro and m.idpaciente = :idpaciente")
})
@NamedNativeQueries({
	@NamedNativeQuery(name="Memorando.findAtual",query="SELECT * from memorando m where m.dataregistro >= curdate() and m.idpaciente = ?1",resultClass=Memorando.class)
})
public class Memorando implements Serializable{

	  private static final long serialVersionUID = 1L;
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    private Integer idmemorando;
	    @Basic(optional = false)
	    private Integer idpaciente;
	    @Basic(optional = false)
	    private Date dataregistro;
	    
	    private Date dataalteracao;
	    @Basic(optional = false)
	    private String descricao;
		public Integer getIdmemorando() {
			return idmemorando;
		}
		public void setIdmemorando(Integer idmemorando) {
			this.idmemorando = idmemorando;
		}
		public Integer getIdpaciente() {
			return idpaciente;
		}
		public void setIdpaciente(Integer idpaciente) {
			this.idpaciente = idpaciente;
		}
		public Date getDataregistro() {
			return dataregistro;
		}
		public void setDataregistro(Date dataregistro) {
			this.dataregistro = dataregistro;
		}
		public Date getDataalteracao() {
			return dataalteracao;
		}
		public void setDataalteracao(Date dataalteracao) {
			this.dataalteracao = dataalteracao;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((idmemorando == null) ? 0 : idmemorando.hashCode());
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
			Memorando other = (Memorando) obj;
			if (idmemorando == null) {
				if (other.idmemorando != null)
					return false;
			} else if (!idmemorando.equals(other.idmemorando))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Memorando [idmemorando=" + idmemorando + ", idpaciente=" + idpaciente + ", dataregistro="
					+ dataregistro + ", dataalteracao=" + dataalteracao + ", descricao=" + descricao + "]";
		}
	    
	    
}
