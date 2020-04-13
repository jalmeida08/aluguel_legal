package br.com.jsa.aluguellegal.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jsa.aluguellegal.config.JwtTokenUtil;
import br.com.jsa.aluguellegal.model.Usuario;
import br.com.jsa.aluguellegal.service.UsuarioService;

@RestController
@RequestMapping("usuario")
@CrossOrigin
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody Usuario usuario) {
		try {
			Usuario user = new Usuario();

			final UserDetails userDetails = usuarioService.login(usuario);			
			user = usuarioService.buscarDadosUsuario(usuario.getUsuario());
			user.setToken(jwtTokenUtil.generateToken(userDetails));
			
			return ResponseEntity.ok(user);
		}catch (RuntimeException e) {
			 return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
		}
	}
	
}
