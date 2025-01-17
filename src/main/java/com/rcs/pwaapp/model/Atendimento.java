package com.rcs.pwaapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dataAtendimento;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Procedimento> procedimentos = new ArrayList<Procedimento>();


    public Atendimento(){
    }

    public Atendimento(LocalDateTime dataAtendimento, String descricao, Cliente cliente, Profissional profissional){
        this.dataAtendimento = dataAtendimento;
        this.descricao = descricao;
        this.cliente = cliente;
        this.profissional = profissional;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDateTime dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public void adiconarProcedimento(Procedimento procedimento){
        procedimentos.add(procedimento);
    }

    public void removerProcedimento(Procedimento procedimento){
        procedimentos.removeIf(a -> a.getId() == procedimento.getId());
    }
}
