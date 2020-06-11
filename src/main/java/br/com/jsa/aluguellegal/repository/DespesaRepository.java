package br.com.jsa.aluguellegal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.jsa.aluguellegal.model.Despesa;

@Repository
public interface DespesaRepository extends CrudRepository<Despesa, Integer> {

}
