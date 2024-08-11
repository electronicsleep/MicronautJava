package com.imgidea.micronaut_java;

import static com.imgidea.micronaut_java.Constants.*;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OpenAPIDefinition(info = @Info(title = "MicronautJava", version = "0.1"))
@Controller("/")
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(
        Application.class
    );

    @Produces(MediaType.TEXT_HTML)
    @Get("/")
    public HttpResponse index() {
        logger.info("Endpoint: /");
        String content =
            HTML_HEADER + HEADER_LINKS + "<pre>" + ART + "</pre>" + HTML_FOOTER;
        return HttpResponse.ok().body(content);
    }

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
