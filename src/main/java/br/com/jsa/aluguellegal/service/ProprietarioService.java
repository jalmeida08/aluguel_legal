package br.com.jsa.aluguellegal.service;

import br.com.jsa.aluguellegal.Util;
import br.com.jsa.aluguellegal.exceptions.CpfJaCadastradoException;
import br.com.jsa.aluguellegal.model.Pessoa;
import br.com.jsa.aluguellegal.model.Proprietario;
import br.com.jsa.aluguellegal.model.Usuario;
import br.com.jsa.aluguellegal.repository.PessoaRepository;
import br.com.jsa.aluguellegal.repository.ProprietarioRepository;
import br.com.jsa.aluguellegal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProprietarioService {

	@Autowired
	private ProprietarioRepository proprietarioRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public void salvar(Proprietario proprietario) { proprietarioRepository.save(proprietario); }

	public Proprietario queroParticiparAloguelLegalSalvar(Proprietario proprietario) throws CpfJaCadastradoException {
		Optional<Pessoa> pessoa = pessoaRepository.findNumCpf(proprietario.getNumCpf());

		if(pessoa.isPresent()) throw new CpfJaCadastradoException();

		Usuario u = proprietario.getUsuario();
		proprietario.setUsuario(null);
		Proprietario p = this.pessoaRepository.save(proprietario);
		u.setPessoa(p);
		p.setUsuario(salvarUsuarioEProcessarEnvioDeEmail(u));
		return p;
	}

	private Usuario salvarUsuarioEProcessarEnvioDeEmail(Usuario usuario) {
		usuario.setAtivo(false);
		Usuario u = usuarioRepository.save(usuario);
		u.setChaveAtivacao(Util.criptografar(u.getId().toString()));
		return usuarioRepository.save(u);
	}

	public void remover(Integer id) { proprietarioRepository.deleteById(id); }
	public Optional<Proprietario> getProprietario(Integer id) {
		return proprietarioRepository.findById(id);
	}
	public Iterable<Proprietario> listarProprietarios() {
		return proprietarioRepository.findAll();
	}
	
}
