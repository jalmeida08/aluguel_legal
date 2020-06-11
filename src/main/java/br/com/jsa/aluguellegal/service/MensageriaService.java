package br.com.jsa.aluguellegal.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MensageriaService {

	@Autowired private JavaMailSender mailSender;
	
	public boolean enviarEmail(String destinatario, String tituloEmail, String textoEmail) {
		
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setTo(destinatario);
			helper.setSubject(tituloEmail);
			helper.setText(textoEmail, true);
			
			mailSender.send(mail);
			return true;
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
