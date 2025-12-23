package com.example.vuelos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Este es el punto de arranque de SpringBoot, es el que arranca el server de TomCat y levanta los controllers/services/repositories etc.

@SpringBootApplication
public class VuelosCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(VuelosCrudApplication.class, args);
    }

}
