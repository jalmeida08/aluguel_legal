package br.com.jsa.aluguellegal.repository;

import br.com.jsa.aluguellegal.model.Despesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends CrudRepository<Despesa, Integer> {

}
