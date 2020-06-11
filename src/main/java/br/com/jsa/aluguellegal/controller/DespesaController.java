package br.com.jsa.aluguellegal.controller;


import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jsa.aluguellegal.model.Despesa;
import br.com.jsa.aluguellegal.service.DespesaService;

@CrossOrigin
@RestController
@RequestMapping("despesa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @PostMapping("/salvar-despesa")
    public ResponseEntity<?> salvar (@RequestBody Despesa despesa){
        despesaService.salvar(despesa);
        return ResponseEntity.ok().build();
    }
}