package com.imgidea.micronaut_java;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.QueryValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller("/")
public class TopScores {

    private static Logger logger = LoggerFactory.getLogger(TopScores.class);
    @Get("/top-score")
    public String topScore(@QueryValue String name, @QueryValue String score, @QueryValue Optional<String> verbose) {
        logger.info("Endpoint: /top-score-get name: " + name + " score: " + score + " verbose: " + verbose);
        if (verbose.isPresent()) {
            logger.info("Endpoint: /top-score-get verbose: " + verbose);
        }
        try {
            Integer.parseInt(score);
            logger.info("Endpoint: /top-score-get found an int");
        } catch (NumberFormatException ex) {
            logger.info("Endpoint: /top-score-get not an int");
            return "{\"error\": \"not a number\"}";
        }
        return "{\""+name+"\": \""+score+"\"}";
    }

    @Post("/top-score")
    public String topScore(@Body String jsonBody) {
            logger.info("Endpoint: /top-score-post jsonBody " + jsonBody);
            ObjectMapper mapper = new ObjectMapper();
            ScoreData scoreData = new ScoreData();
        try {
            //Convert from Json to Data Object
            scoreData = mapper.readValue(jsonBody, ScoreData.class);
            //Convert from Data Object to Json
            String jsonString = mapper.writeValueAsString(scoreData);
            logger.info("Endpoint: /top-score-post jsonString: " + jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Endpoint: /top-score-post scoreData.name " + scoreData.name);
        logger.info("Endpoint: /top-score-post scoreData.score " + scoreData.score);
        return jsonBody;
    }
}
