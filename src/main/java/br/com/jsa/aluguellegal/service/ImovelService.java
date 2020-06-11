package br.com.jsa.aluguellegal.service;

import br.com.jsa.aluguellegal.model.Despesa;
import br.com.jsa.aluguellegal.model.Imovel;
import br.com.jsa.aluguellegal.repository.DespesaRepository;
import br.com.jsa.aluguellegal.repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImovelService {
    @Autowired
    private ImovelRepository imovelRepository;

    @Autowired
    private DespesaRepository despesaRepository;

    public void salvar(Imovel imovel){
        Imovel imovelSalvo = imovelRepository.save(imovel);
        if(!imovel.getDespesa().isEmpty()){
            imovel.getDespesa()
                    .stream()
                    .forEach(c -> salvarDespesaDoImovel(imovelSalvo, c));
        }
    }

    private void salvarDespesaDoImovel(Imovel imovelSalvo, Despesa despesa){
        List<Imovel>listaImovel = new ArrayList<Imovel>();
        listaImovel.add(imovelSalvo);
        despesa.setImovel(listaImovel);
        despesaRepository.save(despesa);
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
