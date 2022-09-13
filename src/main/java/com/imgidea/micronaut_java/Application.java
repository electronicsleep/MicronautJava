package com.imgidea.micronaut_java;

import io.micronaut.http.MediaType;
import io.micronaut.runtime.Micronaut;
import io.micronaut.http.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;

@OpenAPIDefinition(
        info = @Info(
                title = "demo",
                version = "0.0"
        )
)
@Controller("/")
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Get("/")
    public String index() {
        logger.info("Endpoint: /");
        return "{\"Status\": \"MicronautJava\"}";
    }

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}