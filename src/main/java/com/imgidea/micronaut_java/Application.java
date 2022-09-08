package com.imgidea.micronaut_java;

import io.micronaut.runtime.Micronaut;
import io.micronaut.http.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/")
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Get("/")
    public String index() {
        logger.info("app endpoint");
        return "{\"Status\": \"MicronautJava\"}";
    }

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}