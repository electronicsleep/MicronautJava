package com.imgidea.micronaut_java;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.hateoas.Link;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/")
public class ScoreController {

    private static Logger logger = LoggerFactory.getLogger(
        ScoreController.class
    );

    @Get("/top-score")
    public HttpResponse ScoreController(
        HttpRequest request,
        Optional<String> search
    ) {
        logger.info("Endpoint: /top-score search " + search);

        ScoreRepo scoreRepo = new ScoreRepo();
        List<ScoreDto> scoreDto;

        if (search.isPresent()) {
            scoreDto = scoreRepo.SearchScores(search.get());
        } else {
            scoreDto = scoreRepo.SelectScores();
        }

        ObjectMapper mapper = new ObjectMapper();

        String jsonString = "";
        try {
            //Convert from Data Object to Json
            jsonString = mapper.writeValueAsString(scoreDto);
            logger.info("Endpoint: /top-score get jsonString: " + jsonString);
        } catch (Exception e) {
            e.printStackTrace();
            JsonError error = new JsonError("ServerError").link(
                Link.SELF,
                Link.of(request.getUri())
            );
            return HttpResponse.<JsonError>serverError().body(error);
        }
        if (jsonString == null) {
            JsonError error = new JsonError("ServerError").link(
                Link.SELF,
                Link.of(request.getUri())
            );
            return HttpResponse.<JsonError>serverError().body(error);
        }
        return HttpResponse.ok().body(jsonString);
    }

    public static void main(String[] args) {}
}
