package com.imgidea.micronaut_java;

import io.micronaut.runtime.Micronaut;
import io.micronaut.http.annotation.*;
import io.micronaut.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;

@OpenAPIDefinition(
        info = @Info(
                title = "MicronautJava",
                version = "0.1"
        )
)
@Controller("/")
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Get("/")
    public HttpResponse index() {
        logger.info("Endpoint: /");
        return HttpResponse.ok().body("{\"Status\": \"MicronautJava\"}");
    }

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}