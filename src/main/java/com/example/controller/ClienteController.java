package com.example.controller;

import com.example.domain.Cliente;
import com.example.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cliente")
@Slf4j
public class ClienteController {

    private ClienteService service;

    public ClienteController(ClienteService clienteService) {
        this.service = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Cliente> salvar(@RequestBody Cliente cliente) {
        log.info("salvando : {} ", cliente);
        return service.salvar(cliente);
    }

    @GetMapping
    public Flux<Cliente> listar() {
        return service.listar().log();
    }

}