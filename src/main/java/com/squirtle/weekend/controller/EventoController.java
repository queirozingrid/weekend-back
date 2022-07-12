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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evento")
@CrossOrigin(origins = "*")
public class EventoController {
    @Autowired
    private EventoRepository eventoRepository;

    @PutMapping("/editar")
    public Evento editar(@RequestParam("fileupload") List<MultipartFile> files,
                         @RequestParam("posterUp") MultipartFile poster,
                         @Valid Evento evento) throws IOException, FileNotFoundException {

        Evento e2 = eventoRepository.save(evento);
        if(files != null && !files.isEmpty()){
            evento.setFotos(FileSaver.saveEventPics(files,
                    e2.getEstabelecimento().getId(),
                    e2.getEstabelecimento().getNomeFantasia()));
        }
        if(poster!=null){
            evento.setPoster(FileSaver.saveEventPoster(poster, e2.getEstabelecimento().getId(), e2.getEstabelecimento().getNomeFantasia()));
        }

        return eventoRepository.save(evento);
    }

    @PostMapping("/salvar")
    public Evento salvar(@RequestParam("fileupload") List<MultipartFile> files,
                         @RequestParam("posterUp") MultipartFile poster,
                         @Valid Evento evento) throws IOException, FileNotFoundException {

        Evento e2 = eventoRepository.save(evento);
        if(files!=null && !files.isEmpty()){
            evento.setFotos(FileSaver.saveEventPics(files,
                    e2.getEstabelecimento().getId(),
                    e2.getEstabelecimento().getNomeFantasia()));
        }
        if(poster!=null){
            evento.setPoster(FileSaver.saveEventPoster(poster, e2.getEstabelecimento().getId(), e2.getEstabelecimento().getNomeFantasia()));
        }

        return eventoRepository.save(evento);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable(value = "id") Long id) { eventoRepository.deleteById(id); }

    @GetMapping("/todos")
    public List<Evento> listar() {
        List<Evento> eventosCompletos = eventoRepository.findAll();
        List<Evento> eventosResponse = new ArrayList<>();
        for (Evento e: eventosCompletos) {
            e.getEstabelecimento().setSenha(null);
            eventosResponse.add(e);
        }
        return eventosResponse;
    }

    @GetMapping("/{id}")
    public Evento buscar(@PathVariable(value = "id") Long id) {

        Evento eventoCompleto = eventoRepository.findById(id).get();
        Evento eventoResponse = eventoCompleto;
        eventoResponse.getEstabelecimento().setSenha(null);

        return eventoResponse;
    }
}
