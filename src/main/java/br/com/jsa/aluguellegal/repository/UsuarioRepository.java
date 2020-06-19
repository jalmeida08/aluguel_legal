package br.com.jsa.aluguellegal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.jsa.aluguellegal.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{
	
	public Usuario findByUsuario(String usuario);

	public Usuario findByChaveAtivacao(String chaveUsuario);
}
