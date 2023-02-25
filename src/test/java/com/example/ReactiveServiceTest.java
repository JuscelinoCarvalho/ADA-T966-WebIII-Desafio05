package com.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ReactiveServiceTest {

    ReactiveService service = new ReactiveService();

    @Test
    public void nomeFlux() {
        Flux<String> nomes = service.nomes().log();

        StepVerifier.create(nomes)
                .expectNext("Bob")
                //.expectNext("Bob", "Alice", "Sam", "John")
                //.expectNext("Alice")
                //.expectNext("Sam")
                .expectNextCount(3)
                .verifyComplete();
    }

    // alternativa - precisa refatorar
    public static void main(String[] args) {
        ReactiveService service = new ReactiveService();
        Mono<String> nomeMono = service.nomeMono();
        nomeMono.log().subscribe( nome -> System.out.println(nome ));
    }

}