package com.example.service;

import com.example.domain.Cliente;
import com.example.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteService {

    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Mono<Cliente> salvar(Cliente cliente) {
        return repository.save(cliente);

    }

    public Flux<Cliente> listar() {
        return repository.findAll();
    }
}