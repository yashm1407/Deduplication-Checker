package com.fileshare;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class SendMail {
	public static void mailSend(String FileName,String Key,String ReciverMailId) {
		String to=ReciverMailId;
	    String subject="File Access Key Info";
	    String msg="FileName is shared with you as=> "+FileName+".\nYour access this file key is as => "+Key;
	   final String user="projectinfoieee@gmail.com";
	   final String pass="project@123456";// send mail qrcode
	   Properties props = new Properties();
	   props.put("mail.smtp.host", "smtp.gmail.com");
	   props.put("mail.smtp.port", "587"); //this is optional
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.starttls.enable", "true");

	   Session session = Session.getInstance(props,new javax.mail.Authenticator() {    
	   @Override
	   protected PasswordAuthentication getPasswordAuthentication() {
	   return new PasswordAuthentication(user,pass);
	  }
	  });
	   
	   try {
	  MimeMessage message = new MimeMessage(session);
	  message.setFrom(new InternetAddress(user));
	  message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
	  message.setSubject(subject);
	  message.setText(msg);
	  
	   Transport.send(message);
	       System.out.println("sending");
	       System.out.println("Send successfully");
	       
	       
	    }
	   catch(Exception e) 
	   {
	       System.out.println(e);
	   }	
		
	}
	
	public static void main(String[] args) {
		
		mailSend("FileName","Key","jitusunsofttech@gmail.com");	
	}
}



