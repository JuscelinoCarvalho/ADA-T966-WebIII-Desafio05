package com.example.controller;

import com.example.domain.Cliente;
import com.example.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@RestController
@RequestMapping("/clientes")
@Slf4j
public class ClienteController {

    private ClienteService service;

    public ClienteController(ClienteService clienteService) {
        this.service = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Cliente> salvar(@RequestBody Cliente cliente) {
        return service.salvar(cliente);
    }

    @GetMapping
    public Flux<Cliente> listar() {
        return service.listar();
    }

    @PutMapping("/{id}")
    public Mono<Cliente> atualizar(@RequestBody Cliente cliente, @PathVariable String id) {
        Mono<Cliente> atualizar = service.atualizar(cliente, id);
        return atualizar;
    }

    @DeleteMapping("/{id}")
    public Mono<Void> remover(@PathVariable String id) {
        return service.remover(id);
    }


}