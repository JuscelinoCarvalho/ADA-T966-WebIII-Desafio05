package com.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ReactiveServiceTest {

    ReactiveService service = new ReactiveService();

    @Test
    public void nomeFlux() {
        Flux<String> nomes = service.nomes();

        StepVerifier.create(nomes)
                .expectNext("Bob")
                //.expectNext("Bob", "Alice", "Sam", "John")
                //.expectNext("Alice")
                //.expectNext("Sam")
                .expectNextCount(3)
                .verifyComplete();
    }

}