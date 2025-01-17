package com.rcs.pwaapp.service;

import com.rcs.pwaapp.model.Profissional;
import com.rcs.pwaapp.repository.ProfissionalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public List<Profissional> listarProfissionais(){
        return profissionalRepository.findAll();
    }

    public Profissional buscarProfissionalPorId(Long id){
        return profissionalRepository.findById(id).orElse(null);
    }

    @Transactional
    public Profissional criarProfissional(Profissional profissional){
        return profissionalRepository.save(profissional);
    }

    @Transactional
    public Profissional atualizarProfissional(Long id, Profissional profissionalAtualizado){
        Profissional profissionalExistente = profissionalRepository.findById(id).orElseThrow(() -> new RuntimeException("Profissional não encontrado."));

        profissionalExistente.setNome(profissionalAtualizado.getNome());
        profissionalExistente.setEmail(profissionalAtualizado.getEmail());
        profissionalExistente.setTelefone(profissionalAtualizado.getTelefone());

        return profissionalRepository.save(profissionalExistente);
    }

    public void excluirProfissional(Long id){
        profissionalRepository.deleteById(id);
    }
}
