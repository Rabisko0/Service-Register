package com.rcs.pwaapp.controller;

import com.rcs.pwaapp.model.Atendimento;
import com.rcs.pwaapp.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atendimentos")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @GetMapping
    public List<Atendimento> listarAtendimentos(){
        return atendimentoService.listarAtendimentos();
    }

    @PostMapping
    public ResponseEntity<Atendimento> adicionarAtendimento(@RequestBody Atendimento atendimento){
        Atendimento atendimentoSalvo = atendimentoService.salvarAtendimento(atendimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(atendimentoSalvo);
    }

    @DeleteMapping("/{id}")
    public void excluirAtendimento(@PathVariable Long id){
        atendimentoService.excluirAtendimento(id);
    }
}
