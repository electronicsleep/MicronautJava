package com.imgidea.micronaut_java;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.hateoas.Link;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller("/")
public class ScoreAdd {

    private static Logger logger = LoggerFactory.getLogger(ScoreAdd.class);

    @Post("/add-score")
    public HttpResponse scoreAdd(HttpRequest request, @Body String jsonBody) {
        logger.info("Endpoint: /add-score post jsonBody " + jsonBody);
        ObjectMapper mapper = new ObjectMapper();
        ScoreData scoreData = new ScoreData();
        try {
            //Convert from Json to Data Object
            scoreData = mapper.readValue(jsonBody, ScoreData.class);
            //Convert from Data Object to Json
            //String jsonString = mapper.writeValueAsString(scoreData);
            //logger.debug("Endpoint: /add-score post jsonString: " + jsonString);
        } catch (Exception e) {
            logger.error("Endpoint: /add-score Bad Request" + jsonBody);
            JsonError error = new JsonError("Bad Request").link(Link.SELF, Link.of(request.getUri()));
            return HttpResponse.<JsonError>badRequest().body(error);
        }

        ScoreRepo scoreRepo = new ScoreRepo();
        scoreRepo.ScoreAdd(scoreData.name, scoreData.score, scoreData.datetime);

        logger.info("Endpoint: /add-score post scoreData.name " + scoreData.name);
        logger.info("Endpoint: /add-score post scoreData.score " + scoreData.score);
        String body = "{\"AddScore\": \"Ok\"}";
        return HttpResponse.ok().body(body);
    }
}
