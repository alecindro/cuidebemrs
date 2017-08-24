package br.com.cuidebem;

import java.io.File;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class Emailsvc {
	
	private static final String subject_email = "Confirmar cadastro cuidebem";
	private static final String subject_report = "Relat\u00f3rio";
	private static final String subject_email_cuidador = "Convite cuidador cuidebem";
	private static final String desbloqued_subject_email = "Desbloquear usu\u00e1rio cuidebem";
	private static String content_email = "<!DOCTYPE html>"+
"<html>"+
"<head>"+
"<meta http-equiv='Content-type' content='text/html; charset=UTF-8'> "+
"<style>"+
".ui-button {"+
"display: inline-block;"+
 "position: relative;"+
  "padding: 0;"+
   "margin-right: .1em;"+
    "text-decoration: none!important;"+
    "cursor: pointer;"+
    "text-align: center;"+
    "zoom: 1;"+
    "overflow: visible;"+
"} "+
".btn {"+
 "display: inline-block;"+
  "padding: 6px 12px;"+
  "margin-bottom: 0;"+
  "font-size: 14px;"+
    "font-weight: 400;"+
    "line-height: 1.42857143;"+
    "text-align: center;"+
    "white-space: nowrap;"+
    "vertical-align: middle;"+
    "-ms-touch-action: manipulation;"+
    "touch-action: manipulation;"+
    "cursor: pointer;"+
    "-webkit-user-select: none;"+
    "-moz-user-select: none;"+
    "-ms-user-select: none;"+
    "user-select: none;"+
    "background-image: none;"+
    "border: 1px solid transparent;"+
    "border-radius: 4px;"+
"} "+
".btn-success {"+
    "background-image: -webkit-linear-gradient(top,#5cb85c 0,#419641 100%);"+
    "background-image: -o-linear-gradient(top,#5cb85c 0,#419641 100%);"+
    "background-image: -webkit-gradient(linear,left top,left bottom,from(#5cb85c),to(#419641));"+
    "background-image: linear-gradient(to bottom,#5cb85c 0,#419641 100%);"+
    "filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff5cb85c', endColorstr='#ff419641', GradientType=0);"+
    "filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);"+
    "background-repeat: repeat-x;"+
    "border-color: #3e8f3e;"+
	"color: #fff;"+
    "background-color: #5cb85c;"+
"} "+
".ui-button-text {"+
    "display: block;"+
    "line-height: normal;"+
	 "padding: .3em 1em;"+
"} "+
"</style>"+
"</head>"+
"<body>"+
"<blockquote>"+
"#{bodyemail}"+
"</body>"+
"</html>";

private static String bodyActivation = "<div style='text-align:center'>"+
		"<h2>Termo de Aceite</h2>"+
		"</div>"+
		"<p>Ol\u00e1 #{username},</>"+
"<p>Que bom que voc"+"\u00ea"+" se cadastrou no CuideBemApp, Parab\u00e9ns!</p>"+
"<p>O CuideBemApp oferece o acompanhamento organizado da rotina do paciente, de forma simples, para voc\u00ea ter acesso a qualquer hora e em qualquer lugar.</p>"+
"<p>CuideBemApp a melhor forma de acompanhar os cuidados de quem voc\u00ea ama, te deseja boas vindas!!</p>"+
"<br><p>Equipe CuideBem.</p>"+
		"<footer style='text-align:center'>"+
		"<a href='#{link}' class='btn btn-success'>Aceito os termos do acordo</a>"+
		"</footer>"+
		"<div style='text-align:center'>"+
		"<img src='http://www.cuidebemapp.com/resources/img/logo.png'/>"+
		"</div>"+
		"</blockquote>";	
	
	private static String bodyDesblocked= 
"<blockquote>"+
"<div style='text-align:center'>"+
"<h2>Desbloqueio de Usu\u00e1rio</h2>"+
"</div>"+
"<p>Seu usu\u00e1rio est\u00E1 bloqueado. Para desbloque\u00e1-lo, favor clicar no bot\u00e3o abaixo:</p>"+
"<footer style='text-align:center'>"+
"<a href='#{link}' class='btn btn-success'>Desbloquear Usu\u00e1rio</a>"+
"</footer>"+
"<div style='text-align:center'>"+
"<img src='http://www.cuidebemapp.com/resources/img/logo.png'/>"+
"</div>"+
"</blockquote>";
	
	
	private static String conviteCuidador = "<div style='text-align:center'>"+
			"<h2>Convite para ser cuidador</h2>"+
			"</div>"+
			"<p>Ol\u00e1 #{username},</p>"+
"<p>Voc\u00ea foi indicado como cuidador do paciente #{paciente}. </p>"+
"<p>Acesse o site: www.cuidebemapp.com.br e aceite o convite.</p>"+
"<p>CuideBemApp, o aplicativo que vai facilitar sua rotina de trabalho, te deseja boas vindas!!</p>"+
"<br><p>Equipe CuideBem.</p>"+
			"<footer style='text-align:center'>"+
			"<a href='#{link}' class='btn btn-success'>Aceito</a>"+
			"</footer>"+
			"<div style='text-align:center'>"+
			"<img src='http://www.cuidebemapp.com/resources/img/logo.png'/>"+
			"</div>"+
			"</blockquote>";
	
	private static String cadastroCuidador = "<div style='text-align:center'>"+
			"<h2>Convite para ser cuidador</h2>"+
			"</div>"+
			"<p>Ol\u00e1 #{username},</p>"+
"<p>#{nomeResponsavel} solicitou você como cuidador do paciente #{paciente}. </p>"+
"<p>Clique no botão abaixo e faça seu cadsastro.</p>"+
"<p>CuideBemApp, o aplicativo que vai facilitar sua rotina de trabalho, te deseja boas vindas!!</p>"+
"<br><p>Equipe CuideBem.</p>"+
			"<footer style='text-align:center'>"+
			"<a href='#{link}' class='btn btn-success'>Aceito</a>"+
			"</footer>"+
			"<div style='text-align:center'>"+
			"<img src='http://www.cuidebemapp.com/resources/img/logo.png'/>"+
			"</div>"+
			"</blockquote>";
	
	private static String alterarSenha = "<div style='text-align:center'>"+
			"<h2>Alterar Senha</h2>"+
			"</div>"+
			"<p>Ol\u00e1 #{username},</>"+
	"<p>Voc"+"\u00ea"+" se solicitou a alteraç\u00e3o da sua senha.</p>"+
	"<p>Clique no bot\u00e3o abaixo para alterar a senha</p>"+
	"<br><p>Equipe CuideBem.</p>"+
			"<footer style='text-align:center'>"+
			"<a href='#{link}' class='btn btn-success'>Alterar a senha</a>"+
			"</footer>"+
			"<div style='text-align:center'>"+
			"<img src='http://www.cuidebemapp.com/resources/img/logo.png'/>"+
			"</div>"+
			"</blockquote>";	
	
	private static String reportPaciente = "<div style='text-align:center'>"+
			"<h2>Relat\u00f3rio</h2>"+
			"</div>"+
			"<p>Ol\u00e1 #{username},</>"+
	"<p>Relat/u00f3rio do paciente solicitado em anexo.</p>"+
	"<br><p>Equipe CuideBem.</p>"+
			"<footer style='text-align:center'>"+
			"</footer>"+
			"<div style='text-align:center'>"+
			"<img src='http://www.cuidebemapp.com/resources/img/logo.png'/>"+
			"</div>"+
			"</blockquote>";	
	
	
	private static final String link = "#{link}";
	private static final String replace_user = "#{user}";
	private static final String idconvite = "#{idconvite}";
	private static final String nomeResponsavel = "#{nomeResponsavel}";
	private static final String confirma_link = "http://cuidebemapp.com/rest/confirma/#{user}";
	private static final String desbloqueio_link = "http://cuidebemapp.com/rest/desbloqueio/#{user}";
	private static final String convite_cuidador_link = "http://cuidebemapp.com/rest/confirmaCuidador/#{idconvite}/#{user}";
	private static final String cadastro_cuidador_link = "http://cuidebemapp.com/resources/cadastroCuidador.xhtml?email=#{user}&idconvite=#{idconvite}";
	private static String content_type = "text/html;charset=UTF-8";
	private static final String bodyEmail = "#{bodyemail}";
	private static final String username = "#{username}";
	private static final String paciente = "#{paciente}";
	private static final String alterarsenha_link = "http://cuidebemapp.com/resources/alterasenha.xhtml?email=#{user}";
	@EJB
	private SendEmail sendEmail;
	
	@Asynchronous
	public void confirmarEmail(String email, String name){
		String confirma = "";
		confirma = content_email.replace(bodyEmail, bodyActivation);
		confirma = confirma.replace(username, name);
		confirma = confirma.replace(link, confirma_link);
		confirma = confirma.replace(replace_user, email);		
		sendEmail.send(email, subject_email, confirma, content_type);
	}
	
	@Asynchronous
	public void desbloquearEmail(String email){
		String desbloquea = "";
		desbloquea = content_email.replace(bodyEmail, bodyDesblocked);
		desbloquea = desbloquea.replace(link, desbloqueio_link);
		desbloquea = desbloquea.replace(replace_user, email);
		
		sendEmail.send(email, desbloqued_subject_email, desbloquea, content_type);
	}
	
	@Asynchronous
	public void convidarCuidador(String nomeResponsavel, String email, String nomePaciente, int idconvite){
		String confirma = "";
		confirma = content_email.replace(bodyEmail, conviteCuidador);
		confirma = confirma.replace(Emailsvc.nomeResponsavel, nomeResponsavel);
		confirma = confirma.replace(username, email);
		confirma = confirma.replace(paciente, nomePaciente);
		confirma = confirma.replace(link, convite_cuidador_link);
		confirma = confirma.replace(Emailsvc.idconvite, String.valueOf(idconvite));
		confirma = confirma.replace(replace_user, email);		
		sendEmail.send(email, subject_email_cuidador, confirma, content_type);
	}
	@Asynchronous
	public void alteraSenha(String email,String emailEncrypt, String nomeUsuario){
		String altera = "";
		altera = content_email.replace(bodyEmail, alterarSenha);
		altera = altera.replace(username, nomeUsuario);
		altera = altera.replace(link, alterarsenha_link);
		altera = altera.replace(replace_user, emailEncrypt);
		sendEmail.send(email, "Alterar Senha", altera, content_type);
	}
	
	@Asynchronous
	public void cadastroCuidador(String nomeResponsavel, String email, String nomePaciente, int idconvite){
		String confirma = "";
		confirma = content_email.replace(bodyEmail, Emailsvc.cadastroCuidador);
		confirma = confirma.replace(Emailsvc.nomeResponsavel, nomeResponsavel);
		confirma = confirma.replace(username, email);
		confirma = confirma.replace(paciente, nomePaciente);
		confirma = confirma.replace(link, cadastro_cuidador_link);
		confirma = confirma.replace(Emailsvc.idconvite, String.valueOf(idconvite));
		confirma = confirma.replace(replace_user, email);		
		sendEmail.send(email, subject_email, confirma, content_type);
	}
	
	@Asynchronous
	public void relatorio(File file, String nomeResponsavel, String email){
		String report = "";
		report = content_email.replace(bodyEmail, Emailsvc.reportPaciente);
		report = report.replace(username, nomeResponsavel);
		sendEmail.send(email, subject_report, report, content_type,file);
	}
	
	
	
	
	

	
	

}
