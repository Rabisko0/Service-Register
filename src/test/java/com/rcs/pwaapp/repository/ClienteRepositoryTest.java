package com.rcs.pwaapp.repository;

import com.rcs.pwaapp.model.Cliente;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testSaveCliente() {
        Cliente cliente = new Cliente("João", "joao@teste.com", "123456789");
        Cliente savedCliente = clienteRepository.save(cliente);

        assertNotNull(savedCliente);
        assertEquals(cliente.getNome(), savedCliente.getNome());
    }

    @Test
    public void testFindClienteById() {
        Cliente cliente = new Cliente("Maria", "maria@teste.com", "123456789");
        Cliente savedCliente = clienteRepository.save(cliente);

        Optional<Cliente> foundCliente = clienteRepository.findById(savedCliente.getId());
        assertTrue(foundCliente.isPresent());
        assertEquals(cliente.getNome(), foundCliente.get().getNome());
    }

    @Test
    public void testFindClienteByIdNaoEncontrado() {
        Optional<Cliente> cliente = clienteRepository.findById(999L);
        assertFalse(cliente.isPresent());
    }
}
