package com.squirtle.weekend.controller;

import com.squirtle.weekend.models.Categoria;
import com.squirtle.weekend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping("/todos")
    public List<Categoria> listar(){
        return categoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Categoria> buscar(@PathVariable(value = "id") Long id) { return categoriaRepository.findById(id); }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable(value = "id") Long id) { categoriaRepository.deleteById(id);}

    @PostMapping("/salvar")
    public Categoria salvar(@RequestBody @Valid Categoria categoria){
        return categoriaRepository.save(categoria);
    }
}
