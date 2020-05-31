package br.com.jsa.aluguellegal.repository;

import br.com.jsa.aluguellegal.model.Contato;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends CrudRepository<Contato, Integer> {
}
