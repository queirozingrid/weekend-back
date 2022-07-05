package com.squirtle.weekend.controller;

import com.squirtle.weekend.filesManager.FileSaver;
import com.squirtle.weekend.models.Evento;
import com.squirtle.weekend.models.Tag;
import com.squirtle.weekend.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evento")
@CrossOrigin(origins = "*")
public class EventoController {
    @Autowired
    private EventoRepository eventoRepository;

    @PostMapping("/salvar")
    public void salvar(@RequestParam("fileupload") List<MultipartFile> files,
                       @RequestParam("posterUp") MultipartFile poster,
                       @Valid Evento evento) throws IOException, FileNotFoundException {
        evento.setFotos(FileSaver.saveEventPics(files, poster, evento.getId()));
        System.out.println(evento.getFotos());
       // return eventoRepository.save(evento);
    }

    @RequestMapping(value = "/editar", produces = "application/json", method=RequestMethod.PUT)
    public ResponseEntity<Evento> editar(@RequestBody @Valid Evento evento){
        Evento e2 = eventoRepository.save(evento);
        ResponseEntity<Evento> reponse = new ResponseEntity<Evento>(e2, HttpStatus.OK);
        return reponse;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable(value = "id") Long id) { eventoRepository.deleteById(id); }

    @GetMapping("/todos")
    public List<Evento> listar() { return eventoRepository.findAll(); }

    @GetMapping("/{id}")
    public Optional<Evento> buscar(@PathVariable(value = "id") Long id) { return eventoRepository.findById(id); }
}
