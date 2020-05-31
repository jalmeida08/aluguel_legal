package br.com.jsa.aluguellegal.service;

import br.com.jsa.aluguellegal.model.Contato;
import br.com.jsa.aluguellegal.model.Locatario;
import br.com.jsa.aluguellegal.repository.ContatoRepository;
import br.com.jsa.aluguellegal.repository.LocatarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocatarioService {

    @Autowired
    private LocatarioRepository locatarioRepository;
    @Autowired
    private ContatoRepository contatoRepository;

    public void salvar(Locatario locatario){
        Locatario locatarioSalvo = this.locatarioRepository.save(locatario);
        if(!locatario.getContato().isEmpty()){
            locatario.getContato()
                    .stream()
                    .forEach( c -> salvarContato(c, locatarioSalvo));
        }
    }

    private void salvarContato(Contato contato, Locatario locatarioSalvo){
        contato.setPessoa(locatarioSalvo);
        this.contatoRepository.save(contato);
    }
    public List<Locatario> listaPossiveisLocatarios(){
        return this.locatarioRepository.findByAtivo(false);
    }
    public Optional<Locatario> getLocatario(Integer id) {
        return this.locatarioRepository.findById(id);
    }

    public Iterable<Locatario> listaProprietarios(){
        return this.locatarioRepository.findAll();
    }

}
