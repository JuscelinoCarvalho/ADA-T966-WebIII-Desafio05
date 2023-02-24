package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class FilmeController {

    @GetMapping("/flux")
    public Flux<Integer> flux() {
        return Flux.just(1,2,3);
    }

}