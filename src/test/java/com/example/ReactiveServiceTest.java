package com.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class ReactiveServiceTest {

    ReactiveService service = new ReactiveService();

    @Test
    public void nomeFlux() {
        Flux<String> nomes = service.nomes();
    }

}