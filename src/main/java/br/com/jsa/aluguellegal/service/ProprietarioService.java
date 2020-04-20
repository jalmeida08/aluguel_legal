package br.com.jsa.aluguellegal.service;

import br.com.jsa.aluguellegal.model.Proprietario;
import br.com.jsa.aluguellegal.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProprietarioService {

	@Autowired
	private ProprietarioRepository proprietarioRepository;

	public void salvar(Proprietario proprietario) { proprietarioRepository.save(proprietario); }
	public void remover(Integer id) { proprietarioRepository.deleteById(id); }
	public Optional<Proprietario> getProprietario(Integer id) {
		return proprietarioRepository.findById(id);
	}
	public Iterable<Proprietario> listarProprietarios() {
		return proprietarioRepository.findAll();
	}
	
}
