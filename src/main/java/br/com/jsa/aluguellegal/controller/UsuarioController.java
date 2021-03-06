package br.com.jsa.aluguellegal.controller;

import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.jsa.aluguellegal.exceptions.ChaveAtivacaoUsuarioNaoLocalizadoException;
import br.com.jsa.aluguellegal.exceptions.UsuarioJaCadastradoException;
import br.com.jsa.aluguellegal.exceptions.UsuarioNaoLocalizadoException;
import br.com.jsa.aluguellegal.service.MensageriaService;
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

	@Autowired
	private MensageriaService mensageriaService;

	@PostMapping("/login")
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
	
	@PostMapping(value = "/salvar-user-proprietario")
	public ResponseEntity<?> cadastrarUsuarioProprietario(@RequestBody Usuario usuario) {
		Usuario usuarioSalvo = usuarioService.cadastrarUsuarioProprietario(usuario);

		mensageriaService.enviarEmailNovoUsuario(usuarioSalvo.getEmail(),
				usuario.getPessoa().getNome(),
				usuario.getChaveAtivacao());

		return ResponseEntity.ok().build();
	}

    @PostMapping(value = "/salvar-user-locatario")
    public ResponseEntity<?> cadastrarUsuarioLocatario(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioService.cadastrarUsuarioLocatario(usuario);

        mensageriaService.enviarEmailNovoUsuario(usuarioSalvo.getEmail(),
                usuario.getPessoa().getNome(),
                usuario.getChaveAtivacao());

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

	@GetMapping("/ativar-chave-usuario/{chaveUsuario}")
	public ResponseEntity<?> ativarUsuarioChave(@PathVariable("chaveUsuario") String chaveUsuario){

		try {
			Usuario usuario = usuarioService.ativarUsuarioChave(chaveUsuario);
			return ResponseEntity.ok(usuario);
		} catch (ChaveAtivacaoUsuarioNaoLocalizadoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("/buscar-informacoes-por-chave-ativacao/{chaveAtivacao}")
    public ResponseEntity<?> buscarInformacoesPorChaveAtivacao(@PathVariable("chaveAtivacao") String chaveAtivacao){

		try {
			Usuario u = usuarioService.buscarInformacoesPorChaveAtivacao(chaveAtivacao);
			return ResponseEntity.ok(u);
		} catch (ChaveAtivacaoUsuarioNaoLocalizadoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }

    @PostMapping("/finalizar-cadastro-usuario")
	public ResponseEntity<?> finalizarCadastroUsuario(@RequestBody Usuario usuario){
		try {
			Usuario u = usuarioService.finalizarCadastroUsuario(usuario);
			mensageriaService.enviarEmailCadastroProprietario(u.getEmail(),
					u.getPessoa().getNome(),
					u.getChaveAtivacao(),
					u.getUsuario());
			return ResponseEntity.ok(u);
		} catch (UsuarioNaoLocalizadoException | UsuarioJaCadastradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	
}
