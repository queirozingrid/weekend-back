package com.squirtle.weekend.controller;

import com.squirtle.weekend.models.Categoria;
import com.squirtle.weekend.models.Tag;
import com.squirtle.weekend.repository.CategoriaRepository;
import com.squirtle.weekend.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping("/todas")
    public List<Categoria> listar(){
        return categoriaRepository.findAll();
    }

    @PostMapping("/salvar")
    public Categoria salvar(@RequestBody @Valid Categoria categoria){
        return categoriaRepository.save(categoria);
    }
}
