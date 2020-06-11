package br.com.jsa.aluguellegal.controller;

import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@PostMapping(value = "/login", consumes={"application/json"})
	public ResponseEntity<?> createAuthenticationToken(@RequestBody Usuario usuario) {
		try {
			Usuario user = new Usuario();

			final UserDetails userDetails = usuarioService.login(usuario);			
			user = usuarioService.buscarDadosUsuario(usuario.getUsuario());
			user.setSenha("");
			user.setToken(jwtTokenUtil.generateToken(userDetails));
			
			return ResponseEntity.ok(user);
		}catch (RuntimeException e) {
			 return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/salvar", consumes={"application/json"})
	public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario) {
		usuarioService.cadastrarUsuario(usuario);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/remover")
	public ResponseEntity<?> deletarUsuario(Integer id) {
		usuarioService.deletarUsuario(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/listar-usuarios")
	public ResponseEntity<Iterable<Usuario>> listarUsuarios() {
		Iterable<Usuario> usuarios = usuarioService.listarUsuarios();
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Usuario>> buscarUsuarioId(@PathVariable("id") Integer id) {
		Optional<Usuario> usuario = usuarioService.buscarUsuarioId(id);
		if(usuario.isPresent())
			usuario.get().setSenha("");
		return ResponseEntity.ok(usuario);
	}
	
}
