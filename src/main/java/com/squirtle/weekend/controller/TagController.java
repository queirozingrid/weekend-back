package com.squirtle.weekend.controller;

import com.squirtle.weekend.models.Tag;
import com.squirtle.weekend.models.Tag;
import com.squirtle.weekend.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    TagRepository tagRepository;

    @GetMapping("/todos")
    public List<Tag> listar(){
        return tagRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Tag> buscar(@PathVariable(value = "id") Long id) { return tagRepository.findById(id); }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable(value = "id") Long id) { tagRepository.deleteById(id);}

    @PostMapping("/salvar")
    public Tag salvar(@RequestBody @Valid Tag tag){
        return tagRepository.save(tag);
    }

    @PutMapping("/editar")
    public ResponseEntity<Tag> editar(@RequestBody @Valid Tag tag){
        Tag t2 = tagRepository.save(tag);
        return new ResponseEntity<Tag>(t2, HttpStatus.OK);
    }


}
