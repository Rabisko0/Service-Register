package com.rcs.pwaapp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.rcs.pwaapp.model.Atendimento;
import com.rcs.pwaapp.model.Cliente;
import com.rcs.pwaapp.model.Profissional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import com.rcs.pwaapp.service.AtendimentoService;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@WebMvcTest(AtendimentoController.class)
@AutoConfigureMockMvc
@WithMockUser
public class AtendimentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AtendimentoService atendimentoService;

    @Test
    public void testCriarAtendimento() throws Exception {
        // Criando as instâncias necessárias
        Cliente cliente = new Cliente("Cliente Teste", "121213", "121212121212");
        Profissional profissional = new Profissional("Profissional Teste", "aaaa@1aaa", "111111111");

        // Usando OffsetDateTime para definir a data esperada com fuso horário (-03:00)
        OffsetDateTime dataEsperada = OffsetDateTime.parse("2025-01-09T15:30:00-03:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        String descricao = "procedimento qualquer";

        // Criando o objeto Atendimento simulado
        Atendimento atendimento = new Atendimento(dataEsperada.toLocalDateTime(), descricao, cliente, profissional);

        // Mocking the service call
        when(atendimentoService.salvarAtendimento(any(Atendimento.class))).thenReturn(atendimento);

        // Perform the mock request
        mockMvc.perform(post("/api/atendimentos")
                        .contentType("application/json")
                        .content("{ \"data\": \"2025-01-09T15:30:00-03:00\", \"descricao\": \"" + descricao + "\", \"cliente\": { \"nome\": \"Cliente Teste\", \"documento\": \"121213\", \"cpf\": \"121212121212\" }, \"profissional\": { \"nome\": \"Profissional Teste\", \"email\": \"aaaa@1aaa\", \"telefone\": \"111111111\" } }")
                        .with(csrf())  // Adiciona o token CSRF
                        .with(httpBasic("user", "password")))  // Adiciona as credenciais de autenticação
                .andExpect(status().isCreated())  // Espera o status 201 Created
                .andExpect(jsonPath("$.dataAtendimento").value("2025-01-09T15:30:00Z"))  // Verifica a data na resposta
                .andExpect(jsonPath("$.descricao").value(descricao))  // Verifica a descrição na resposta
                .andExpect(jsonPath("$.cliente.nome").value("Cliente Teste"))  // Verifica o nome do cliente
                .andExpect(jsonPath("$.profissional.nome").value("Profissional Teste"));  // Verifica o nome do profissional
    }

}
