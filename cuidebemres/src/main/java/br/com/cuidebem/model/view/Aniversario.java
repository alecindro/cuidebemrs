package br.com.cuidebem.model.view;

import java.io.Serializable;

public class Aniversario implements Serializable{
	
	public static final String FIND_NIVER = "SELECT idpaciente as id,nome,apelido, "
			+ "DATE_FORMAT(datanascimento,'%d-%m') as dtniver, 0 as tipousuario, null as resp_paciente, datanascimento as dt "
			+ "FROM  paciente "
			+ "WHERE  idresidencia = ?1 and enabled = 1 and DATE_ADD(datanascimento, "
			+ " INTERVAL YEAR(CURDATE())-YEAR(datanascimento) YEAR)  "
			+ "BETWEEN date_sub(curdate(),interval 2 DAY) AND DATE_ADD(CURDATE(), INTERVAL 30 DAY) "
			+ "union "
			+ "SELECT u.idusuario as id,u.nome,u.apelido,DATE_FORMAT(u.datanascimento,'%d-%m') as dtniver, "
			+ "1 as tipousuario, null as resp_paciente,u.datanascimento as dt "
			+ "FROM  usuario u inner join usuario_residencia ur on u.idusuario = ur.idusuario "
			+ "WHERE  ur.idresidencia = ?2 and  u.enabled = 1 and DATE_ADD(u.datanascimento, "
			+ " INTERVAL YEAR(CURDATE())-YEAR(u.datanascimento) YEAR)  "
			+ "BETWEEN date_sub(curdate(),interval 2 DAY) AND DATE_ADD(CURDATE(), INTERVAL 30 DAY) "
			+ "union "
			+ "SELECT r.idresponsavel as id,r.nome,r.apelido,DATE_FORMAT(r.datanascimento,'%d-%m') as dtniver, "
			+ "2 as tipousuario, p.idpaciente as resp_paciente, r.datanascimento as dt "
			+ "FROM  responsavel r inner join responsavel_paciente rp on r.idresponsavel = rp.idresponsavel "
			+ "inner join paciente p on p.idpaciente = rp.idpaciente  "
			+ "WHERE  p.idresidencia = ?3 and p.enabled = 1 and DATE_ADD(r.datanascimento, "
			+ " INTERVAL YEAR(CURDATE())-YEAR(r.datanascimento) YEAR)  "
			+ "BETWEEN date_sub(curdate(),interval 2 DAY) AND DATE_ADD(CURDATE(), INTERVAL 30 DAY) order by DATE_FORMAT(dt,'%m%d') asc";
	
	public static final String LINK_PACIENTE = "link_paciente";
	public static final String LINK_USUARIO = "link_usuario";
	public static final String LINK_RESPONSAVEL = "link_responsavel";
	public static final String MESSAGE = "message_aniversario";
	
	private static final long serialVersionUID = 1L;
	private String message;	
	private String link;
	
	
	
	public Aniversario() {
		super();
	}
	
	public Aniversario(String message, String link) {
		super();
		this.message = message;
		this.link = link;
	}


	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		Aniversario other = (Aniversario) obj;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Aniversario [message=" + message + ", link=" + link + "]";
	}
	
	
	
	

}
