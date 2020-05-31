package br.com.jsa.aluguellegal.controller;

import br.com.jsa.aluguellegal.model.Locatario;
import br.com.jsa.aluguellegal.service.LocatarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("locatario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LocatarioController {

    @Autowired
    private LocatarioService locatarioService;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody  Locatario locatario){
        locatarioService.salvar(locatario);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Locatario>> getLocatario(@PathVariable("id") Integer id){
        Optional<Locatario> locatario = locatarioService.getLocatario(id);
        return ResponseEntity.ok(locatario);
    }

    @GetMapping("/listar-proprietarios")
    public ResponseEntity<Iterable<Locatario>> listarLocatarios(){
        Iterable<Locatario> locatarios = locatarioService.listaProprietarios();
        return ResponseEntity.ok(locatarios);
    }
    @GetMapping("/lista-possiveis-locatarios")
    public ResponseEntity<List<Locatario>> listaPossiveisLocatarios(){
        return ResponseEntity.ok(this.locatarioService.listaPossiveisLocatarios());
    }
}
