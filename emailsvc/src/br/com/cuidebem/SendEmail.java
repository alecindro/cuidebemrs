package br.com.cuidebem;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
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

@Stateless
public class SendEmail {

	@Resource(mappedName = "java:/cuidebemmail")
	private Session mailSession;

	@Asynchronous
	public void send(String to_email, String subject, String content, String type_content) {
		try {
			MimeMessage m = new MimeMessage(mailSession);
			m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to_email));
			m.setSubject(subject);
			m.setSentDate(new java.util.Date());
			m.setContent(content, type_content);
			Transport.send(m);
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();

		}
	}

	@Asynchronous
	public void send(String to_email, String subject, String content, String type_content, File file) {
		try {
			MimeMessage m = new MimeMessage(mailSession);
			Address[] to = new InternetAddress[] { new InternetAddress(to_email) };
			m.setRecipients(Message.RecipientType.TO, to);
			m.setSubject(subject);
			m.setSentDate(new java.util.Date());
			m.setContent(content, type_content);
			m.saveChanges();
			FileDataSource fds = new FileDataSource(file);
			m.setDataHandler(new DataHandler(fds));
			m.setFileName(file.getName());
			Transport.send(m);
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();

		}
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
	public void send(String to_email, String subject, String content, String type_content, List<FileMail> fileMails) {
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
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendMessage(Message message, String to_email) throws AddressException, MessagingException {
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to_email));
		Transport.send(message);
	}

}
