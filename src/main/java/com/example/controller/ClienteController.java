package com.example.controller;

import com.example.domain.Cliente;
import com.example.exception.UserNotFoundException;
import com.example.service.ClienteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.List;


@RestController
@RequestMapping("/clientes")
@Slf4j
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService clienteService) {
        this.service = clienteService;
    }

    Sinks.Many<Cliente> sinks = Sinks.many().replay().latest();

    @PostMapping(path="/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<Cliente>> salvar(@RequestBody @Valid Cliente cliente) throws MethodArgumentNotValidException {
        Mono<Cliente> cliResponse = service.salvar(cliente)
                .doOnNext(salvo -> sinks.tryEmitNext(salvo));

        return cliResponse
                .map(cli -> ResponseEntity
                            .ok()
                            .body(cli))
                            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                .onErrorResume(err -> Mono.error(new Exception()));
    }

    @PostMapping(path="/saveAll")
    public ResponseEntity<Flux<Cliente>> saveList(@Valid @RequestBody List<Cliente> clienteList){
        Flux<Cliente> srv = service.salvarTodos(clienteList);
        return ResponseEntity.ok().body(srv);
        //Professor não consegui retornar um Mono do ResponseEntity da lista Flux.
        //Estou em dúvida se essa forma de retorno nao sendo Mono estaria errada pois o
        //Mono é Subscriber.
    }

    @GetMapping(path = "/")
    public Flux<Cliente> listar() {
        //throw new NullPointerException("Nulo!!!");
        return service.listar();
    }

    @GetMapping(path = "/get/{id}")
    public Mono<ResponseEntity<Cliente>> getById(@PathVariable String id) throws UserNotFoundException {
        log.info("Chamou com o ID = " + id);
         Mono<Cliente> cli = service.getById(id);
        return cli.map( cliResp -> ResponseEntity.ok().body(cliResp))
                .switchIfEmpty(Mono.error(new Exception()));
    }


    @PutMapping("/{id}")
    public Mono<ResponseEntity<Cliente>> atualizar(@RequestBody @Valid Cliente cliente, @PathVariable String id) throws UserNotFoundException {
        Mono<Cliente> atualizar = service.atualizar(cliente, id);
        return atualizar
            .map(atual -> ResponseEntity.ok().body(atual))
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> remover(@PathVariable String id) {
        return service.remover(id);
    }

    @GetMapping("/stream")
    public Flux<Cliente> stream() {
        return sinks.asFlux();
    }

}