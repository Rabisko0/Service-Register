package com.rcs.pwaapp.service;

import com.rcs.pwaapp.model.Atendimento;
import com.rcs.pwaapp.repository.AtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    public List<Atendimento> listarAtendimentos(){
        return atendimentoRepository.findAll();
    }

    public Atendimento buscarAtendimentoPorId(Long id){
        return atendimentoRepository.findById(id).orElse(null);
    }

    public Atendimento salvarAtendimento(Atendimento atendimento){
        return atendimentoRepository.save(atendimento);
    }

    public void excluirAtendimento(Long id){
        atendimentoRepository.deleteById(id);
    }
}
