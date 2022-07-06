package com.squirtle.weekend.controller;

import com.squirtle.weekend.filesManager.FileSaver;
import com.squirtle.weekend.models.Estabelecimento;
import com.squirtle.weekend.repository.EstabelecimentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estabelecimento")
@CrossOrigin(origins = "*")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;


    // método oficial, favor, não fazer baguncinha aqui rsrs (o método para testes é o /salvarImagem)
    @PostMapping(value = "/salvar")
    public Estabelecimento salvar(@RequestParam("fileupload") MultipartFile file, @Valid Estabelecimento estabelecimento) throws IOException {
        Estabelecimento e2 = estabelecimentoRepository.save(estabelecimento);
        System.out.println(e2.getId());
        if(file != null){
            estabelecimento.setLogo(FileSaver.writeLogo(file, e2.getId(), e2.getNomeFantasia()));
        }
        else {
            estabelecimento.setLogo(null);
        }
       return estabelecimentoRepository.save(estabelecimento);
    }


    @GetMapping("/todos")
    public List<Estabelecimento> listarTodos () {
        return estabelecimentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Estabelecimento> listarPorId (@PathVariable(value = "id") Long id){
        return estabelecimentoRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deletar (@PathVariable(value = "id") Long id){
        estabelecimentoRepository.deleteById(id);
    }
    @PutMapping("/editar")
    public Estabelecimento editar (@RequestParam("fileupload") MultipartFile file, @Valid Estabelecimento estabelecimento) throws IOException {
        Estabelecimento e2 = estabelecimentoRepository.save(estabelecimento);
        System.out.println(e2.getId());
        if(file != null){
            estabelecimento.setLogo(FileSaver.writeLogo(file, e2.getId(), e2.getNomeFantasia()));
        }
        else {
            estabelecimento.setLogo(null);
        }
        return estabelecimentoRepository.save(estabelecimento);
        //return new ResponseEntity<Estabelecimento>(estabelecimento, HttpStatus.OK);
    }
}

