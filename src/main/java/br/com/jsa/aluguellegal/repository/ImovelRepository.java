package br.com.jsa.aluguellegal.repository;

import br.com.jsa.aluguellegal.model.Imovel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImovelRepository  extends CrudRepository<Imovel, Integer> {

    @Query(value = "SELECT * FROM IMOVEL I GROUP BY I.ENDERECO", nativeQuery = true)
    public List<Imovel> buscarListaEnderecosAgrupados();

    public List<Imovel> findByEndereco(String endereco);

    public List<Imovel> findBy();


}
