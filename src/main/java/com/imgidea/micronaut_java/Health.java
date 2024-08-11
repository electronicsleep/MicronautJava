package com.imgidea.micronaut_java;

import static com.imgidea.micronaut_java.Constants.*;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/")
public class Health {

    private static Logger logger = LoggerFactory.getLogger(Health.class);

    @Get("/health")
    public HttpResponse health() {
        return HttpResponse.ok().body(HEALTH);
    }

    public static void main(String[] args) {}
}
