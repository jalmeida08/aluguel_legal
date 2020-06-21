package br.com.jsa.aluguellegal.controller;

import br.com.jsa.aluguellegal.exceptions.CpfJaCadastradoException;
import br.com.jsa.aluguellegal.model.Proprietario;
import br.com.jsa.aluguellegal.service.MensageriaService;
import br.com.jsa.aluguellegal.service.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("proprietario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProprietarioController {

	@Autowired
	private ProprietarioService proprietarioService;

	@Autowired
	private MensageriaService mensageriaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Proprietario>> getPessoa(@PathVariable("id") Integer id){
		Optional<Proprietario> prorprietario = proprietarioService.getProprietario(id);
		return ResponseEntity.ok(prorprietario);
	}

	@PostMapping(value="/salvar")
	public ResponseEntity<?> salvar(@RequestBody Proprietario proprietario) {
		proprietarioService.salvar(proprietario);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value="/quero-participar-algueguel-legal")
	public ResponseEntity<?> queroParticiparAlgueguelLegal(@RequestBody Proprietario proprietario) {
		try{
			Proprietario p = proprietarioService.queroParticiparAloguelLegalSalvar(proprietario);
			mensageriaService.enviarEmailNovoProprietario(
					p.getUsuario().getEmail(),
					p.getNome(),
					p.getUsuario().getChaveAtivacao()
			);
			return ResponseEntity.ok().build();
		}catch (CpfJaCadastradoException e){
			ResponseEntity.badRequest().body(e.getMessage());
		}


		return ResponseEntity.ok().build();
	}

	@GetMapping("/listar-proprietarios")
	public ResponseEntity<Iterable<Proprietario>> listaProprietarios() {
		return ResponseEntity.ok(proprietarioService.listarProprietarios());
	}

}
