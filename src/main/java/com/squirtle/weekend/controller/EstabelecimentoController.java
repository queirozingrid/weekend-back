package com.squirtle.weekend.controller;

import com.squirtle.weekend.models.Estabelecimento;
import com.squirtle.weekend.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("estabelecimento")
public class EstabelecimentoController {

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @PostMapping("salvar")
    public Estabelecimento salvar(@RequestBody Estabelecimento estabelecimento){
        return estabelecimentoRepository.save(estabelecimento);
    }

    @GetMapping("todos")
    public List<Estabelecimento> listarTodos(){
        return estabelecimentoRepository.findAll();
    }
}
