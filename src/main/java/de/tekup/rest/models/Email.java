package de.tekup.rest.models;

import lombok.Data;

@Data
public class Email {
 String toEmail;
 String fromEmail;
 String subject;
 String body;
 
}
