package com.imgidea.micronaut_java;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/")
public class Health {

    private static Logger logger = LoggerFactory.getLogger(Health.class);
    @Get("/health")
    public String health() {
        //logger.info("Endpoint: /health");
        return "{\"Status\": \"Ok\"}";
    }
    public static void main(String[] args) {
    }
}