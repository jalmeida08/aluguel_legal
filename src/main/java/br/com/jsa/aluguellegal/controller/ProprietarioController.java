package br.com.jsa.aluguellegal.controller;

import br.com.jsa.aluguellegal.exceptions.CpfJaCadastradoException;
import br.com.jsa.aluguellegal.exceptions.EmailJaCadastradoException;
import br.com.jsa.aluguellegal.model.Proprietario;
import br.com.jsa.aluguellegal.service.MensageriaService;
import br.com.jsa.aluguellegal.service.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("proprietario")
@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
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
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (EmailJaCadastradoException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/listar-proprietarios")
	public ResponseEntity<Iterable<Proprietario>> listaProprietarios() {
		return ResponseEntity.ok(proprietarioService.listarProprietarios());
	}

}
