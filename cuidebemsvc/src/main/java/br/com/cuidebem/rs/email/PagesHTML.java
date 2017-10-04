package br.com.cuidebem.rs.email;

public class PagesHTML {
	
	protected static String return_confirma = "<!DOCTYPE html>"+
			"<html>"+
			"<head>"+
			"<meta charset='UTF-8'>"+
			"<meta http-equiv='refresh' content=\"1;URL='http://www.cuidebemapp.com/'\" />"+
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
			"<div style='text-align:center'>"+
			"<h2>Termo de Aceite</h2>"+
			"</div>"+
			"<p>Cadastro confirmado com sucesso.</p>"+
			"<footer style='text-align:center'>"+
			"<a href='http://www.cuidebemapp.com' class='btn btn-success'>Acessar Cuide Bem</a>"+
			"</footer>"+
			"<div style='text-align:center'>"+
			"<img src='img/logo.png'/>"+
			"</div>"+
			"</blockquote>"+
			"</body>"+
			"</html>";
				
	protected static String return_oops = "<!DOCTYPE html>"+
						"<html>"+
						"<head>"+
						"<meta charset='UTF-8'>"+
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
						"<div style='text-align:center'>"+
						"<h2>Termo de Aceite</h2>"+
						"</div>"+
						"<p style='color:red'>#{message}</p>"+
						
						"<div style='text-align:center'>"+
						"<img src='/resources/img/logo.png'/>"+
						"</div>"+
						"</blockquote>"+
						"</body>"+
						"</html>";
				
				protected static final String message = "#{message}";
				protected static final String oops = "OOps. Tivemos problemas ao ativar seu cadastro. Por favor tente novamente.";
				
				protected static String return_desbloqueio = "<!DOCTYPE html>"+
						"<html>"+
						"<head>"+
						"<meta charset='UTF-8'>"+
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
						"<div style='text-align:center'>"+
						"<h2>Desbloqueio de usuário</h2>"+
						"<p>Usuário desbloqueado com sucesso.</p>"+
						"</div>"+
						"<footer style='text-align:center'>"+
						"<a href='#{link}' class='btn btn-success'>Acessar Cuide Bem</a>"+
						"</footer>"+
						"<div style='text-align:center'>"+
						"<img src='/resources/img/logo.png'/>"+
						"</div>"+
						"</blockquote>"+
						"</body>"+
						"</html>";
				
				protected static final String oops_desbloqueio = "OOps. Tivemos problemas ao desbloquear seu cadastro. Por favor tente novamente.";
				
				protected static String return_oops_desbloqueio = "<!DOCTYPE html>"+
						"<html>"+
						"<head>"+
						"<meta charset='UTF-8'>"+
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
						"<div style='text-align:center'>"+
						"<h2>Desbloqueio</h2>"+			
						"<p style='color:red'>#{message}</p>"+
						"</div>"+
						"<div style='text-align:center'>"+
						"<img src='/resources/img/logo.png'/>"+
						"</div>"+
						"</blockquote>"+
						"</body>"+
						"</html>";
				


				protected static String return_confirma_cuidador = "<!DOCTYPE html>"+
						"<html>"+
						"<head>"+
						"<meta charset='UTF-8'>"+
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
						"<div style='text-align:center'>"+
						"<h2>Cuidador Convidado</h2>"+
						"</div>"+
						"<p>Convite confirmado com sucesso.</p>"+
						"<footer style='text-align:center'>"+
						"<a href='http://www.cuidebemapp.com' class='btn btn-success'>Acessar Cuide Bem</a>"+
						"</footer>"+
						"<div style='text-align:center'>"+
						"<img src='/resources/img/logo.png'/>"+
						"</div>"+
						"</blockquote>"+
						"</body>"+
						"</html>";
				
				protected static String return_cadastro_cuidador = "<!DOCTYPE html>"+
						"<html>"+
						"<head>"+
						"<meta charset='UTF-8'>"+
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
						"<div style='text-align:center'>"+
						"<h2>Cuidador Convidado</h2>"+
						"</div>"+
						"<p>Prezado #{email}, por favor faça o seu cadastro para validarmos seu convite clicando </p> <a href='http://www.cuidebemapp.com/resources/cadastroCuidador.xhtml?email=#{email}&idconvite=#{idconvite}' class='btn btn-success'>aqui</a>"+
						"<footer style='text-align:center'>"+
						"<a href='http://www.cuidebemapp.com/resources/cadastroCuidador.xhtml' class='btn btn-success'>Acessar Cuide Bem</a>"+
						"</footer>"+
						"<div style='text-align:center'>"+
						"<img src='/resources/img/logo.png'/>"+
						"</div>"+
						"</blockquote>"+
						"</body>"+
						"</html>";
				
				protected static final String email = "#{email}";
				protected static final String idconvite = "#{idconvite}";
				protected static final String _app = "#{app}";
				
				public static String desbloqueio(String app){
					return return_desbloqueio.replace(_app, app);
				}


}
