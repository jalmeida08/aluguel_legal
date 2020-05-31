package br.com.jsa.aluguellegal.service;

import br.com.jsa.aluguellegal.model.Despesa;
import br.com.jsa.aluguellegal.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public void salvar(Despesa despesa){
        despesaRepository.save(despesa);
    }

}
