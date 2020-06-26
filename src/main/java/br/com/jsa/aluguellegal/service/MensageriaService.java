package br.com.jsa.aluguellegal.service;

import javax.mail.internet.MimeMessage;

import br.com.jsa.aluguellegal.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MensageriaService {

	@Autowired private JavaMailSender mailSender;
	
	private boolean enviarEmail(String destinatario, String tituloEmail, String textoEmail) {
		
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

	public void enviarEmailNovoUsuario(String destinatario, String nome, String chaveAtivacao){
		final String tituloEmail = "Bem Vindo ao Aluguel Legal";
		String linkParaAtivarUsuario = "http://localhost:4200/administracao/usuario/confirm/p";
		linkParaAtivarUsuario += chaveAtivacao;
		String textoEmail = "<html> <head>"
		+ "<style>"
		+ ".botao:hover {background-color: rgba(135,206,250,0.5)}"
		+ ".botao {background-color: #87CEFA; padding: 10px; color: #000; border-radius: 3px; text-decoration: none}"
		+ "</style>"
		+ "</head> <body>"
		+ "<h1>"+ nome +", seja muito bem vindo(a). </h1>"
		+ "<br>"
		+ "<p>Clique no botão para ativar o seu usuário: "
		+ "<a class='botao' href='"+linkParaAtivarUsuario+"'>Ativar Usuário</a></p>"
		+ "<br><br><br><br><br><br><br><br>"
		+ "Caso o botão não funcione, clique no link: <a href="+ linkParaAtivarUsuario +">" + linkParaAtivarUsuario + "</a>"
		+ "</body></html>";
		enviarEmail(destinatario, tituloEmail, textoEmail);
	}

	public void enviarEmailNovoProprietario(String destinatario, String nome, String chaveAtivacao){
		final String tituloEmail = "Bem Vindo ao Aluguel Legal";
		String linkParaAtivarUsuario = "http://localhost:4200/administracao/usuario/confirm/l";
		linkParaAtivarUsuario += chaveAtivacao;
		String textoEmail = "<html> <head>"
				+ "<style>"
				+ ".botao:hover {background-color: rgba(135,206,250,0.5)}"
				+ ".botao {background-color: #87CEFA; padding: 10px; color: #000; border-radius: 3px; text-decoration: none}"
				+ "</style>"
				+ "</head> <body>"
				+ "<h1>"+ nome +", seja muito bem vindo(a). </h1>"
				+ "<br>"
				+ "<p>Clique no botão para ativar o seu usuário: "
				+ "<a class='botao' href='"+linkParaAtivarUsuario+"'>Ativar Usuário</a></p>"
				+ "<br><br><br><br><br><br><br><br>"
				+ "Caso o botão não funcione, clique no link: <a href="+ linkParaAtivarUsuario +">" + linkParaAtivarUsuario + "</a>"
				+ "</body></html>";
		enviarEmail(destinatario, tituloEmail, textoEmail);
	}

	public void enviarEmailCadastroProprietario(String destinatario, String nome, String chaveAtivacao, String usuario){
		final String tituloEmail = "Cadastro efetuado com sucesso";
		String linkLogin = "http://localhost:4200/";
		String textoEmail = "<html> <head>"
				+ "<style>"
				+ ".botao:hover {background-color: rgba(135,206,250,0.5)}"
				+ ".botao {background-color: #87CEFA; padding: 10px; color: #000; border-radius: 3px; text-decoration: none}"
				+ "</style>"
				+ "</head> <body>"
				+ "<h3>Olá "+ nome +", O seu cadastro foi efetuado e já se encontra ativo.</h3>"
				+ "<br>"
				+ "<p>Utilize o seu usuário cadastrado para efetuar os próximos acessos ao sistema do Aluguel Legal."
				+ "<p>Usuario:"+ usuario +" </p>"
				+ "<p>Acesse o link para efetuar login:<a href="+ linkLogin +">Clique aqui</a></p>"
				+ "</body></html>";
		enviarEmail(destinatario, tituloEmail, textoEmail);
	}
}
