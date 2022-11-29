package com.imgidea.micronaut_java;

import static com.imgidea.micronaut_java.Constants.*;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/")
public class TextHtml {
    private static Logger logger = LoggerFactory.getLogger(TextHtml.class);
    @Get("/text-html/{name}")
    @Produces(MediaType.TEXT_HTML)
    public HttpResponse TextHtml(String name) {
        String html = HTML_HEADER + HEADER_LINKS + ABOUT + name;
        logger.info("Endpoint: /text-html/" + name);
        return HttpResponse.ok().body(html);
    }
}