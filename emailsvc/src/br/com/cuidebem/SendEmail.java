package br.com.cuidebem;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.cuidebem.controller.EmailException;
import br.com.cuidebem.controller.EmailJpaController;
import br.com.cuidebem.model.Emailenviado;

@Stateless
public class SendEmail {

	@Resource(mappedName = "java:/cuidebemmail")
	private Session mailSession;
	@EJB
	private EmailJpaController emailJpaController;
	

	@Asynchronous
	public void send(String to_email, String subject, String content, String type_content) {
		try {
			MimeMessage m = new MimeMessage(mailSession);
			m.setSubject(subject);
			m.setSentDate(new java.util.Date());
			m.setContent(content, type_content);
			sendMessage(m, to_email);
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();

		}
	}
	
	@Asynchronous
	public void send(String to_email, String subject, String content, String type_content, File file)
	{
		
	}

	
	@Asynchronous
	public void send(String to_email, String subject, String content, String cid, String type_content, File file) {
		try {
			MimeMessage m = new MimeMessage(mailSession);
			MimeMultipart mimeMultipart = new MimeMultipart("related");
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setContent(content, type_content);
			mimeMultipart.addBodyPart(textPart);
			MimeBodyPart imagePart = new MimeBodyPart();
			imagePart.attachFile(file);
			imagePart.setContentID("<" + cid + ">");
			imagePart.setDisposition(MimeBodyPart.INLINE);
			mimeMultipart.addBodyPart(imagePart);
			m.setContent(mimeMultipart);
			m.setSubject(subject);
			sendMessage(m, to_email);
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Asynchronous
	public void send(Integer idresidencia, String to_email, String subject, String content, String type_content, Date dataRelatorio, boolean automatic, List<FileMail> fileMails) {
		Emailenviado emailenviado = new Emailenviado ();
		emailenviado.setContent_type(type_content);
		emailenviado.setDataenvio(Calendar.getInstance().getTime());
		emailenviado.setSubject(subject);
		emailenviado.setTo_email(to_email);
		emailenviado.setDatarelatorio(dataRelatorio);
		emailenviado.setIdresidencia(idresidencia);
		emailenviado.setAutomatic(automatic);
		try {
			MimeMessage m = new MimeMessage(mailSession);
			MimeMultipart mimeMultipart = new MimeMultipart("related");
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setContent(content, type_content);
			mimeMultipart.addBodyPart(textPart);

			for (FileMail fileMail : fileMails) {
				MimeBodyPart imagePart = new MimeBodyPart();
				imagePart.attachFile(fileMail.getFile());
				imagePart.setContentID("<" + fileMail.getCid() + ">");
				imagePart.setDisposition(MimeBodyPart.INLINE);
				mimeMultipart.addBodyPart(imagePart);
			}

			m.setContent(mimeMultipart);
			m.setSubject(subject);
			sendMessage(m, to_email);
			emailenviado.setError(false);
			emailenviado.setMessage("Email enviado com sucesso");
		} catch (javax.mail.SendFailedException e) {
			String message = e.getMessage();
			if(e.getInvalidAddresses()!=null){
			  message = "Email Inválido: ";
			  for(Address addrees : e.getInvalidAddresses()){
				  message = message.concat(addrees.toString()).concat(";");
			  }
			}
			emailenviado.setError(true);
			emailenviado.setMessage(message);
			
		
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
			emailenviado.setError(true);
			emailenviado.setMessage(e.getMessage());
			
		} catch (IOException e) {
			e.printStackTrace();
			emailenviado.setError(true);
			emailenviado.setMessage(e.getMessage());
		}
		try {
			emailJpaController.create(emailenviado,content);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendMessage(Message message, String to_email) throws AddressException, MessagingException {
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to_email));
		Transport.send(message);
	}

}
