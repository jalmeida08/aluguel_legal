package br.com.jsa.aluguellegal.service;

import br.com.jsa.aluguellegal.model.Despesa;
import br.com.jsa.aluguellegal.model.Imovel;
import br.com.jsa.aluguellegal.model.Pessoa;
import br.com.jsa.aluguellegal.repository.DespesaRepository;
import br.com.jsa.aluguellegal.repository.ImovelRepository;
import br.com.jsa.aluguellegal.repository.PessoaRepository;
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

    @Autowired
    private PessoaRepository pessoaRepository;

    public void salvar(List<Imovel> listaImovel){
        listaImovel.stream().forEach(a -> this.salvarImovel(a));
    }

    private void salvarImovel(Imovel imovel){
        if(!imovel.getPessoa().isEmpty()){
            imovel.getPessoa().stream().forEach(p -> p = buscarPessoa(p.getId()));
        }

        Imovel imovelSalvo = imovelRepository.save(imovel);

        if(!imovel.getDespesa().isEmpty()){
            imovel.getDespesa()
                    .stream()
                    .forEach(c -> salvarDespesaDoImovel(imovelSalvo, c));
        }
    }

    private Pessoa buscarPessoa(Integer id){
        return this.pessoaRepository.findById(id).get();
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
