package br.com.jsa.aluguellegal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.jsa.aluguellegal.model.Imovel;

@Repository
public interface ImovelRepository  extends CrudRepository<Imovel, Integer> {

    @Query(value = "SELECT * FROM IMOVEL I GROUP BY I.ENDERECO", nativeQuery = true)
    public List<Imovel> buscarListaEnderecosAgrupados();

    public List<Imovel> findByEndereco(String endereco);

    public List<Imovel> findBy();


}
