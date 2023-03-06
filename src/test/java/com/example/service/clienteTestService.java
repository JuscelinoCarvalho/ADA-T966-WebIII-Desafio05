package com.example.service;

import com.example.domain.Cliente;
import com.example.exception.UserNotFoundException;
import com.example.repository.ClienteRepository;
import com.example.util.ClienteCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
public class clienteTestService {
    @InjectMocks
    private ClienteService cliService;

    @Mock
    private ClienteRepository cliRepository;

    private final Cliente cli = ClienteCreator.CreateClient();

    @BeforeEach
    public void setUp(){
        BDDMockito.when(cliRepository.findAll())
                .thenReturn(Flux.just(cli));

        BDDMockito.when(cliRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(cli));
    }

    @Test
    @DisplayName("FindAll returns a Flux of Cliente")
    public void findReturnFluxOfClienteWhenSuccess(){
        StepVerifier.create(cliService.listar())
                .expectSubscription()
                .expectNext(cli)
                .verifyComplete();
    }

    @Test
    @DisplayName("FindById returns a Mono Cliente when Sucessfull!")
    public void findByIdReturnMonoClienteWhenSuccess() throws UserNotFoundException {
        StepVerifier.create(cliService.getById("64046e7a2cbda476e88cab8e"))
                .expectSubscription()
                .expectNext(cli)
                .verifyComplete();
    }

    //Ambos os métodos abaixo é para garantir que nosso código não está
    //BLOQUEADO por alguma outra THREAD, pois estamos trabalhando de forma
    //REATIVA.
    /*
    @BeforeAll
    public static void blockHoundSetup() {
        BlockHound.install();
    }
    @Test
    public void blockHoundWorks(){
        try{
            FutureTask<?> task = new FutureTask<>(() -> {
                Thread.sleep(0);
                return "";
            });
            Schedulers.parallel().schedule(task);
        }catch (Exception e){
            Assertions.assertTrue(e.getCause() instanceof BlockingOperationException);
        }*/
    }
