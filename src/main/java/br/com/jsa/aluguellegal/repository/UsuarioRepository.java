package br.com.jsa.aluguellegal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.jsa.aluguellegal.model.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{
	
	public Optional<Usuario> findByUsuario(String usuario);
	public Optional<Usuario> findByChaveAtivacao(String chaveUsuario);
	public Optional<Usuario> findByEmail(String email);
}
