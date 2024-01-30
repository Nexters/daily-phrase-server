package com.nexters.dailyphrase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(
        servers = {
            @Server(url = "${server.servlet.context-path}", description = "Default Server URL")
        })
public class DailyphraseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyphraseApplication.class, args);
    }
}
