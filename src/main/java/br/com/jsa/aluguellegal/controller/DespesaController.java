package br.com.jsa.aluguellegal.controller;

import br.com.jsa.aluguellegal.model.Despesa;
import br.com.jsa.aluguellegal.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Response;

@CrossOrigin
@RestController
@RequestMapping("despesa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @PostMapping("/listar-imoveis-endereco-especificado")
    public ResponseEntity<?>(@RequestBody  Despesa despesa){
        despesaService.salvar(despesa);
        return ResponseEntity.ok().build();
    }

}