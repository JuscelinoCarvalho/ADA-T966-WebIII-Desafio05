package com.example;

import reactor.core.publisher.Flux;

import java.util.List;

public class ReactiveService {

    public Flux<String> nomes() {
        var nomes =  List.of("Bob", "Alice", "Sam", "John");
        var nomesFlux = Flux.fromIterable(nomes);
        return nomesFlux;
    }

}