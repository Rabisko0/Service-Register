package com.rcs.pwaapp.controller;

import com.rcs.pwaapp.model.Procedimento;
import com.rcs.pwaapp.service.ProcedimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/procedimentos")
public class ProcedimentoController {

    @Autowired
    private ProcedimentoService procedimentoService;

    @GetMapping
    public List<Procedimento> listarProcedimentos(){
        return procedimentoService.listarProcedimentos();
    }

    @PostMapping
    public Procedimento adicionarProcedimento(@RequestBody Procedimento procedimento){
        return procedimentoService.criarProcedimento(procedimento);
    }

    @PutMapping
    public Procedimento atualizarProcedimento(@PathVariable Long id, @RequestBody Procedimento procedimento){
        return procedimentoService.atualizarProcedimento(id, procedimento);
    }

    @DeleteMapping("/{id}")
    public void excluirProcedimento(@PathVariable Long id){
        procedimentoService.excluirProcedimento(id);
    }
}
