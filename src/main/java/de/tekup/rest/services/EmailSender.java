package de.tekup.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender 
{@Autowired
	private JavaMailSender mailSender;
public void sendEmail(String toEmail, String subject,String body)
{
	SimpleMailMessage message = new SimpleMailMessage();
	message.setFrom("ttaem0199@gmail.com");
	message.setTo(toEmail);
	message.setText(body);
	message.setSubject(subject);
	mailSender.send(message);
	System.out.println("mail sent....");
	
}

public void contactUs(String fromEmail, String subject,String body)
{
	SimpleMailMessage message = new SimpleMailMessage();
	message.setFrom(fromEmail);
	message.setTo("ttaem0199@gmail.com");
	message.setText(body);
	message.setSubject(subject);
	mailSender.send(message);
	System.out.println("mail sent....");
	
}
}