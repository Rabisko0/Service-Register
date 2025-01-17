package com.rcs.pwaapp.service;

import com.rcs.pwaapp.model.Atendimento;
import com.rcs.pwaapp.model.Cliente;
import com.rcs.pwaapp.model.Procedimento;
import com.rcs.pwaapp.model.Profissional;
import com.rcs.pwaapp.repository.AtendimentoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.core.IsInstanceOf.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AtendimentoServiceTest {

    @Mock
    private AtendimentoRepository atendimentoRepository;

    @InjectMocks
    private AtendimentoService atendimentoService;

    @Test
    public void testCriarAtendimento() {
        Cliente cliente = new Cliente("Cliente Teste", "121213", "121212121212");
        Profissional profissional = new Profissional("Profissional Teste", "aaaa@1aaa", "111111111");
        Procedimento procedimento = new Procedimento("Procedimento Teste", 200.00);
        LocalDateTime data = LocalDateTime.now();
        String descricao = "procedimento qualquer";

        Atendimento atendimento = new Atendimento(data, descricao, cliente, profissional);

        // Simulando o retorno do Repository
        when(atendimentoRepository.save(ArgumentMatchers.any(Atendimento.class))).thenReturn(atendimento);

        Atendimento resultado = atendimentoService.salvarAtendimento(atendimento);

        // Verifique se o atendimento foi salvo corretamente
        assertNotNull(resultado);
        assertEquals(cliente.getNome(), resultado.getCliente().getNome());
    }

    @Test
    public void testBuscarAtendimentoPorId() {
        Atendimento atendimento = new Atendimento();
        atendimento.setId(1L);

        // Simulando o retorno do Repository
        when(atendimentoRepository.findById(1L)).thenReturn(Optional.of(atendimento));

        Atendimento resultado = atendimentoService.buscarAtendimentoPorId(1L);

        // Verifique se o atendimento foi encontrado
        assertNotNull(resultado);
        assertEquals(Long.valueOf(1L), resultado.getId());
    }

}
