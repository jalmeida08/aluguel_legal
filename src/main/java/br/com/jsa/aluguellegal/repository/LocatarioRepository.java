package br.com.jsa.aluguellegal.repository;

import br.com.jsa.aluguellegal.model.Locatario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocatarioRepository extends CrudRepository<Locatario, Integer> {

    public List<Locatario> findByAtivo(boolean tipoStatusLocatario);
}
