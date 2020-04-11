package br.com.jsa.aluguellegal.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import br.com.jsa.aluguellegal.model.Usuario;
import br.com.jsa.aluguellegal.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UserDetails login(Usuario usuario) {
		Usuario findByUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
		if(usuario.getUsuario().equals(findByUsuario.getUsuario())) {
			if(BCrypt.checkpw(usuario.getSenha(), findByUsuario.getSenha())) {
				return new User(findByUsuario.getUsuario(), findByUsuario.getSenha(), new ArrayList<>());
			} else {
				throw new RuntimeException("Senha inválida.");
			}
		} else {
			System.out.println("SENHA INCORRETA");
			throw new UsernameNotFoundException("User not found with username: " + usuario.getUsuario());
		}
	}

}
