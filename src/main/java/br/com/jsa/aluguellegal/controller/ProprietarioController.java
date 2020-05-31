package br.com.jsa.aluguellegal.controller;

import br.com.jsa.aluguellegal.model.Proprietario;
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

	@GetMapping("/listar-proprietarios")
	public ResponseEntity<Iterable<Proprietario>> listaProprietarios() {
		return ResponseEntity.ok(proprietarioService.listarProprietarios());
	}

}
