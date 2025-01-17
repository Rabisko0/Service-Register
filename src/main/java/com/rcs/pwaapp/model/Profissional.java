package com.rcs.pwaapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Email
    private String email;

    @Column(nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL)
    private List<Atendimento> atendimentos = new ArrayList<Atendimento>();

    public Profissional(){

    }

    public Profissional(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void adicionarAtendimento(Atendimento atendimento){
        atendimentos.add(atendimento);
    }

    public void removerAtendimento(Atendimento atendimento){
        atendimentos.removeIf(a -> a.getId() == atendimento.getId());
    }
}
