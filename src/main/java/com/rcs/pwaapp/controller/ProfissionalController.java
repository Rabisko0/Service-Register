package com.rcs.pwaapp.controller;

import com.rcs.pwaapp.model.Profissional;
import com.rcs.pwaapp.service.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    @GetMapping
    public List<Profissional> listarProfissionais(){
        return profissionalService.listarProfissionais();
    }

    @PostMapping
    public Profissional adicionarProfissional(@RequestBody Profissional profissional){
        return profissionalService.criarProfissional(profissional);
    }

    @DeleteMapping("/{id}")
    public void excluirProfissional(@PathVariable Long id){
        profissionalService.excluirProfissional(id);
    }
}
