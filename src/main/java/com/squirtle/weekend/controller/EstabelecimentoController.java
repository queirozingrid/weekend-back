package com.squirtle.weekend.controller;

import com.squirtle.weekend.models.Estabelecimento;
import com.squirtle.weekend.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estabelecimento")
public class EstabelecimentoController {

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @PostMapping("/salvar")
    public Estabelecimento salvar(@RequestBody Estabelecimento estabelecimento){
        return estabelecimentoRepository.save(estabelecimento);
    }

    @GetMapping("/todos")
    public List<Estabelecimento> listarTodos(){
        return estabelecimentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Estabelecimento> listarPorId(@PathVariable(value = "id") Long id) { return estabelecimentoRepository.findById(id); }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable(value = "id") Long id) { estabelecimentoRepository.deleteById(id); }
}
