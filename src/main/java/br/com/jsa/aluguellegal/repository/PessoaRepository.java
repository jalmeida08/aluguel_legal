package br.com.jsa.aluguellegal.repository;

import br.com.jsa.aluguellegal.model.Locatario;
import br.com.jsa.aluguellegal.model.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {

    public Optional<Pessoa> findNumCpf(Long numCpf);
}
