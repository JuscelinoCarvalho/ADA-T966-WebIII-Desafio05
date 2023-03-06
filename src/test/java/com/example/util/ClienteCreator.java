package com.example.util;

import com.example.controller.ClienteController;
import com.example.domain.Cliente;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebFluxTest(ClienteController.class)
public class ClienteCreator {

    //Cliente cliente = new Cliente();

    private static List<String> roles = new ArrayList<>();

    public static Cliente CreateClient(){
        roles.add("Partner");
        roles.add("Employee");
        return Cliente.builder()
                .nome("Joao dos Testes")
                .roles(roles)
                .nasc(Long.valueOf(2001))
                .entrou(LocalDate.parse("2023-03-05"))
                .build();
    }

    public static Cliente UpdateClient(){
        roles.add("Partner");
        roles.add("Employee");

        return Cliente.builder()
                .id("xxxxxxxxxxxxxxxxxxx")
                .nome("Joao dos Testes")
                .roles(roles)
                .nasc(Long.valueOf(2001))
                .entrou(LocalDate.parse("2023-03-05"))
                .build();
    }

    public static List<Cliente> CreateAllClients(){
        roles.add("Partner");
        roles.add("Employee");

        List<Cliente> clienteList = new ArrayList<>();
        Cliente cli1,cli2,cli3;

        cli1 = Cliente.builder()
                .nome("Joao dos Testes")
                .roles(roles)
                .nasc(Long.valueOf(2001))
                .entrou(LocalDate.parse("2023-03-05"))
                .build();
        cli2 = Cliente.builder()
                .nome("Maria dos Testes")
                .roles(roles)
                .nasc(Long.valueOf(1998))
                .entrou(LocalDate.parse("2023-03-05"))
                .build();
        cli3 = Cliente.builder()
                .nome("Amanda dos Sonhos")
                .roles(roles)
                .nasc(Long.valueOf(1977))
                .entrou(LocalDate.parse("2023-03-05"))
                .build();

        clienteList.add(cli1);
        clienteList.add(cli2);
        clienteList.add(cli3);

        return clienteList;

    }



}