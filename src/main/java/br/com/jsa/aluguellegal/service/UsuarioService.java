package br.com.jsa.aluguellegal.service;

import br.com.jsa.aluguellegal.Util;
import br.com.jsa.aluguellegal.config.JwtTokenUtil;
import br.com.jsa.aluguellegal.exceptions.ChaveAtivacaoUsuarioNaoLocalizadoException;
import br.com.jsa.aluguellegal.exceptions.UsuarioJaCadastradoException;
import br.com.jsa.aluguellegal.exceptions.UsuarioNaoLocalizadoException;
import br.com.jsa.aluguellegal.model.Usuario;
import br.com.jsa.aluguellegal.repository.LocatarioRepository;
import br.com.jsa.aluguellegal.repository.ProprietarioRepository;
import br.com.jsa.aluguellegal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProprietarioRepository propietarioRepository;

	@Autowired
	private LocatarioRepository locatarioRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public UserDetails login(Usuario usuario) {
		Usuario findByUsuario = usuarioRepository.findByUsuario(usuario.getUsuario()).get();
		if(findByUsuario != null) {
			if(usuario.getUsuario().equals(findByUsuario.getUsuario()) && BCrypt.checkpw(usuario.getSenha(), findByUsuario.getSenha())) {
				return new User(findByUsuario.getUsuario(), findByUsuario.getSenha(), new ArrayList<>());
			} else {
				throw new RuntimeException("Senha inválida.");
			}
		} else {
			throw new UsernameNotFoundException("Usuário inválido");
		}
	}

	private UserDetails loginAutomaticoViaSistema(Usuario usuario){
		if(usuario != null) {
			return new User(usuario.getUsuario(), usuario.getSenha(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("Usuário inválido");
		}
	}

	public UserDetails dadosAutenticacaoAutorizacao(String usuario) {
		Usuario user = usuarioRepository.findByUsuario(usuario).get();
		return new User(user.getUsuario(), user.getSenha(), new ArrayList<>());
				
	}
	
	public Usuario buscarDadosUsuario(String usuario) {
		return usuarioRepository.findByUsuario(usuario).get();
	}

	public Usuario cadastrarUsuarioProprietario(Usuario usuario) {
		usuario.setPessoa(propietarioRepository.findById(usuario.getPessoa().getId()).get());
		usuario.setAtivo(false);
		usuario.setSenha(BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt()));
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		return gerarChaveAtivacaoUsuario(usuarioSalvo.getId());
	}

	public Usuario cadastrarUsuarioLocatario(Usuario usuario){
		usuario.setPessoa(locatarioRepository.findById(usuario.getPessoa().getId()).get());
		usuario.setAtivo(false);
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		return gerarChaveAtivacaoUsuario(usuarioSalvo.getId());
	}
	private Usuario gerarChaveAtivacaoUsuario(Integer id){
		Optional<Usuario> u = usuarioRepository.findById(id);
		if(u.isPresent()){
			Usuario usuario = u.get();
			usuario.setChaveAtivacao(Util.criptografar(usuario.getId().toString()));
			usuarioRepository.save(usuario);
			return usuario;
		}else{
			throw new RuntimeException("Não foi possível localizar o usuario");
		}
	}

	public void deletarUsuario(Integer id) {
		usuarioRepository.deleteById(id);
	}
	
	public Iterable<Usuario> listarUsuarios(){
		return usuarioRepository.findAll();
	}

	public Optional<Usuario> buscarUsuarioId(Integer id) {
		return usuarioRepository.findById(id);
	}


	public Usuario ativarUsuarioChave(String chaveUsuario) throws ChaveAtivacaoUsuarioNaoLocalizadoException {
		Optional<Usuario> u = usuarioRepository.findByChaveAtivacao(chaveUsuario);
		if(!u.isPresent())
			throw new ChaveAtivacaoUsuarioNaoLocalizadoException();

		u.get().setAtivo(true);
		Usuario user = usuarioRepository.save(u.get());
		UserDetails userDetails = loginAutomaticoViaSistema(user);
		user.setToken(jwtTokenUtil.generateToken(userDetails));
		user.setSenha("");
		return user;
	}

	public Usuario buscarInformacoesPorChaveAtivacao(String chaveAtivacao) throws ChaveAtivacaoUsuarioNaoLocalizadoException {
		Optional<Usuario> u = usuarioRepository.findByChaveAtivacao(chaveAtivacao);
		if(!u.isPresent())
			throw new ChaveAtivacaoUsuarioNaoLocalizadoException();
		return u.get();
	}

	public Usuario finalizarCadastroUsuario(Usuario usuario) throws UsuarioNaoLocalizadoException, UsuarioJaCadastradoException {
		Optional<Usuario> u = null;
		u = usuarioRepository.findByUsuario(usuario.getUsuario());
		if(u.isPresent())
			throw new UsuarioJaCadastradoException();
//		if(!u.isPresent())
//			throw new UsuarioNaoLocalizadoException();

		u = usuarioRepository.findById(usuario.getId());
		u.get().setAtivo(true);
		u.get().setSenha(BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt()));
		u.get().setUsuario(usuario.getUsuario());
		Usuario usuarioSalvo = usuarioRepository.save(u.get());
		UserDetails userDetails = this.loginAutomaticoViaSistema(usuarioSalvo);
		usuarioSalvo.setToken(jwtTokenUtil.generateToken(userDetails));
		usuarioSalvo.setSenha("");
		return usuarioSalvo;
	}
}
