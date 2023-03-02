package com.example.service;

import com.example.domain.Cliente;
import com.example.exception.UnsuportActionException;
import com.example.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Mono<Cliente> salvar(Cliente cliente) {
        return repository.save(cliente);
    }

    public Flux<Cliente> listar() {
        return repository.findAll();
    }

    // transacoes compensatorias -> try -> confirm/cancel
    public Mono<Cliente> atualizar(Cliente cliente, String id) {
        return repository.findById(id)
            .flatMap( atual -> {
               atual.setNome( cliente.getNome() );
               atual.setEntrou( cliente.getEntrou() );
               atual.setNasc( cliente.getNasc() );
               atual.setRoles( cliente.getRoles() );
               return repository.save(atual);
            });
    }

    // TODO
    public Mono<Cliente> atualizar(Cliente cliente, Cliente atual) {
            atual.setNome(cliente.getNome());
            atual.setEntrou(cliente.getEntrou());
            atual.setNasc(cliente.getNasc());
            atual.setRoles(cliente.getRoles());
            return repository.save(atual);
    }

    public Mono<Void> remover(String id) {
        return repository.deleteById(id);
    }


    public Flux<Cliente> salvarTodos(List<Cliente> clienteList){
        return repository.saveAll(clienteList) ;
    }

}