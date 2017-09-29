package br.com.cuidebem;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class SendEmail {

	
	 @Resource(mappedName="java:/cuidebemmail")
	    private Session mailSession;
	 
	 @Asynchronous
	 public void send(String to_email, String subject, String content, String type_content){
		 try    {
             MimeMessage m = new MimeMessage(mailSession);
             //Address from = new InternetAddress(from_email);
             //Address[] to = new InternetAddress[] {new InternetAddress(to_email) };
             //m.setFrom(from);
             m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to_email));
             m.setSubject(subject);
             m.setSentDate(new java.util.Date());
             m.setContent(content,type_content);
             Transport.send(m);
         }
         catch (javax.mail.MessagingException e)
         {
             e.printStackTrace();
            
         }
	 }
	 @Asynchronous
	 public void send(String to_email, String subject, String content, String type_content, File file){
		 try    {
             MimeMessage m = new MimeMessage(mailSession);
             //Address from = new InternetAddress(from_email);
             Address[] to = new InternetAddress[] {new InternetAddress(to_email) };
             //m.setFrom(from);
             m.setRecipients(Message.RecipientType.TO, to);
             m.setSubject(subject);
             m.setSentDate(new java.util.Date());
             m.setContent(content,type_content);
             m.saveChanges();
             FileDataSource fds = new FileDataSource(file);
             m.setDataHandler(new DataHandler(fds));
             m.setFileName(file.getName());
             Transport.send(m);
         }
         catch (javax.mail.MessagingException e)
         {
             e.printStackTrace();
            
         }
	 }
	
	
}
