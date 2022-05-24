package com.squirtle.weekend.controller;

import com.squirtle.weekend.models.Categoria;
import com.squirtle.weekend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

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

    @PutMapping("/editar")
    public ResponseEntity<Categoria> editar(@RequestBody @Valid Categoria categoria){
        Categoria c2 = categoriaRepository.save(categoria);
        return new ResponseEntity<Categoria>(c2, HttpStatus.OK);
    }
}
