package com.imgidea.micronaut_java;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.QueryValue;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/")
public class TopScores {

    private static Logger logger = LoggerFactory.getLogger(Health.class);
    @Get("/top-score")
    public String topScores(@QueryValue String name, @QueryValue String score, @QueryValue Optional<String> verbose) {
        logger.info("Endpoint: /top-score name: " + name + " score: " + score + " verbose: " + verbose);
        if (verbose.isPresent()) {
            logger.info("Endpoint: /top verbose: " + verbose);
        }
        try {
            Integer.parseInt(score);
            logger.info("found an int");
        } catch (NumberFormatException ex) {
            logger.info("not an int");
            return "{\"error\": \"not a number\"}";
        }
        return "{\""+name+"\": \""+score+"\"}";
    }
    public static void main(String[] args) {
    }
}
