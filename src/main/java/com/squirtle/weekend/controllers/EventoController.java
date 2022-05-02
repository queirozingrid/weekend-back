package com.squirtle.weekend.controllers;

import com.squirtle.weekend.models.Evento;
import com.squirtle.weekend.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    EventoRepository eventoRepository;

    @PostMapping("/salvar")
    public Evento salvar(@RequestBody Evento evento) {
        return eventoRepository.save(evento);
    }

    @GetMapping("/todos")
    public List<Evento> listar() {
        return eventoRepository.findAll();
    }

}
