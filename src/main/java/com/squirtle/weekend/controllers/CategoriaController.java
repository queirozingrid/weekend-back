package com.squirtle.weekend.controllers;

import com.squirtle.weekend.models.Categoria;
import com.squirtle.weekend.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping("/salvar")
    public ResponseEntity<Categoria> salvar(@RequestBody @Valid Categoria categoria) {

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
    }

    @GetMapping("/todos")
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }
}
