package br.com.jsa.aluguellegal.controller;

import br.com.jsa.aluguellegal.model.Imovel;
import br.com.jsa.aluguellegal.service.ImovelService;
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
@RequestMapping("imovel")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ImovelController {

    @Autowired
    private ImovelService imovelService;

    @PostMapping("/salvar")
    public void salvar(@RequestBody Imovel imovel){
        imovelService.salvar(imovel);
    }

    @GetMapping("/listar-imoveis")
    public Iterable<Imovel> listarTodosImovel(){
        return imovelService.buscarTodosImoveis();
    }

    @GetMapping("/{id}")
    public Optional<Imovel> getImoveis (@PathVariable("id")  Integer id){
        return imovelService.getImovel(id);
    }

    @GetMapping("/buscar-enderecos-agrupados")
    public ResponseEntity<List<Imovel>> buscarListaEnderecosAgrupados(){
        List<Imovel> imoveis = imovelService.buscarListaEnderecosAgrupados();
        return ResponseEntity.ok(imoveis);
    }

    @PostMapping("/listar-imoveis-endereco-especificado")
    public ResponseEntity<List<Imovel>> buscarListaEnderecoEspecificado(@RequestBody String endereco){
        List<Imovel> imoveis = imovelService.buscarListaEnderecoEspecificado(endereco);
        return ResponseEntity.ok(imoveis);
    }

    @DeleteMapping("/deletar-imovel/{id}")
    public ResponseEntity<?> deletarImovel (@PathVariable("id")  Integer id){
        imovelService.removerImovel(id);
        return ResponseEntity.ok().build();
    }

}
