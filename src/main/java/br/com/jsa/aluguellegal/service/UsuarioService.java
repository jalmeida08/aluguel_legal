package br.com.jsa.aluguellegal.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import br.com.jsa.aluguellegal.model.Proprietario;
import br.com.jsa.aluguellegal.model.Usuario;
import br.com.jsa.aluguellegal.repository.ProprietarioRepository;
import br.com.jsa.aluguellegal.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProprietarioRepository propietarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public UserDetails login(Usuario usuario) {
		Usuario findByUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
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

	public UserDetails dadosAutenticacaoAutorizacao(String usuario) {
		Usuario user = usuarioRepository.findByUsuario(usuario);
		return new User(user.getUsuario(), user.getSenha(), new ArrayList<>());
				
	}
	
	public Usuario buscarDadosUsuario(String usuario) {
		return usuarioRepository.findByUsuario(usuario);
	}

	public Usuario cadastrarUsuario(Usuario usuario) {
		Optional<Proprietario> findById = propietarioRepository.findById(usuario.getPessoa().getId());
		String senha = usuario.getSenha();
		usuario.setSenha(BCrypt.hashpw(senha, BCrypt.gensalt()));
		usuario.setPessoa(findById.get());
		return usuarioRepository.save(usuario);
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
	

}
