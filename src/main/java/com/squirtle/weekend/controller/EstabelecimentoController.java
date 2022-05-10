package com.squirtle.weekend.controller;

import com.squirtle.weekend.models.Estabelecimento;
import com.squirtle.weekend.models.Estabelecimento;
import com.squirtle.weekend.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("estabelecimento")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @PostMapping("/salvar")
    public Estabelecimento salvar(@RequestBody @Valid Estabelecimento estabelecimento){
        return estabelecimentoRepository.save(estabelecimento);
    }

    @GetMapping("/todos")
    public List<Estabelecimento> listarTodos(){
        return estabelecimentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Estabelecimento> buscar(@PathVariable ("id") Long id){return estabelecimentoRepository.findById(id); }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable ("id") Long id){estabelecimentoRepository.deleteById(id);}

    @PutMapping("/editar")
    public ResponseEntity<Estabelecimento> editar(@RequestBody @Valid Estabelecimento estabelecimento){
        Estabelecimento e2 = estabelecimentoRepository.save(estabelecimento);
        return new ResponseEntity<Estabelecimento>(e2, HttpStatus.OK);
    }
}
