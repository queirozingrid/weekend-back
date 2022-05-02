package com.squirtle.weekend.controllers;

import com.squirtle.weekend.models.Tag;
import com.squirtle.weekend.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagRepository tagRepository;

    @PostMapping("/salvar")
    public ResponseEntity<Tag> salvar(@RequestBody @Valid Tag tag) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tagRepository.save(tag));
    }

    @GetMapping("/todos")
    public List<Tag> listar() {
        return tagRepository.findAll();
    }
}
