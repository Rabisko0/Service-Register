package com.rcs.pwaapp.service;

import com.rcs.pwaapp.model.Procedimento;
import com.rcs.pwaapp.repository.ProcedimentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcedimentoService {

    @Autowired
    private ProcedimentoRepository procedimentoRepository;

    public List<Procedimento> listarProcedimentos(){
        return procedimentoRepository.findAll();
    }

    public Procedimento buscarProcedimentoPorId(Long id){
        return procedimentoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Procedimento criarProcedimento(Procedimento procedimento){
        return procedimentoRepository.save(procedimento);
    }

    @Transactional
    public Procedimento atualizarProcedimento(Long id, Procedimento procedimentoAtualizado){
        Procedimento procedimentoExistente = procedimentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Procedimento não encontrado."));

        procedimentoExistente.setNome(procedimentoAtualizado.getNome());
        procedimentoExistente.setValor(procedimentoAtualizado.getValor());

        return procedimentoRepository.save(procedimentoExistente);
    }

    public void excluirProcedimento(Long id){
        procedimentoRepository.deleteById(id);
    }
}
