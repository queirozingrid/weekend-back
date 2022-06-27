package com.squirtle.weekend.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.squirtle.weekend.filesManager.FileSaver;
import com.squirtle.weekend.models.Estabelecimento;
import com.squirtle.weekend.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.validation.Valid;
import java.io.File;
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
    public  void salvar(@RequestParam("fileupload") MultipartFile file, @Valid Estabelecimento estabelecimento) {
        String pathLogo = FileSaver.saveLogo(file);
        
        estabelecimento.setLogo(pathLogo);
       // return estabelecimentoRepository.save(estabelecimento);
    }

    // método para testes
    @PostMapping("/salvarImagem")
    public void salvarImagem(@RequestParam("fileupload") MultipartFile file, Estabelecimento estabelecimento){
        System.out.println(estabelecimento.getCnpj());
        System.out.println(estabelecimento.getNomeFantasia());
        System.out.println(estabelecimento.getEndereco().getRua());
        System.out.println(file.getOriginalFilename());
        if(file.getOriginalFilename().contains("png")){
            System.out.println("contéééém");
        }
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
    public ResponseEntity<Estabelecimento> editar (@RequestBody @Valid Estabelecimento estabelecimento){
        Estabelecimento e2 = estabelecimentoRepository.save(estabelecimento);
        return new ResponseEntity<Estabelecimento>(e2, HttpStatus.OK);
    }
}

