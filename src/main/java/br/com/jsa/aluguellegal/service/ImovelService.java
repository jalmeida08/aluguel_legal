package br.com.jsa.aluguellegal.service;

import br.com.jsa.aluguellegal.model.Imovel;
import br.com.jsa.aluguellegal.repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImovelService {
    @Autowired
    private ImovelRepository imovelRepository;

    public void salvar(Imovel imovel){
        imovelRepository.save(imovel);
    }

    public Optional<Imovel> getImovel(Integer id){
        return imovelRepository.findById(id);
    }

    public Iterable<Imovel> buscarTodosImoveis(){
        return imovelRepository.findAll();
    }

    public List<Imovel> buscarListaEnderecosAgrupados(){
        return imovelRepository.buscarListaEnderecosAgrupados();
    }

    public List<Imovel> buscarListaEnderecoEspecificado(String endereco){
        return this.imovelRepository.findByEndereco(endereco);
    }

    public void removerImovel(Integer id){
        imovelRepository.deleteById(id);
    }
}
