package com.squirtle.weekend.controller;

import com.squirtle.weekend.models.Evento;
import com.squirtle.weekend.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evento")
@CrossOrigin(origins = "*")
public class EventoController {
    @Autowired
    private EventoRepository eventoRepository;

    @PostMapping("/salvar")
    public Evento salvar(@RequestBody @Valid Evento evento) { return eventoRepository.save(evento); }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable(value = "id") Long id) { eventoRepository.deleteById(id); }

    @GetMapping("/todos")
    public List<Evento> listar() { return eventoRepository.findAll(); }

    @GetMapping("/{id}")
    public Optional<Evento> buscar(@PathVariable(value = "id") Long id) { return eventoRepository.findById(id); }
}
