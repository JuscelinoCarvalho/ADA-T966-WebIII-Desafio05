package com.example.service;

import com.example.domain.Cliente;
import com.example.dto.UserNotFoundExceptionDTO;
import com.example.exception.UserNotFoundException;
import com.example.repository.ClienteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Log4j2
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Mono<Cliente> salvar(Cliente cliente) throws MethodArgumentNotValidException {
        /*
        var param = new MethodParameter(this.getClass().getDeclaredMethod("salvar", Cliente.class), 0);
        BindingResult bindResult = null;
        bindResult.addError(new FieldError(this.getClass().getName(), "", "Campos Inválidos"));
        */
        return repository.save(cliente).onErrorResume(err -> Mono.error(new Exception()));
    }

    public Flux<Cliente> listar() {
        return repository.findAll();
    }

    // transacoes compensatorias -> try -> confirm/cancel
    public Mono<Cliente> atualizar(Cliente cliente, String id) throws UserNotFoundException {

        Mono<Cliente> cli = repository.findById(id);
        UserNotFoundExceptionDTO erroDTO = new UserNotFoundExceptionDTO(HttpStatus.BAD_REQUEST.value(), "Não existe usuário com o ID = " + id);

        return cli.flatMap( atual -> {
                    atual.setNome( cliente.getNome() );
                    atual.setEntrou( cliente.getEntrou() );
                    atual.setNasc( cliente.getNasc() );
                    atual.setRoles( cliente.getRoles() );
                    return repository.save(atual);
                })
                .switchIfEmpty(Mono.error(new UserNotFoundException(erroDTO)));
    }

    public Mono<Cliente> getById(String id) throws UserNotFoundException {
        Mono<Cliente> cli = repository.findById(id);
        //Mono<Boolean> monoTest = Mono.just(true);
        //cli.toString().equals("MonoUsingWhen")
        /*
        Passando o ID (string) correto do usuário, ele retornará o mesmo.
        http://localhost:8080/clientes/get/6404c00222e077514eb35c9a

        Passando o ID (string) ERRADO!!! do usuário, ele irá estourar UserNotFoundException.
        http://localhost:8080/clientes/get/6404c00222e077514eb35c9axxxxxxx
        * */
        UserNotFoundExceptionDTO userError = new UserNotFoundExceptionDTO(HttpStatus.BAD_REQUEST.value(), "Não existe usuário com ID = " + id );
        return cli.switchIfEmpty(Mono.error(new UserNotFoundException(userError)));
    }

    public Mono<Void> remover(String id)  {
        return repository.deleteById(id);
    }


    public Flux<Cliente> salvarTodos(List<Cliente> clienteList){
        return repository.saveAll(clienteList);
    }

}