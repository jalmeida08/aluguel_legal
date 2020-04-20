package br.com.jsa.aluguellegal.repository;

import br.com.jsa.aluguellegal.model.Proprietario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietarioRepository extends CrudRepository<Proprietario, Integer> {

}
