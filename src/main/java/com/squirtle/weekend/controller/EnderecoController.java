package com.squirtle.weekend.controller;


import com.squirtle.weekend.models.Endereco;
import com.squirtle.weekend.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping("/todos")
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

    @GetMapping("/buscarPorCep/{cep}")
    public Optional<Endereco> listarPorCep(@PathVariable(value = "cep") String cep) { return enderecoRepository.findByCep(cep); }

    @PutMapping("/editar")
    public ResponseEntity<Endereco> editar(@RequestBody @Valid Endereco endereco){
        Endereco e2 = enderecoRepository.save(endereco);
        return new ResponseEntity<Endereco>(e2, HttpStatus.OK);
    }

}
