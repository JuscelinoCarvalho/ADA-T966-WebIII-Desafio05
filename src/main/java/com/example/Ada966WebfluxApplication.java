package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ada966WebfluxApplication {

    public static void main(String[] args) {

        SpringApplication.run(Ada966WebfluxApplication.class, args);
    }

    /*
    ------------------------------------------------------------
    GetAll: http://localhost:8080/clientes/
    Body: N/A
    ------------------------------------------------------------
    Save..: http://localhost:8080/clientes/save
    Body:
        {
            "nome": "Ulysses Mekaru TESTE MongoDB",
            "roles": ["Admin", "Partner", "Employee"],
            "nasc": 2010,
            "entrou": "2023-03-05"
        }
     ------------------------------------------------------------
     Save c/ Error Validate: http://localhost:8080/clientes/save
     Body:
        {
            "nome": null,
            "roles": [],
            "nasc": 1917,
            "entrou": "2023-03-01"
        }
     ------------------------------------------------------------
     Save All: http://localhost:8080/clientes/saveAll
     Body:
        [
            {
                "nome" : "Fred Mercury",
                "nasc" : 1960,
                "roles"  : [ "usuario", "cantor" ],
                "entrou" : "2023-02-20"
            },
            {
                "nome" : "Leo Santana",
                "nasc" : 1990,
                "roles"  : [ "usuario", "cantor" ],
                "entrou" : "2023-02-24"
            },
            {
                "nome" : "Michael Jordan",
                "nasc" : 1963,
                "roles"  : [ "usuario", "jogador", "basquete", "professor" ],
                "entrou" : "2023-02-27"
            }
        ]
      ------------------------------------------------------------
        Atualizar: http://localhost:8080/clientes/6404c00222e077514eb35c9a
        Obs.: Verificar um ID existente na base ao invés do valor de exemplo acima: 6404c00222e077514eb35c9a

        Body:
            {
                "id": "6404c00222e077514eb35c9a",
                "nome": "Juscelino A. de Carvalho",
                "roles": [
                    "Admin",
                    "Partner",
                    "Employee"
                ],
                "nasc": 1977,
                "entrou": "2023-03-01"
            }
        ------------------------------------------------------------
        GetByID: http://localhost:8080/clientes/6404c00222e077514eb35c9a
        Obs.: Verificar um ID existente na base ao invés do valor de exemplo acima: 6404c00222e077514eb35c9a
     */
}
