package br.com.jsa.aluguellegal.repository;

import br.com.jsa.aluguellegal.model.Locatario;
import br.com.jsa.aluguellegal.model.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Integer> { }
