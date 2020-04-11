package br.com.jsa.aluguellegal.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jsa.aluguellegal.config.JwtTokenUtil;
import br.com.jsa.aluguellegal.model.JwtResponse;
import br.com.jsa.aluguellegal.model.Usuario;
import br.com.jsa.aluguellegal.service.JwtUserDetailsService;
import br.com.jsa.aluguellegal.service.UsuarioService;

@RestController
@RequestMapping("usuario")
@CrossOrigin
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioController {

//	@Autowired
//	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody Usuario usuario) throws Exception {
		
		final UserDetails userDetails = usuarioService.login(usuario);
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
}
