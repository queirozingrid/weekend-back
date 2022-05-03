package com.squirtle.weekend.controller;


import com.squirtle.weekend.models.Endereco;
import com.squirtle.weekend.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    EnderecoRepository enderecoRepository;

    @GetMapping("/todas")
    public List<Endereco> listar(){
        return enderecoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Endereco> buscar(@PathVariable(value = "id") Long id) { return enderecoRepository.findById(id); }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable(value = "id") Long id) { enderecoRepository.deleteById(id);}

    @PostMapping("/salvar")
    public Endereco salvar(@RequestBody @Valid Endereco endereco){
        return enderecoRepository.save(endereco);
    }

}
